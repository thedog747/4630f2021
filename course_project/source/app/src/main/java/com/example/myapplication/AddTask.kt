package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.add_task.*
import java.sql.DatabaseMetaData
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.graphics.Color
import android.widget.CalendarView

import android.widget.CalendarView.OnDateChangeListener




class AddTask : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task)
        var difficulty = 0
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        var selectedDate = sdf.format(Date(CalendarView.date))

        CalendarView.setOnDateChangeListener(OnDateChangeListener { calendar, year, month, dayOfMonth ->
            selectedDate = (dayOfMonth.toString() + "/" + month.toString() + "/" + year.toString())
        })

        SubmitTask.setOnClickListener(){
            val theDate: String = selectedDate
            var task = Task((System.currentTimeMillis()).toInt(), difficulty, Description.text.toString(), 0, theDate)
            var db = DataBaseHandler(this)
            db.addTask(task)
        }


        EasyButton.setOnClickListener(){
            difficulty = 0
            EasyButton.setBackgroundColor(Color.parseColor("#5F00ED"))
            MediumButton.setBackgroundColor(Color.parseColor("#808080"))
            HardButton.setBackgroundColor(Color.parseColor("#808080"))
        }

        MediumButton.setOnClickListener(){
            difficulty = 1
            EasyButton.setBackgroundColor(Color.parseColor("#808080"))
            MediumButton.setBackgroundColor(Color.parseColor("#5F00ED"))
            HardButton.setBackgroundColor(Color.parseColor("#808080"))
        }

        HardButton.setOnClickListener(){
            difficulty = 2
            EasyButton.setBackgroundColor(Color.parseColor("#808080"))
            MediumButton.setBackgroundColor(Color.parseColor("#808080"))
            HardButton.setBackgroundColor(Color.parseColor("#5F00ED"))
        }

    }

}
