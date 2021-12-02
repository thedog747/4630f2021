package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.DatabaseMetaData
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.provider.ContactsContract
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AddTaskButton.setOnClickListener{
            val intent = Intent(this, AddTask::class.java)
            startActivity(intent)
        }
        ConceptsButton.setOnClickListener{
            val intent = Intent(this, FolderView::class.java)
            startActivity(intent)
            finish();
        }
        extraStuff.setOnClickListener{
            DataBaseHandler(this).clearDB()
            displayTasks()
        }
        displayTasks()
    }

    override fun onResume() {
        super.onResume()
        displayTasks()
    }

    fun displayTasks(){
        var tasks = DataBaseHandler(this).getTasks()
        var lm = LinearLayoutManager(this)
        Content.layoutManager = lm
        var adp: RecyclerAdapterTask = RecyclerAdapterTask(this, DataBaseHandler(this))
        Content.adapter = adp

        return
    }
}


