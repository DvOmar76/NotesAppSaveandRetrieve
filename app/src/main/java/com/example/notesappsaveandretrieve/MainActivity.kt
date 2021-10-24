package com.example.notesappsaveandretrieve

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper=DBHelper(applicationContext)
        val notes=dbHelper.getData()
        recyclerView.adapter= RVAdapter(notes)
        recyclerView.layoutManager=LinearLayoutManager(this)
        btnAddNote.setOnClickListener {
            val note=edNote.text.toString()
            if (edNote.text.isNotEmpty()){
                val status=dbHelper.saveNote(note)
                if (status!=-1L){
                    dbHelper.saveNote(note)
                    Toast.makeText(applicationContext, "note added $status", Toast.LENGTH_SHORT).show()
                    recyclerView.adapter= RVAdapter(dbHelper.getData())
                    recyclerView.layoutManager=LinearLayoutManager(this)
                    recyclerView.scrollToPosition(notes.size-1);
                    Log.d("aasd321321","input: "+note)
                    edNote.text.clear()
                }
            }else{
                Toast.makeText(applicationContext, "note is empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}