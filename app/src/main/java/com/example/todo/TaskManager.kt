package com.example.todo

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlin.random.Random

@Entity
data class Task(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "is_done") var isDone: Boolean
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE uid = :taskId")
    fun loadAllByIds(taskId: Int): List<Task>

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(user: Task)
}

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

class TaskManager {
    val tasks = ObservableArrayList<Task>()
    val completedTasksCount = ObservableField<String>().apply { set("0") }

    lateinit var db: AppDatabase

    fun initDb(applicationContext: Context) {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        tasks.addAll(db.taskDao().getAll())
        completedTasksCount.set(tasks.count { it.isDone }.toString())
    }

    fun addTask(name: String, description: String) {
        val task = Task(Random.nextInt(), name, description, false)
        tasks.add(
            task
        )

        db.taskDao().insert(task)
    }

    fun checkTask(position: Int) {
        tasks[position].isDone = !tasks[position].isDone
        completedTasksCount.set(tasks.count { it.isDone }.toString())

        db.taskDao().update(tasks[position])
    }

    fun removeTask(position: Int) {
        val task = tasks[position]
        tasks.removeAt(position)

        db.taskDao().delete(task)
    }
}