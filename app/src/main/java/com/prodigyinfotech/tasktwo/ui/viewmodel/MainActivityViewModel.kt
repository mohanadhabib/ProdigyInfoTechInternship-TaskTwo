package com.prodigyinfotech.tasktwo.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.prodigyinfotech.tasktwo.data.TodoDatabase
import com.prodigyinfotech.tasktwo.data.TodoModel
import kotlinx.coroutines.launch

class MainActivityViewModel(private val application: Application) : AndroidViewModel(application) {

    fun insertTodo(todo: TodoModel){
        viewModelScope.launch {
            TodoDatabase.getDatabase(application.applicationContext).dao.insertTodo(todo)
        }
    }

    fun updateTodo(todo: TodoModel){
        viewModelScope.launch {
            TodoDatabase.getDatabase(application.applicationContext).dao.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: TodoModel){
        viewModelScope.launch {
            TodoDatabase.getDatabase(application.applicationContext).dao.deleteTodo(todo)
        }
    }

    fun getTodoList():LiveData<List<TodoModel>>{
        return TodoDatabase.getDatabase(application.applicationContext).dao.getTodoList()
    }

}