package com.example.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

val taskManager = TaskManager()

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskManager.initDb(this)
    }

}