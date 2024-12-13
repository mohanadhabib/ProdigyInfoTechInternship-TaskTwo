package com.prodigyinfotech.tasktwo.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxDefaults
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.prodigyinfotech.tasktwo.ui.viewmodel.MainActivityViewModel
import com.prodigyinfotech.tasktwo.data.TodoModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(todo: TodoModel, viewModel: MainActivityViewModel, onLongPress:(Offset)->Unit, onDismiss:()->Unit){
    // Current Context
    val context = LocalContext.current
    // State variable
    var isCompleted by remember { mutableStateOf(todo.isCompleted) }
    // The Swipe State
    val state = SwipeToDismissBoxState(
        initialValue = SwipeToDismissBoxValue.Settled,
        positionalThreshold = SwipeToDismissBoxDefaults.positionalThreshold,
        density = Density(context),
        confirmValueChange = {
            when (it) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    onDismiss()
                    true
                }
                SwipeToDismissBoxValue.EndToStart -> {
                    onDismiss()
                    true
                }
                else -> {
                    false
                }
            }
        },
    )

    // The main Composable
    SwipeToDismissBox(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = onLongPress
                )
            },
        state = state,
        backgroundContent = {}
    ) {
        Card (
            shape = RoundedCornerShape(9.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            border = BorderStroke(width = 2.dp , color = MaterialTheme.colorScheme.background)
        ){
            ConstraintLayout (
                modifier = Modifier.fillMaxWidth()
            ){
                val (title,description,checkBox) = createRefs()

                Text(
                    modifier = Modifier.constrainAs(title){
                        top.linkTo(parent.top , margin = 10.dp)
                        start.linkTo(parent.start , margin = 10.dp)
                    },
                    text = todo.title
                )
                Text(
                    modifier = Modifier.constrainAs(description){
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                        start.linkTo(title.start)
                    },
                    text = todo.description
                )
                Checkbox(
                    modifier = Modifier.constrainAs(checkBox){
                        top.linkTo(title.bottom)
                        bottom.linkTo(description.top)
                        end.linkTo(parent.end , margin = 10.dp)
                    },
                    checked = isCompleted,
                    onCheckedChange = {
                        isCompleted = it
                        val newTodo = todo.copy(isCompleted = it)
                        viewModel.updateTodo(newTodo)
                    }
                )
            }
        }
    }
}
