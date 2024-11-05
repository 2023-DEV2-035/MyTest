package com.tictactoe.mytest.ui.game

import androidx.lifecycle.ViewModel
import com.tictactoe.mytest.domain.GameUseCase
import javax.inject.Inject

class GameViewModel @Inject constructor(
    private val gameUseCase: GameUseCase
) :ViewModel() {



}