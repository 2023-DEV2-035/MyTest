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
}

