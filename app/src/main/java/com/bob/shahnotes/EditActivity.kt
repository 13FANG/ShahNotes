package com.bob.shahnotes

import android.app.Activity
import android.content.Intent
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bob.shahnotes.databinding.EditActivityBinding
import com.bob.shahnotes.db.MyDbManager

class EditActivity : AppCompatActivity() {
    lateinit var bindingClass : EditActivityBinding
    val myDbManager = MyDbManager(this)
    val imageRequestCode = 10
    var tempImageUri = "empty"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = EditActivityBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = with(bindingClass) {
        super.onActivityResult(requestCode, resultCode, data)
    }
    override fun onResume() = with(bindingClass)  {
        super.onResume()
        myDbManager.openDb()
        val dataList = myDbManager.readDbData()
    }
    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
    fun onClickSave(view: View) = with(bindingClass) {
        val title = edTitle.text.toString()
        val desc = edDesc.text.toString()
        if (title != "" && desc != ""){
            myDbManager.insertToDb(title, desc, tempImageUri)
        }
    }
}