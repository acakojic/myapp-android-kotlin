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
        val CREATE_TABLE = "CREATE TABLE messages (id INTEGER PRIMARY KEY, message TEXT)"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS messages"
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun createTable(){
        val database: SQLiteDatabase = writableDatabase
        val CREATE_TABLE = "CREATE TABLE messages (id INTEGER PRIMARY KEY, message TEXT)"
        database.execSQL(CREATE_TABLE)
    }

    fun dropTable(){
        val DROP_TABLE = "DROP TABLE messages"
        val database: SQLiteDatabase = writableDatabase
        database.execSQL(DROP_TABLE)
    }

    fun insertMessages(name: String) {
        val database: SQLiteDatabase = writableDatabase
        val values: ContentValues
        values = ContentValues()
        values.put("message", name)
        database.insert(TABLE_NAME, null, values)
        //database.close()
    }

    fun selectMessages(): ArrayList<String> {
        val list: ArrayList<String>

        list = arrayListOf()
        val database: SQLiteDatabase = readableDatabase
        val cursor = database.rawQuery("SELECT message FROM messages", null)

        while (cursor.moveToNext())
        {
            //var id: Int = cursor.getInt(cursor.getColumnIndex("id"))
            var message: String = cursor.getString(cursor.getColumnIndex("message"))
            list.add(message)
        }
        return list
    }

    fun updateMessages(oldValue: String ,newValue: String){
        var database: SQLiteDatabase = writableDatabase

        var values: ContentValues = ContentValues()
        values.put("message", newValue)

        database.update(TABLE_NAME, values, "message = '" + oldValue + "'", null)
    }

    fun deleteMessages(message: String){
        var database: SQLiteDatabase = writableDatabase
        database.delete(TABLE_NAME, "message = '" + message + "'", null)
    }

    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "test_db"
        private val TABLE_NAME = "messages"
        private val MESSAGE = "message"
        private val ID = "id"
    }
}