package com.example.todoapp.addTasks.domain

import com.example.todoapp.addTasks.data.TaskRepository
import com.example.todoapp.addTasks.ui.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
){
    suspend operator fun invoke(taskModel: TaskModel){
        taskRepository.add(taskModel)
    }
}