package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

val DB_NAME = "Inception"
val TABLE_NAME = "Tasks"
val KEY_ID = "id"
val KEY_COMPLEXITY = "complexity"
val KEY_COMPLETION = "completion"
val KEY_DAY = "DAY"
val KEY_MONTH = "MONTH"
val KEY_YEAR = "YEAR"
val KEY_DETAILS = "DETAILS"

class DataBaseHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1){
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_COMPLEXITY + " INTEGER,"  +
                KEY_COMPLETION + " INTEGER,"  +
                KEY_DAY + " INTEGER,"  +
                KEY_MONTH + " INTEGER,"  +
                KEY_YEAR + " INTEGER,"  +
                KEY_DETAILS + " TEXT" + ")")
        p0?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addTask(task : Task):Long{
        val db = this.writableDatabase
        val cv = ContentValues()
        with(cv) {
            put(KEY_ID, task.id)
            put(KEY_COMPLEXITY, task.complexity)
            put(KEY_COMPLETION, task.completion)
            put(KEY_DAY, task.date.get(Calendar.DAY_OF_MONTH))
            put(KEY_MONTH, task.date.get(Calendar.MONTH))
            put(KEY_YEAR, task.date.get(Calendar.YEAR))
            put(KEY_DETAILS, task.details)
        }
        db.close()

        val result = db.insert(TABLE_NAME, null, cv)

        return result
    }

}