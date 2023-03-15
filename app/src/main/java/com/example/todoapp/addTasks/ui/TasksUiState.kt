package com.example.todoapp.addTasks.ui

import com.example.todoapp.addTasks.ui.model.TaskModel

sealed interface TasksUiState {
    object Loading: TasksUiState
    data class Error(val throwable: Throwable): TasksUiState
    data class Success(val tasks: List<TaskModel>): TasksUiState
}