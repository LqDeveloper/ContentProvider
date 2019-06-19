package com.example.contentprovidera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class MySqlManager extends SQLiteOpenHelper {
    private Context mContent;

    public MySqlManager(@Nullable Context context) {
        super(context, context.getExternalFilesDir("sql").getAbsolutePath() + "/sql.db", null, 1);
        mContent = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists user(userId INTEGER PRIMARY KEY  autoincrement," +
                "name varchar(30)," +
                "age int)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void createUser(ContentValues values) {
        getReadableDatabase().insert("user", null, values);
    }

    public void delete(String userId) {
        getReadableDatabase().delete("user", "userId=?", new String[]{userId});
    }

    public void updateUser(ContentValues values,String userId) {
        getReadableDatabase().update("user", values, "userId=?", new String[]{userId});
    }

    public Cursor query(String[] projection,String selection) {
        return getReadableDatabase().query("user",projection,selection,null,null,null,null);
    }

}
