package com.tictactoe.mytest.domain

import com.tictactoe.mytest.MyTestFactory.getWinnerPlayer1Horizontally
import com.tictactoe.mytest.MyTestFactory.getWinnerPlayerVertically
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
    fun `should return Player1 is winner horizontally`() {
        runBlocking {
            getWinnerUseCase(GetWinnerUseCase.Params(getWinnerPlayer1Horizontally())).also {
                assertEquals(it, Winner.PLAYER1)
            }
        }
    }
    @Test
    fun `should return Player1 is winner vertically`() {
        runBlocking {
            getWinnerUseCase(GetWinnerUseCase.Params(getWinnerPlayerVertically())).also {
                assertEquals(it, Winner.PLAYER1)
            }
        }
    }
}
