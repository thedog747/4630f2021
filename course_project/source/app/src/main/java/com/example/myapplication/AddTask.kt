package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.add_task.*
import java.sql.DatabaseMetaData
import java.text.SimpleDateFormat
import java.util.*

class AddTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task)

        SubmitTask.setOnClickListener(){
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val just_day = SimpleDateFormat("dd")
            val just_month = SimpleDateFormat("MM")
            val just_year = SimpleDateFormat("yyyy")
            val theDate: String = sdf.format(Date(CalendarView.getDate()))
            val day = just_day.format(theDate).toInt()
            val month = just_month.format(theDate).toInt()
            val year = just_year.format(theDate).toInt()
            calendar.set(year,month,day)

            var task = Task(Difficulty.text.toString().toInt(), Description.text.toString(), 0, calendar)
            var db = DataBaseHandler(this)
            db.addTask(task)
        }
    }
}
