package com.tictactoe.mytest.model

import com.tictactoe.mytest.TicTacToeConstants.Winner
import com.tictactoe.mytest.TicTacToeConstants.Status

data class GameViewState(
    val list: List<Status> = emptyList(),
    val winner: Winner = Winner.NOT_YET
)
