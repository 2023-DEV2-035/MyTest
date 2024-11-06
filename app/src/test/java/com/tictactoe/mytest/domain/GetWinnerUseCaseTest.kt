package com.tictactoe.mytest.domain

import com.tictactoe.mytest.MyTestFactory.getDefaultBoard
import com.tictactoe.mytest.MyTestFactory.getWinnerPlayer1
import com.tictactoe.mytest.data.Status
import com.tictactoe.mytest.data.Winner
import io.mockk.MockKAnnotations
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetWinnerUseCaseTest {

    private lateinit var getWinnerUseCase: GetWinnerUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getWinnerUseCase = GetWinnerUseCase()
    }

    @Test
    fun `should return Player1 is winner Horizontally`() {
        runBlocking {
            getWinnerUseCase(GetWinnerUseCase.Params(getWinnerPlayer1())).also {
                assertEquals(it, Winner.PLAYER1)
            }
        }
    }
}
