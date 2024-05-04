package com.example.todo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs

class TaskDetailsActivity : AppCompatActivity() {
    val args: TaskDetailsActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

        val task = taskManager.tasks[args.taskPosition]

        findViewById<TextView>(R.id.taskNameTextView).text = task.name
        findViewById<TextView>(R.id.taskDescriotionTextView).text = task.description
        findViewById<TextView>(R.id.taskDoneTextView).text = task.isDone.toString()
    }
}