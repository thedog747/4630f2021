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


class MainActivity : AppCompatActivity() {
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
        tasksView.text = String()
        for (i in tasks){
            var tempComplex = String()
            if(i.complexity == 0)
                tempComplex = "Easy"
            if(i.complexity == 1)
                tempComplex = "Medium"
            if(i.complexity == 2)
                tempComplex = "Hard"

            var tempComplete = String()
            if(i.completion == 0)
                tempComplete = "Incomplete"
            if(i.completion == 1)
                tempComplete = "Complete"

            tasksView.append("(" + i.details + ")\n"
                    + i.date + "\n"
                    + tempComplex + " | "
                    + tempComplete + " | "
                    + "ID: " + i.id.toString()
                    + "\n\n")
        }
    }
}


