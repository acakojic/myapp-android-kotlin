package com.example.koja.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

import java.util.ArrayList

class MessagesDBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE messages (message TEXT);"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS messages"
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun insertMessages(name: String) {
        val db: SQLiteDatabase = writableDatabase
        val values: ContentValues
        values = ContentValues()
        values.put("message", name)
        db.insert(TABLE_NAME, null, values)
        //db.close()
    }

    fun selectMessages(): ArrayList<String> {
        var list: ArrayList<String>

        list = arrayListOf()
        val database: SQLiteDatabase = readableDatabase
        val cursor = database.rawQuery("SELECT * FROM messages", null)

        while (cursor.moveToNext())
        {
            var message: String = cursor.getString(cursor.getColumnIndex("message"))
            list.add(message)
        }
        return list
    }

    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "test_db"
        private val TABLE_NAME = "messages"
        private val MESSAGE = "message"
    }
}