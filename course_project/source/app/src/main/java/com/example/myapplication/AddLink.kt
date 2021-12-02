package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.add_link.*


class AddLink : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_link)
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val date = Date()
        var selectedDate = sdf.format(date)

        SubmitLink.setOnClickListener(){
            var link = Link((System.currentTimeMillis()).toInt(), TitleView.text.toString(), Link.text.toString(), selectedDate)
            var db = DataBaseHandlerLinks(this)
            db.addTask(link)
        }

    }

}
