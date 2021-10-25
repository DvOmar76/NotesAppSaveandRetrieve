package com.example.notesappsaveandretrieve

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper (context: Context):SQLiteOpenHelper(context,"details.db",null,1){
    val sqLiteDatabase:SQLiteDatabase=writableDatabase
    val db:SQLiteDatabase=readableDatabase
    override fun onCreate(p0: SQLiteDatabase?) {
        if(p0!=null){
            p0.execSQL("create table Notes(note text)")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun saveNote(note:String): Long {
        val cv=ContentValues()
        cv.put("note",note)
        return sqLiteDatabase.insert("Notes",null,cv)

    }

    fun getData():ArrayList<String>{
        val notes=ArrayList<String>()
        val c:Cursor=db.query("Notes",null,null,null,null,null,null)
        c.moveToFirst()
        while (c.moveToNext()){
            val note=c.getString(c.getColumnIndex("note"))
           Log.d("aasd321321",note)
            notes.add(note)
       
       }
        Log.d("aasd321321",notes.toString())
        c.close()
        return notes
    }

}
