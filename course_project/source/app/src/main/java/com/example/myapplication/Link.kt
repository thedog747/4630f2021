package com.example.myapplication

import android.telecom.Call
import java.time.Instant
import java.util.*

class Link {

    var id : Int = 0
    var title : String = ""
    var hyperlink : String = ""
    var date : String = ""


    constructor(id: Int, title: String, hyperlink : String, date: String){
        this.id = id
        this.title = title
        this.hyperlink = hyperlink
        this.date = date
    }

}
