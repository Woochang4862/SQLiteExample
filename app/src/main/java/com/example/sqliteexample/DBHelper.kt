package com.example.sqliteexample

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(context: Context) : SQLiteOpenHelper(context, "school.db", null, 1) {
    val TAG = "####"
    val TABLE = "student"

    companion object {
        val _id: String = "_id"
        val genre: String = "genre"
        val title: String = "title"
        val content: String = "content"
    }

    val DATABASE_CREATE =
        "CREATE TABLE if not exists " + TABLE + " (" +
                "${_id} integer PRIMARY KEY AUTOINCREMENT," +
                "${genre} text," +
                "${title} text,"+
                "${content} text"+
                ")"

    fun addStudent(_id:String, _name:String, _phone: String, _depart:String) {
        val values = ContentValues()
        values.put(num, _num)
        values.put(name, _name)
        values.put(phone, _phone)
        values.put(depart, _depart)
        writableDatabase.insert(TABLE, null, values);
    }

    fun getStudent() : Cursor {
        return readableDatabase.query(TABLE, arrayOf(num, name, phone, depart), null, null, null, null, null);
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "Creating: " + DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        val dropSQL = "drop table student"
    }

}
