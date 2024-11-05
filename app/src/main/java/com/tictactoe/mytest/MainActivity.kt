package com.tictactoe.mytest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tictactoe.mytest.data.Status
import com.tictactoe.mytest.ui.game.GameScreen
import com.tictactoe.mytest.ui.theme.MyTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTestTheme {
                    GameScreen(
                        listOf(
                            Status.X,
                            Status.X,
                            Status.O,
                            Status.X,
                            Status.X,
                            Status.O,
                            Status.X,
                            Status.X,
                            Status.O
                        ), onClick = viewModel
                    )

            }
        }
    }
}

