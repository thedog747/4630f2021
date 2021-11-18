package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.add_task.*
import java.sql.DatabaseMetaData
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.graphics.Color

class AddTask : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task)
        var difficulty = 0

        SubmitTask.setOnClickListener(){
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val theDate: String = sdf.format(Date(CalendarView.getDate()))
            var task = Task((System.currentTimeMillis()).toInt(), difficulty, Description.text.toString(), 0, theDate)
            var db = DataBaseHandler(this)
            db.addTask(task)
        }

        EasyButton.setOnClickListener(){
            difficulty = 0
            EasyButton.setBackgroundColor(Color.parseColor("#5F00ED"))
            MediumButton.setBackgroundColor(Color.parseColor("#000000"))
            HardButton.setBackgroundColor(Color.parseColor("#000000"))
        }

        MediumButton.setOnClickListener(){
            difficulty = 1
            EasyButton.setBackgroundColor(Color.parseColor("#000000"))
            MediumButton.setBackgroundColor(Color.parseColor("#5F00ED"))
            HardButton.setBackgroundColor(Color.parseColor("#000000"))
        }

        HardButton.setOnClickListener(){
            difficulty = 2
            EasyButton.setBackgroundColor(Color.parseColor("#000000"))
            MediumButton.setBackgroundColor(Color.parseColor("#000000"))
            HardButton.setBackgroundColor(Color.parseColor("#5F00ED"))
        }

    }

}
