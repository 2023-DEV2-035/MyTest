package com.tictactoe.mytest.data

data class GameViewState(
    val list: List<Status> = emptyList(),
    val winner: Winner = Winner.NOT_YET
)