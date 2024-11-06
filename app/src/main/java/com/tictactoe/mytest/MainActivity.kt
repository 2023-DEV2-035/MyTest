package com.tictactoe.mytest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.tictactoe.mytest.data.GameViewState
import com.tictactoe.mytest.ui.game.GameScreen
import com.tictactoe.mytest.ui.game.GameViewModel
import com.tictactoe.mytest.ui.theme.Black
import com.tictactoe.mytest.ui.theme.MyTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<GameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Black
                ) {
                    val viewState =
                        viewModel.uiState.collectAsState(GameViewState(viewModel.getDefaultList()))
                    GameScreen(
                        viewState.value, onClick = { index, isFirstPlayer ->
                            viewModel.updateBoard(index, isFirstPlayer)
                        },
                        onRefresh = { viewModel.refresh() }
                    )
                }
            }
        }
    }
}

