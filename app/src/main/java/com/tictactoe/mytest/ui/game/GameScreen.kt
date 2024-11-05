package com.tictactoe.mytest.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tictactoe.mytest.data.Status
import com.tictactoe.mytest.ui.theme.Blue
import com.tictactoe.mytest.ui.theme.Pink80
import com.tictactoe.mytest.ui.theme.Red
import com.tictactoe.mytest.ui.theme.transparent


@Composable
fun GameScreen(
    list: List<Status>,
    onClick: (position: Int) -> Unit

) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(list.size) { item ->
            CaseItem(list[item], onClick, item)
        }
    }
}

@Composable
fun CaseItem(status: Status, onClick: (position: Int) -> Unit, item: Int) {
    Row(
        Modifier
            .background(Pink80)
            .clickable {
                onClick(item)
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

    GameScreen(
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
        ),
        onClick = {}
    )


}