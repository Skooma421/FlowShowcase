package com.example.flowshowcase

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(
        listOf(
            Task(1, "Buy groceries"),
            Task(2, "Finish homework"),
            Task(3, "Call Mom")
        )
    )
    val tasks: StateFlow<List<Task>> get() = _tasks

    fun addTask(task: Task) {
        _tasks.value = _tasks.value + task
    }

    fun removeTask(taskId: Int) {
        _tasks.value = _tasks.value.filter { it.id != taskId }
    }


}