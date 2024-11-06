package com.tictactoe.mytest

import com.tictactoe.mytest.data.Status
import io.mockk.InternalPlatformDsl.toArray

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

    fun getWinnerPlayer1() = ArrayList<Status>(getDefaultBoard()).apply {
        set(0, Status.X)
        set(1, Status.X)
        set(2, Status.X)
    }.toList()
}
