package com.tictactoe.mytest

import com.tictactoe.mytest.data.Status

object MyTestFactory {

    fun getDefaultBoard() = ArrayList<Status>(9).apply {
        repeat(9) { i ->
            add(i, Status.BLANK)
        }
    }.toList()

    fun getGameBoard() = ArrayList<Status>(9).apply {
        repeat(9) { i ->
            add(i, Status.BLANK)
        }
        set(1, Status.O)
    }.toList()

    fun getWinnerPlayer1Horizontally() = ArrayList<Status>(getDefaultBoard()).apply {
        set(0, Status.X)
        set(1, Status.X)
        set(2, Status.X)
    }.toList()

    fun getWinnerPlayerVertically() = ArrayList<Status>(getDefaultBoard()).apply {
        set(0, Status.X)
        set(3, Status.X)
        set(6, Status.X)
    }.toList()
}