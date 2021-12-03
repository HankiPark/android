package com.example.mydiary;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity implements AutoPermissionsListener {
    DBHelper dbhelper;
    ImageView imageDiary;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 2;
    static final int REQUEST_PHOTO_SELECTION = 3;
    String currentPhotoPath;
    Uri photoURI;
    File photoFile;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        AutoPermissions.Companion.loadAllPermissions(this,101);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        Button btn1 = findViewById(R.id.btnSave);
        Button btn2 = findViewById(R.id.btnImage);
        Button btn3 = findViewById(R.id.btn3);

        TextView edTitle = findViewById(R.id.editTitle);
        TextView edContent = findViewById(R.id.editContent);
        imageDiary = findViewById(R.id.imageDiary);
        dbhelper = new DBHelper(getApplicationContext());
        Intent intent2 = getIntent();

        btn3.setOnClickListener(v->{
           getPhoto();

        });
        btn2.setOnClickListener(v -> {

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            photoFile = null;
            Log.d("TAG", "onCreate: sd");
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this,
                        "com.example.mydiary",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }

               // startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);



        });


        if (intent2.getExtras().getString("_id").equals("abc")) { //hasExtras() 아니면 getExtras()만
            btn1.setOnClickListener(v -> {

                DiaryVO diaryVO = new DiaryVO();
                diaryVO.setTitle(edTitle.getText().toString());
                diaryVO.setContent(edContent.getText().toString());
                if(photoURI !=null){
                diaryVO.setImg(photoURI.toString());}else{}
               // diaryVO.setImg();
                DiaryDAO.insert(dbhelper, diaryVO);

                setResult(2, null);
                finish();
            });
        } else {


            edTitle.setText(intent2.getExtras().getString("title"));
            edContent.setText(intent2.getExtras().getString("content"));
            imageDiary.setImageURI(Uri.parse(intent2.getExtras().getString("img")));
            btn1.setOnClickListener(v -> {

                DiaryVO diaryVO = new DiaryVO();
                diaryVO.setId(intent2.getExtras().getString("_id"));
                diaryVO.setTitle(edTitle.getText().toString());
                diaryVO.setContent(edContent.getText().toString());

                if(photoURI !=null){
                    diaryVO.setImg(photoURI.toString());}else{
                    diaryVO.setImg(intent2.getExtras().getString("img"));
                }
                DiaryDAO.update(dbhelper, diaryVO);
                setResult(1, null);
                finish();
            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageDiary.setImageBitmap(imageBitmap);

            }
       else if(requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            imageDiary.setImageURI(photoURI);
        }else if(requestCode == REQUEST_PHOTO_SELECTION && resultCode ==RESULT_OK){
           Uri selectedImg = data.getData();
            Log.d("uri", selectedImg.toString());
           imageDiary.setImageURI(selectedImg);
           photoURI=selectedImg;
        }
        }
private void getPhoto(){
    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    startActivityForResult(intent,REQUEST_PHOTO_SELECTION);
}

    @Override
    public void onDenied(int i, String[] strings) { }
    @Override
    public void onGranted(int i, String[] strings) { }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    { super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);}

}


