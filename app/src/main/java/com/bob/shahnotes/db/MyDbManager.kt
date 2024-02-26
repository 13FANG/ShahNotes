package com.bob.shahnotes.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager(context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null
    fun openDb(){
        db = myDbHelper.writableDatabase
    }
    fun insertToDb( title: String, content: String){
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)
        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }
    @SuppressLint("Range")
    fun readDbData() : ArrayList<ListItem>{
        val dataList = ArrayList<ListItem>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null, null, null, null, null,)
        while(cursor?.moveToNext()!!){
            val dataTitle = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
            val dataContent = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_CONTENT))
            var item = ListItem()
            item.title = dataTitle
            item.desc = dataContent
            dataList.add(item)
        }
        cursor.close()
        return dataList
    }
    fun closeDb(){
        myDbHelper.close()
    }
}