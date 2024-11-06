package com.tictactoe.mytest.domain

import com.tictactoe.mytest.TicTacToeConstants.EIGHT
import com.tictactoe.mytest.TicTacToeConstants.FOUR
import com.tictactoe.mytest.TicTacToeConstants.ONE
import com.tictactoe.mytest.TicTacToeConstants.SIX
import com.tictactoe.mytest.TicTacToeConstants.THREE
import com.tictactoe.mytest.TicTacToeConstants.TWO
import com.tictactoe.mytest.TicTacToeConstants.ZERO
import com.tictactoe.mytest.data.Status
import com.tictactoe.mytest.data.Winner
import javax.inject.Inject

class GetWinnerUseCase @Inject constructor() {
    suspend operator fun invoke(params: Params): Winner {
        val winners = listOf(
            checkWinnerHorizontallyAndVertically(params),
            checkWinnerDiagonally(params)
        )
        val notYet =
            winners.all { it == Winner.NONE } && params.list.find { it == Status.BLANK } != null
        return when {
            notYet -> Winner.NOT_YET
            Winner.PLAYER1 in winners -> Winner.PLAYER1
            Winner.PLAYER2 in winners -> Winner.PLAYER2
            else -> Winner.NONE
        }
    }

    private fun checkWinnerDiagonally(params: Params): Winner {
        when {
            params.list[ZERO] == Status.X && params.list[FOUR] == Status.X && params.list[EIGHT] == Status.X -> {
                return Winner.PLAYER1
            }

            params.list[TWO] == Status.X && params.list[FOUR] == Status.X && params.list[SIX] == Status.X -> {
                return Winner.PLAYER1
            }

            params.list[ZERO] == Status.O && params.list[FOUR] == Status.O && params.list[EIGHT] == Status.O -> {
                return Winner.PLAYER2
            }

            params.list[TWO] == Status.O && params.list[FOUR] == Status.O && params.list[SIX] == Status.O -> {
                return Winner.PLAYER2
            }
        }
        return Winner.NONE
    }

    private fun checkWinnerHorizontallyAndVertically(params: Params): Winner {
        for (i in ZERO until THREE) {
            when {
                params.list[i * THREE] == Status.X &&
                        params.list[i * THREE + ONE] == Status.X &&
                        params.list[i * THREE + TWO] == Status.X ||
                        params.list[i] == Status.X &&
                        params.list[i + THREE] == Status.X &&
                        params.list[i + SIX] == Status.X -> {
                    return Winner.PLAYER1
                }

                params.list[i * THREE] == Status.O &&
                        params.list[i * THREE + ONE] == Status.O &&
                        params.list[i * THREE + TWO] == Status.O ||
                        params.list[i] == Status.O &&
                        params.list[i + THREE] == Status.O &&
                        params.list[i + SIX] == Status.O -> {
                    return Winner.PLAYER2
                }
            }
        }
        return Winner.NONE
    }

    data class Params(val list: List<Status>)
}

