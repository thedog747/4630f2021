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

val DB_NAME_LINKS = "InceptionLinks"
val TABLE_NAME_LINKS = "Links"
val KEY_ID_LINKS = "id"
val KEY_TITLE_LINKS = "Title"
val KEY_HYPERLINK_LINKS = "Hyperlink"
val KEY_DATE_LINKS = "Date"

class DataBaseHandlerLinks(var context: Context) : SQLiteOpenHelper(context, DB_NAME_LINKS, null, 1){
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE " + TABLE_NAME_LINKS + " (" +
                KEY_ID_LINKS + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_TITLE_LINKS + " TEXT," +
                KEY_HYPERLINK_LINKS + " TEXT," +
                KEY_DATE_LINKS + " TEXT" + ")")
        p0?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addTask(link : Link):Long{
        val db = this.writableDatabase
        val cv = ContentValues()
        with(cv) {
            put(KEY_ID_LINKS, link.id)
            put(KEY_TITLE_LINKS, link.title)
            put(KEY_HYPERLINK_LINKS, link.hyperlink)
            put(KEY_DATE_LINKS, link.date)
        }
        val result = db.insert(TABLE_NAME_LINKS, null, cv)

        db.close()

        if(result == (-1).toLong())
            Toast.makeText(context, "Failed to add link!", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()
        return result
    }

    fun getTasks() : MutableList<Link>{
        val db = this.readableDatabase
        val request = "Select * from $TABLE_NAME_LINKS"
        val result = db.rawQuery(request, null)
        var links : MutableList<Link> = ArrayList()

        if(!(result.moveToFirst())){
            result.close()
            db.close()
            return links
        }

        var proceed = true
        while(proceed) {
            val id = result.getString(result.getColumnIndex(KEY_ID_LINKS).toInt()).toInt()
            val title = result.getString(result.getColumnIndex(KEY_TITLE_LINKS).toInt())
            val hyperlink = result.getString(result.getColumnIndex(KEY_HYPERLINK_LINKS).toInt())
            val date = result.getString(result.getColumnIndex(KEY_DATE_LINKS).toInt())
            var link = Link(id, title, hyperlink, date)
            links.add(link)

            proceed = result.moveToNext()
        }

        result.close()
        db.close()
        return links
    }


    fun clearDB(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME_LINKS, null, null)
        db.close()
    }

    fun deleteElement(id : String){
        val db = this.writableDatabase
        db.delete(TABLE_NAME_LINKS, "$KEY_ID=?", arrayOf(id))
        db.close()
    }

}
