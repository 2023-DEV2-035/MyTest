package com.tictactoe.mytest.domain

import com.tictactoe.mytest.data.Status
import javax.inject.Inject


class GameUseCase @Inject constructor() {
    suspend operator fun invoke(params: Params): List<Status> {

        return emptyList()
    }
    data class Params(val list: List<Status>, val position: Int)
}
