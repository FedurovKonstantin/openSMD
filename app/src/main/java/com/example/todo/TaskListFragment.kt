package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.FragmentTaskListBinding


class TaskListFragment : Fragment() {
    private lateinit var customAdapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val binding =
            DataBindingUtil.inflate<FragmentTaskListBinding>(
                inflater,
                R.layout.fragment_task_list,
                container,
                false
            )

        binding.tasks = taskManager.tasks
        binding.completdTasks = taskManager.completedTasksCount
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        


        customAdapter = CustomAdapter(taskManager, findNavController())

        customAdapter.notifyDataSetChanged()

        val recyclerView: RecyclerView = requireView().findViewById(R.id.task_list)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        requireView().findViewById<Button>(R.id.button).setOnClickListener {
            findNavController().navigate(
                TaskListFragmentDirections.toNewTaskActivity()
            )
        }
    }

    override fun onResume() {
        super.onResume()
        customAdapter.notifyDataSetChanged()
    }


}