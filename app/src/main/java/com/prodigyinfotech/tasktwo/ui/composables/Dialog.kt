package com.prodigyinfotech.tasktwo.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prodigyinfotech.tasktwo.ui.viewmodel.MainActivityViewModel
import com.prodigyinfotech.tasktwo.data.TodoModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialog(viewModel: MainActivityViewModel, todo: TodoModel?, onDismiss:(Boolean)->Unit){

    // State Variables
    var title by remember { if(todo == null ) mutableStateOf("") else mutableStateOf(todo.title) }
    var description by remember { if(todo == null) mutableStateOf("") else mutableStateOf(todo.description) }

    BasicAlertDialog(
        modifier = Modifier.wrapContentSize().background(MaterialTheme.colorScheme.background,RoundedCornerShape(15.dp)),
        onDismissRequest = { onDismiss(false) },
    ) {
        Column (
            modifier = Modifier.wrapContentSize().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = "Add Title",
                color = MaterialTheme.colorScheme.onBackground
                )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = title,
                onValueChange = {title = it})

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Add Description",
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = description,
                onValueChange = {description = it})

            Spacer(modifier = Modifier.height(30.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                Button(
                    onClick = {
                        if(todo == null){
                            val newTodo = TodoModel(title = title , description = description , isCompleted = false)
                            viewModel.insertTodo(newTodo)
                            onDismiss(false)
                        }
                        else{
                            val newTodo = todo.copy(title = title , description = description)
                            viewModel.updateTodo(newTodo)
                            onDismiss(false)
                        }
                    }
                ) {
                    Text(text = if(todo == null) "Add" else "Update" )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Button(
                    onClick = {
                        onDismiss(false)
                    }
                ) {
                    Text(text = "Cancel" )
                }
            }
        }
    }
}