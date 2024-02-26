package com.bob.shahnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bob.shahnotes.databinding.ActivityMainBinding
import com.bob.shahnotes.db.MyAdapter
import com.bob.shahnotes.db.MyDbManager

class MainActivity : AppCompatActivity() {
    val myDbManager = MyDbManager(this)
    val myAdapter = MyAdapter(ArrayList(), this)
    lateinit var bindingClass : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        init()
    }
    override fun onResume() = with(bindingClass)  {
        super.onResume()
        myDbManager.openDb()
        fillAdapter()
    }
    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
    fun onClickNew(view: View) = with(bindingClass) {
        val i = Intent(this@MainActivity,EditActivity::class.java)
        startActivity(i)
    }
    fun init() = with(bindingClass) {
        rcView.layoutManager = LinearLayoutManager(this@MainActivity)
        rcView.adapter = myAdapter
    }
    fun fillAdapter() = with(bindingClass) {
        val list = myDbManager.readDbData()
        myAdapter.updateAdapter(list)
        if (list.size>0){ tvNoElements.visibility = View.GONE}
        else {tvNoElements.visibility = View.VISIBLE}
    }
}