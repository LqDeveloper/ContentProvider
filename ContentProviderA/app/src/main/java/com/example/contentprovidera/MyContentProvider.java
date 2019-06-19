package com.example.contentprovidera;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    private MySqlManager sqlManager;
    @Override
    public boolean onCreate() {
        sqlManager = new MySqlManager(getContext());
        return true;
    }


    @Nullable
    @Override
    public Cursor query( @NonNull Uri uri,  @Nullable String[] projection,  @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return sqlManager.query(projection,selection);
    }


    @Nullable
    @Override
    public String getType( @NonNull Uri uri) {
        return "sqlite";
    }


    @Nullable
    @Override
    public Uri insert( @NonNull Uri uri, @Nullable ContentValues values) {
        sqlManager.createUser(values);
        return uri;
    }

    @Override
    public int delete( @NonNull Uri uri,String selection, @Nullable String[] selectionArgs) {
        sqlManager.delete(selection);
        return 0;
    }

    @Override
    public int update( @NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        sqlManager.updateUser(values,selection);
        return 0;
    }
}
