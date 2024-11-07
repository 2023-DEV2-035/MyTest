package com.tictactoe.mytest.ui.game

import app.cash.turbine.test
import com.tictactoe.mytest.MainDispatcherRule
import com.tictactoe.mytest.MyTestFactory.getDefaultBoard
import com.tictactoe.mytest.MyTestFactory.getGameBoard
import com.tictactoe.mytest.TicTacToeConstants.Winner
import com.tictactoe.mytest.domain.GameUseCase
import com.tictactoe.mytest.domain.GetWinnerUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var gameUseCase: GameUseCase

    @MockK
    lateinit var getWinnerUseCase: GetWinnerUseCase

    private lateinit var gameViewModel: GameViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        gameViewModel = GameViewModel(gameUseCase, getWinnerUseCase)
    }

    @Test
    fun `should refresh`() {
        gameViewModel.refresh()
        runBlocking {
            gameViewModel.uiState.test {
                assertEquals(awaitItem().list, getDefaultBoard())
            }
        }
    }

    @Test
    fun `should return a list with O at the position 1`() {
        coEvery { gameUseCase(any()) } returns getGameBoard()
        coEvery { getWinnerUseCase(any()) } returns Winner.NOT_YET
        runTest {
            gameViewModel.updateBoard(1, false)
            gameViewModel.uiState.test {
                val result =awaitItem()
                assertEquals(result.list, getGameBoard())
                assertEquals(result.winner, Winner.NOT_YET)
            }
        }
    }
}
