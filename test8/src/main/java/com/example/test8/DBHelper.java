package com.example.test8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private String TABLE = "song";

    public DBHelper(@Nullable Context context) {
        super(context, "song_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists " + TABLE + "(_id integer PRIMARY KEY AUTOINCREMENT, genre text, title text, content text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addStudent(String genre, String title, String content){
        ContentValues newData = new ContentValues();
        newData.put("genre",genre);
        newData.put("title",title);
        newData.put("content", content);
        getWritableDatabase().insert(TABLE, null, newData);
    }

    public Cursor getStudent(String title){
        return getReadableDatabase().query(TABLE, new String[]{"_id","genre","title","content"},"title=?",new String[]{title}, null,null, null,null);
    }
}
