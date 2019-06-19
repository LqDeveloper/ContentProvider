package com.example.contentproviderb;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Uri uri = Uri.parse("content://com.my.contentprovider/");

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.add);
        textView1.setOnClickListener(this);

        textView2 = findViewById(R.id.update);
        textView2.setOnClickListener(this);

        textView3 = findViewById(R.id.delete);
        textView3.setOnClickListener(this);

        textView4 = findViewById(R.id.query);
        textView4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        ContentValues values = new ContentValues();
        switch (v.getId()) {
            case R.id.add:

                values.put("name","小明");
                values.put("age",10);
                getContentResolver().insert(uri,values);
                break;
            case R.id.update:
                values.put("age",20);
                getContentResolver().update(uri,values,"1",null);
                break;
            case R.id.delete:
                getContentResolver().delete(uri,"1",null);
                break;
            case R.id.query:
                Cursor cursor = getContentResolver().query(uri,new String[]{"userId,name,age"},"",null,null);
                if (cursor.moveToFirst()){
                    do {
                        Log.e("MainActivity","userId: " + cursor.getInt(cursor.getColumnIndex("userId")) + " name: "
                                +cursor.getString(cursor.getColumnIndex("name"))+
                                "  age:  "+cursor.getInt(cursor.getColumnIndex("age")));
                    }while (cursor.moveToNext());
                }
                break;
        }
    }
}
