package com.prodigyinfotech.tasktwo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {

    @Insert
    suspend fun insertTodo(todo: TodoModel)

    @Update
    suspend fun updateTodo(todo: TodoModel)

    @Delete
    suspend fun deleteTodo(todo: TodoModel)

    @Query("SELECT * FROM todo")
    fun getTodoList() : LiveData<List<TodoModel>>

}