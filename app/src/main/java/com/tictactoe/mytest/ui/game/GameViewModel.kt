package com.tictactoe.mytest.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tictactoe.mytest.data.GameViewState
import com.tictactoe.mytest.data.Status
import com.tictactoe.mytest.domain.GameUseCase
import com.tictactoe.mytest.domain.GetWinnerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameUseCase: GameUseCase,
    private val getWinnerUseCase: GetWinnerUseCase

) : ViewModel() {

    private val _uiState = MutableStateFlow(GameViewState(getDefaultList()))
    val uiState: StateFlow<GameViewState> = _uiState.asStateFlow()

    fun updateBoard(index: Int, isFirstPlayer: Boolean) {
        viewModelScope.launch {
            gameUseCase(
                GameUseCase.Params(
                    _uiState.value.list,
                    index,
                    isFirstPlayer
                )
            ).also { list ->
                _uiState.value = _uiState.value.copy(
                    list = list
                )
                checkWinner()
            }
        }
    }

    private fun checkWinner() {
        viewModelScope.launch {
            getWinnerUseCase(GetWinnerUseCase.Params(_uiState.value.list)).also { winner ->
                _uiState.value = _uiState.value.copy(
                    winner = winner
                )
            }
        }
    }

    fun refresh() {
        _uiState.value = GameViewState(getDefaultList())
    }

    fun getDefaultList(): List<Status> {
        return ArrayList<Status>(9).apply {
            repeat(9) { i ->
                add(i, Status.BLANK)
            }
        }.toList()
    }
}