package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val taskManager: TaskManager,
    private val navController: NavController
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskCheck: CheckBox
        val deletaView: ImageView
        val view: View

        init {
            this.view = view
            taskCheck = view.findViewById<CheckBox>(R.id.task_check)
            deletaView = view.findViewById<ImageView>(R.id.deleteIcon)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.task_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val task = taskManager.tasks[position]
        viewHolder.taskCheck.text = task.name
        viewHolder.taskCheck.isChecked = task.isDone
        viewHolder.taskCheck.setOnClickListener {
            taskManager.checkTask(position)
        }
        viewHolder.view.setOnClickListener {
            navController.navigate(
                TaskListFragmentDirections.toTaskDetailsActivity(position)
            )
        }
        viewHolder.deletaView.setOnClickListener {
            taskManager.removeTask(position)
            notifyDataSetChanged()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = taskManager.tasks.size

}