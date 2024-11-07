package com.tictactoe.mytest.domain

import com.tictactoe.mytest.MyTestFactory.getDefaultBoard
import com.tictactoe.mytest.TicTacToeConstants.Status
import io.mockk.MockKAnnotations
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GameUseCaseTest {

    private lateinit var gameUseCase: GameUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        gameUseCase = GameUseCase()
    }

    @Test
    fun `should return a list with O at the position 1`() {
        runBlocking {
            gameUseCase(GameUseCase.Params(getDefaultBoard(), 1, false)).also {
                assertEquals(it[1], Status.O)
            }
        }
    }

    @Test
    fun `should return a list with X at the position 1`() {
        runBlocking {
            gameUseCase(GameUseCase.Params(getDefaultBoard(), 1, true)).also {
                assertEquals(it[1], Status.X)
            }
        }
    }
}
