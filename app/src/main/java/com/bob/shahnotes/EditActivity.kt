package com.bob.shahnotes

import android.app.Activity
import android.content.Intent
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bob.shahnotes.databinding.EditActivityBinding
import com.bob.shahnotes.db.MyDbManager
import com.bob.shahnotes.db.MyIntentConstants

class EditActivity : AppCompatActivity() {
    lateinit var bindingClass : EditActivityBinding
    val myDbManager = MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = EditActivityBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        getMyIntents()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = with(bindingClass) {
        super.onActivityResult(requestCode, resultCode, data)
    }
    override fun onResume() = with(bindingClass)  {
        super.onResume()
        myDbManager.openDb()
    }
    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
    fun onClickSave(view: View) = with(bindingClass) {
        val title = edTitle.text.toString()
        val desc = edDesc.text.toString()
        if (title != "" && desc != ""){
            myDbManager.insertToDb(title, desc)
        }
        finish()
    }
    fun getMyIntents() = with(bindingClass) {
        val i = intent
        if (i.getStringExtra(MyIntentConstants.I_TITLE_KEY) != null){
            edTitle.setText(i.getStringExtra(MyIntentConstants.I_TITLE_KEY))
            edDesc.setText(i.getStringExtra(MyIntentConstants.I_DESC_KEY))
        }
    }
}