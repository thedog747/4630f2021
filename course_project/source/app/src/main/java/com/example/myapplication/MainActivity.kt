package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.DatabaseMetaData
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AddTask.setOnClickListener{ startActivity(Intent(this, AddTask::class.java)) }
    }
}
