package com.tictactoe.mytest.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.tictactoe.mytest.TicTacToeConstants.ROW_SIZE
import com.tictactoe.mytest.data.GameViewState
import com.tictactoe.mytest.data.Status
import com.tictactoe.mytest.data.Winner
import com.tictactoe.mytest.ui.theme.Blue
import com.tictactoe.mytest.ui.theme.Pink80
import com.tictactoe.mytest.ui.theme.Red
import com.tictactoe.mytest.ui.theme.transparent

@Composable
fun GameScreen(
    viewState: GameViewState,
    onClick: (position: Int, isFirstPlayer: Boolean) -> Unit,
    onRefresh: () -> Unit
) {
    val isFirstPlayer = remember { mutableStateOf(true) }
    val shouldShowDialog = remember { mutableStateOf(false) }

    if (viewState.winner != Winner.NOT_YET) {
        shouldShowDialog.value = true
    }
    if (shouldShowDialog.value) {
        ShowAlert(shouldShowDialog = shouldShowDialog, viewState.winner, isFirstPlayer, onRefresh)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        LazyVerticalGrid(
            modifier = Modifier.padding(16.dp),
            columns = GridCells.Fixed(ROW_SIZE),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(viewState.list.size) { item ->
                CaseItem(viewState.list[item], onClick, item, isFirstPlayer)
            }
        }
        OutlinedButton(onClick = {
            isFirstPlayer.value = true
            onRefresh()
        }) {
            Text("Start new match")
        }
    }
}

@Composable
fun ShowAlert(
    shouldShowDialog: MutableState<Boolean>, winner: Winner, isFirstPlayer: MutableState<Boolean>,
    onRefresh: () -> Unit
) {
    val properties = DialogProperties(
        dismissOnClickOutside = false,
        dismissOnBackPress = false
    )
    AlertDialog(
        onDismissRequest = {
            shouldShowDialog.value = false
        },
        properties = properties,
        title = {
            Text(
                text = when (winner) {
                    Winner.PLAYER1 -> "X won "
                    Winner.PLAYER2 -> "Y won"
                    Winner.NOT_YET -> ""
                    Winner.NONE -> "It's a Draw!"
                }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    isFirstPlayer.value = true
                    onRefresh()
                    shouldShowDialog.value = false
                }
            ) {
                Text(
                    text = "Start new match",
                )
            }
        }
    )
}

@Composable
fun CaseItem(
    status: Status,
    onClick: (position: Int, isFirstPlayer: Boolean) -> Unit,
    item: Int,
    isFirstPlayer: MutableState<Boolean>
) {
    Row(
        Modifier
            .background(Pink80)
            .height(50.dp)
            .clickable {
                if (status == Status.BLANK) {
                    onClick(item, isFirstPlayer.value)
                    isFirstPlayer.value = !isFirstPlayer.value
                }
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    )
    {
        Text(
            text = when (status) {
                Status.BLANK -> ""
                Status.X -> "X"
                Status.O -> "O"
            },
            modifier = Modifier.defaultMinSize(),
            fontSize = 30.sp,
            color = when (status) {
                Status.BLANK -> transparent
                Status.X -> Blue
                Status.O -> Red
            },
            fontWeight = FontWeight.Bold
        )
    }

}

@Preview
@Composable
private fun GameScreenPreview() {

    GameScreen(GameViewState(
        listOf(
            Status.X,
            Status.X,
            Status.O,
            Status.X,
            Status.X,
            Status.O,
            Status.X,
            Status.X,
            Status.O
        )
    ), onClick = { _, _ -> }, onRefresh = {})
}
