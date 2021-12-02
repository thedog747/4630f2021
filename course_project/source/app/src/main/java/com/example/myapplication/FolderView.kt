package com.example.myapplication

import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.add_link.*
import java.sql.DatabaseMetaData
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.folder_view.*
import kotlinx.android.synthetic.main.folder_view.CalendarButton
import kotlinx.android.synthetic.main.folder_view.Content


class FolderView : AppCompatActivity() {

    var folder_context : Context = this

    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.folder_view)

        CalendarButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        AddFileButton.setOnClickListener{
            val intent = Intent(this, AddLink::class.java)
            startActivity(intent)
        }

        clearAll.setOnClickListener{
            DataBaseHandlerLinks(this).clearDB()
            displayTasks()
        }

        displayTasks()

    }

    override fun onResume() {
        super.onResume()
        displayTasks()
    }

    fun displayTasks(){
        var links = DataBaseHandlerLinks(this).getTasks()
        var lm = LinearLayoutManager(this)
        Content.layoutManager = lm
        var adp: RecyclerAdapterLink = RecyclerAdapterLink(this, DataBaseHandlerLinks(this))
        Content.adapter = adp

        return
    }

}
