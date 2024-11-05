package com.tictactoe.mytest.domain


class GameUseCase {

    suspend operator fun invoke(params: Params): Boolean {

        return false

    }

    data class Params(val position: Int)
}