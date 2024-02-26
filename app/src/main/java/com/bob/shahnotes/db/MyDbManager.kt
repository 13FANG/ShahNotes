package com.bob.shahnotes.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class MyDbManager(context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null
    fun openDb(){
        db = myDbHelper.writableDatabase
    }
    fun removeItemFromDb( id: String){
        val selection = BaseColumns._ID + "=$id"
        db?.delete(MyDbNameClass.TABLE_NAME, selection, null)
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
            val dataId = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            var item = ListItem()
            item.title = dataTitle
            item.desc = dataContent
            item.id = dataId
            dataList.add(item)
        }
        cursor.close()
        return dataList
    }
    fun closeDb(){
        myDbHelper.close()
    }
}