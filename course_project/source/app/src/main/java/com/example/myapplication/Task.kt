package com.example.myapplication

import android.telecom.Call
import java.time.Instant
import java.util.*

enum class Complexity(val difficulty: Int) { TRIVIAL(0), NORMAL(1), COMPLEX(2) }
enum class Completion(val status: Int) { INCOMPLETE(0), COMPLETE(1) }

class Task {

    var id : Int = 0
    var complexity :  Int = Complexity.NORMAL.difficulty
    var completion : Int = Completion.INCOMPLETE.status
    var date : Calendar = Calendar.getInstance()
    var details : String = ""

    constructor(complexity: Int, details: String, completion: Int, date: Calendar){
        this.complexity = complexity
        this.completion = completion
        this.details = details
        this.date = date
    }

}
