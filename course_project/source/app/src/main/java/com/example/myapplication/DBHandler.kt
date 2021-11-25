package com.example.myapplication

import android.app.PendingIntent.getActivity
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.security.AccessController.getContext
import java.util.*
import kotlin.collections.ArrayList

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

        db.close()

        if(result == (-1).toLong())
            Toast.makeText(context, "Failed to add task!", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()
        return result
    }

    fun getTasks() : MutableList<Task>{
        val db = this.readableDatabase
        val request = "Select * from " + TABLE_NAME
        val result = db.rawQuery(request, null)
        var tasks : MutableList<Task> = ArrayList()

        if(!(result.moveToFirst())){
            result.close()
            db.close()
            return tasks
        }

        var proceed = true
        while(proceed) {
            val id = result.getString(result.getColumnIndex(KEY_ID).toInt()).toInt()
            val complexity = result.getString(result.getColumnIndex(KEY_COMPLEXITY).toInt()).toInt()
            val completion = result.getString(result.getColumnIndex(KEY_COMPLETION).toInt()).toInt()
            val date = result.getString(result.getColumnIndex(KEY_DATE).toInt())
            val details = result.getString(result.getColumnIndex(KEY_DETAILS).toInt())
            var task = Task(id, complexity, details, completion, date)
            tasks.add(task)

            proceed = result.moveToNext()
        }

        result.close()
        db.close()
        return tasks
    }


    fun clearDB(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        db.close()
    }

}
