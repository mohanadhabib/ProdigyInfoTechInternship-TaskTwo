package com.prodigyinfotech.tasktwo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prodigyinfotech.tasktwo.ui.screens.MainScreen
import com.prodigyinfotech.tasktwo.ui.theme.TodoAppTheme
import com.prodigyinfotech.tasktwo.ui.viewmodel.MainActivityViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                val viewModel = viewModel(modelClass = MainActivityViewModel::class)
                MainScreen(viewModel = viewModel)
            }
        }
    }
}