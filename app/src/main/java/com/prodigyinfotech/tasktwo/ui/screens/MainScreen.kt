package com.prodigyinfotech.tasktwo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prodigyinfotech.tasktwo.data.TodoModel
import com.prodigyinfotech.tasktwo.ui.composables.Dialog
import com.prodigyinfotech.tasktwo.ui.composables.TaskItem
import com.prodigyinfotech.tasktwo.ui.viewmodel.MainActivityViewModel

@Composable
fun MainScreen(viewModel: MainActivityViewModel){

    // State variables
    val list by viewModel.getTodoList().observeAsState()
    var isUpdating by remember { mutableStateOf(false) }
    var isInserting by remember { mutableStateOf(false) }
    var currentTodo by remember { mutableStateOf<TodoModel?>(null) }

    // Main Screen
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Button(
                onClick = {
                    isInserting = true
                    currentTodo = null
                }
            ) {
                Icon(
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 5.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Todo")
            }
        }
    ){
        if(list == null || list?.isEmpty()!!){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No Items Available"
                )
            }
        }
        else {
            LazyColumn (
                modifier = Modifier.padding(it)
            ){
                items(list ?: emptyList()){ item ->
                    TaskItem(
                        todo = item,
                        viewModel = viewModel,
                        onLongPress = {
                            isUpdating = true
                            currentTodo = item
                        },
                        onDismiss = { viewModel.deleteTodo(item) })
                }
            }
        }
    }

    if(isUpdating){
        Dialog(viewModel = viewModel, todo = currentTodo ) {
            isUpdating = it
        }
    }

    if(isInserting){
        Dialog(viewModel = viewModel, todo = currentTodo) {
            isInserting = it
        }
    }
}
