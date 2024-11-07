package com.tictactoe.mytest.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.tictactoe.mytest.R
import com.tictactoe.mytest.TicTacToeConstants.ROW_SIZE
import com.tictactoe.mytest.model.GameViewState
import com.tictactoe.mytest.TicTacToeConstants.Winner
import com.tictactoe.mytest.TicTacToeConstants.Status
import com.tictactoe.mytest.ui.theme.Blue
import com.tictactoe.mytest.ui.theme.Gray
import com.tictactoe.mytest.ui.theme.Red
import com.tictactoe.mytest.ui.theme.Transparent

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
            modifier = Modifier.padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            columns = GridCells.Fixed(ROW_SIZE),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewState.list.size) { item ->
                CaseItem(viewState.list[item], onClick, item, isFirstPlayer)
            }
        }
        OutlinedButton(
            modifier = Modifier.padding(top = 32.dp),
            onClick = {
                isFirstPlayer.value = true
                onRefresh()
            }) {
            Text(text = stringResource(R.string.start_new_match))
        }
    }
}

@Composable
fun ShowAlert(
    shouldShowDialog: MutableState<Boolean>,
    winner: Winner,
    isFirstPlayer: MutableState<Boolean>,
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
                    Winner.PLAYER1 -> stringResource(R.string.x_won)
                    Winner.PLAYER2 -> stringResource(R.string.o_won)
                    Winner.NOT_YET -> ""
                    Winner.NONE -> stringResource(R.string.it_is_a_draw)
                }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    isFirstPlayer.value = true
                    onRefresh()
                    shouldShowDialog.value = false
                }) {
                Text(
                    text = stringResource(R.string.start_new_match),
                )
            }
        })
}

@Composable
fun CaseItem(
    status: Status,
    onClick: (position: Int, isFirstPlayer: Boolean) -> Unit,
    item: Int,
    isFirstPlayer: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .background(Gray)
            .height(50.dp)
            .clickable {
                if (status == Status.BLANK) {
                    onClick(item, isFirstPlayer.value)
                    isFirstPlayer.value = !isFirstPlayer.value
                }
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = when (status) {
                Status.BLANK -> ""
                Status.X -> stringResource(R.string.x_play)
                Status.O -> stringResource(R.string.o_play)
            },
            fontSize = 30.sp,
            color = when (status) {
                Status.BLANK -> Transparent
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
    ),
        onClick = { _, _ -> },
        onRefresh = {})
}
