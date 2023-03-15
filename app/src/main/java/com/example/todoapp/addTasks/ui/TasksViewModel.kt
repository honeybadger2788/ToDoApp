package com.example.todoapp.addTasks.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.addTasks.domain.AddTaskUseCase
import com.example.todoapp.addTasks.domain.GetTasksUseCase
import com.example.todoapp.addTasks.ui.TasksUiState.*
import com.example.todoapp.addTasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    getTasksUseCase: GetTasksUseCase
):ViewModel() {
    val uiState: StateFlow<TasksUiState> = getTasksUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    //private val _tasksList = mutableStateListOf<TaskModel>()
    //val tasksList: List<TaskModel> = _tasksList

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        _showDialog.value = false
        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        /*val index = _tasksList.indexOf(taskModel)
        _tasksList[index] = _tasksList[index].let {
            it.copy(selected = !it.selected)
        }*/
    }

    fun onItemRemove(taskModel: TaskModel) {
        /*val task = _tasksList.find { it.id == taskModel.id }
        _tasksList.remove(task)*/
    }
}