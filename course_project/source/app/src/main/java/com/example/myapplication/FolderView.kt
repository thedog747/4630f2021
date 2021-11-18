package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.add_task.*
import java.sql.DatabaseMetaData
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.graphics.Color
import kotlinx.android.synthetic.main.activity_main.*

class FolderView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.folder_view)

        CalendarButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
