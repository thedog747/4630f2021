package com.example.myapplication

import android.app.PendingIntent.getActivity
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.security.AccessController.getContext
import java.util.*

val DB_NAME = "Inception"
val TABLE_NAME = "Tasks"
val KEY_ID = "id"
val KEY_COMPLEXITY = "complexity"
val KEY_COMPLETION = "completion"
val KEY_DATE = "DATE"
val KEY_DETAILS = "DETAILS"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1){
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_COMPLEXITY + " INTEGER,"  +
                KEY_COMPLETION + " INTEGER,"  +
                KEY_DATE + " TEXT," +
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
            put(KEY_DATE, task.date)
            put(KEY_DETAILS, task.details)
        }

        val result = db.insert(TABLE_NAME, null, cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "Failed to add task!", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()
        return result
    }

}
