package com.tictactoe.mytest.domain

import com.tictactoe.mytest.data.Status
import com.tictactoe.mytest.data.Winner
import javax.inject.Inject

class GetWinnerUseCase @Inject constructor() {
    suspend operator fun invoke(params: Params): Winner {
       return Winner.NOT_YET
    }

    data class Params(val list: List<Status>)
}

