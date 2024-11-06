package com.tictactoe.mytest.domain

import com.tictactoe.mytest.data.Status
import com.tictactoe.mytest.data.Winner
import javax.inject.Inject

class GetWinnerUseCase @Inject constructor() {
    suspend operator fun invoke(params: Params): Winner {
        for (i in 0 until 3) {
            when {
                params.list[i * 3] == Status.X && params.list[(i * 3) + 1] == Status.X && params.list[(i * 3) + 2] == Status.X ||
                        params.list[i] == Status.X && params.list[i + 3] == Status.X && params.list[i + 6] == Status.X -> {
                    return Winner.PLAYER1
                }

                params.list[i * 3] == Status.O && params.list[(i * 3) + 1] == Status.O && params.list[(i * 3) + 2] == Status.O ||
                        params.list[i] == Status.O && params.list[i + 3] == Status.O && params.list[i + 6] == Status.O -> {
                    return Winner.PLAYER2
                }
            }
        }

        return Winner.NONE
    }

    data class Params(val list: List<Status>)
}

