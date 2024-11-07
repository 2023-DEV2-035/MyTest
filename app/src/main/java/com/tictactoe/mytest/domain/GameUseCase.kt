package com.tictactoe.mytest.domain

import com.tictactoe.mytest.TicTacToeConstants.Status
import javax.inject.Inject

class GameUseCase @Inject constructor() {
    operator fun invoke(params: Params): List<Status> {
        val list = ArrayList(params.list)
        list[params.position] = if (params.isFirstPlayer) Status.X else Status.O
        return list.toList()
    }

    data class Params(val list: List<Status>, val position: Int, val isFirstPlayer: Boolean = true)
}
