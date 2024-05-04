package com.example.todo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        findViewById<Button>(R.id.createTask).setOnClickListener {
            val taskName = findViewById<EditText>(R.id.taskNameTextField).text.toString()
            val taskDescription =
                findViewById<EditText>(R.id.taskDescriptionTextField).text.toString()

            taskManager.addTask(taskName, taskDescription)
            finish()
        }
    }
}