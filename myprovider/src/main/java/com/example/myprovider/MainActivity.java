package com.example.myprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity  implements AutoPermissionsListener {
    Button btn1,btn2,btn3;
    EditText txt1,txt2,txt3,txt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoPermissions.Companion.loadAllPermissions(this,101);

     //   ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG,Manifest.permission.READ_CONTACTS},MODE_PRIVATE);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        txt1 =findViewById(R.id.txt1);
        txt2 =findViewById(R.id.txt2);
        txt3 =findViewById(R.id.txt3);
        txt4 =findViewById(R.id.txt4);
        btn1.setOnClickListener(v->{
        txt1.setText(getCallHistory());

        });
        btn2.setOnClickListener(v->{
            txt1.setText(getContents());
        });

        btn3.setOnClickListener(v->{
            addContent();
        });
    }

    public void addContent() {
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, txt2.getText())

                .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE,
                        ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                // Inserts a phone number
                .putExtra(ContactsContract.Intents.Insert.PHONE, txt3.getText())

                .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE,
                        ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
            .putExtra(ContactsContract.Intents.Insert.NAME, txt4.getText());

        startActivity(intent);
    }

    public String getContents(){
        String[] PROJECTION =
                {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.LOOKUP_KEY,
                        Build.VERSION.SDK_INT
                                >= Build.VERSION_CODES.HONEYCOMB ?
                                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                                ContactsContract.Contacts.DISPLAY_NAME

                };
        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                PROJECTION, null, null, null);



        StringBuffer callBuff = new StringBuffer(); // 최대 100 통화 저장

        c.moveToFirst();
        do {

            callBuff.append(c.getString(0));

            callBuff.append(":"+c.getString(2)+"\n\n" );
        } while (c.moveToNext());

        c.close();
        return callBuff.toString();




    }

    public String getCallHistory() {
        String[] callSet = new String[]{CallLog.Calls.DATE,
                CallLog.Calls.TYPE, CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION};

        Cursor c = getContentResolver().query(CallLog.Calls.CONTENT_URI,
                callSet, null, null, null);

        if (c == null)
            return "통화기록 없음";

        StringBuffer callBuff = new StringBuffer(); // 최대 100 통화 저장
        callBuff.append("\n날짜 : 구분 : 전화번호 : 통화시간\n\n");
        c.moveToFirst();
        do {
            long callDate = c.getLong(0);
            SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
            String date_str = datePattern.format(new Date(callDate));
            callBuff.append(date_str + ":");
            if (c.getInt(1) == CallLog.Calls.INCOMING_TYPE)
                callBuff.append("착신 :");
            else
                callBuff.append("발신 :");
            callBuff.append(c.getString(2) + ":");
            callBuff.append(c.getString(3) + "초\n");
        } while (c.moveToNext());

        c.close();
        return callBuff.toString();
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