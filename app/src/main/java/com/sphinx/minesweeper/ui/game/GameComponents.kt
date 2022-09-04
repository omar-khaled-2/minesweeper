package com.sphinx.minesweeper.ui.game

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sphinx.minesweeper.R
import com.sphinx.minesweeper.Square
import com.sphinx.minesweeper.Visiablity
import java.util.*

@Composable
fun MineSweeperTopBar(
    remainingFlags:Int,
    time:Int,
    progress:Float,
    modifier: Modifier = Modifier
){


    Row(

        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {


        Surface(shape = MaterialTheme.shapes.medium,color = MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.BackgroundOpacity)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
                ){
                Icon(imageVector = Icons.Filled.Flag, contentDescription = null)
                Text(text = remainingFlags.toString())
            }
        }

        CircularProgressIndicator(progress = progress,strokeWidth = 15.dp)

        Surface(shape = MaterialTheme.shapes.medium,color = MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.BackgroundOpacity)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ){
                Icon(imageVector = Icons.Filled.Schedule, contentDescription = null)
                Text(text = time.toString())
            }
        }

    }

}


@ExperimentalFoundationApi
@Composable
fun Board(
    board:List<List<Square>>,
    show:(i:Int,j:Int) -> Unit,
    unHide:(i:Int,j:Int) -> Unit,
    hide:(i:Int,j:Int) -> Unit,

    modifier: Modifier = Modifier,


    ){



    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        for (row in board.indices) {
            if (row > 0) Divider()

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(2.dp)

            ) {
                for (column in board[row].indices) {
                    if (column > 0) Divider(modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp));

                    val square = board[row][column]


                    when(square.visiablity){
                        Visiablity.INVISIBLE -> Surface(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .combinedClickable (
                                    onClick = { show(row, column) },
                                    onLongClick = { hide(row,column)}

                                )
                                ,
                            color = if((row + column) % 2 == 0) MaterialTheme.colors.primary else MaterialTheme.colors.primaryVariant,
                            shape = MaterialTheme.shapes.small
                        ) {}

                        Visiablity.HIDDEN -> Surface(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .combinedClickable (
                                    onClick = {  },
                                    onLongClick = { unHide(row,column)}

                                )
                                ,

                            color = MaterialTheme.colors.secondary,
                            shape = MaterialTheme.shapes.small

                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(imageVector = Icons.Filled.Flag, contentDescription = "Flag")
                            }
                        }

                        Visiablity.VISIBILE -> Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                            ,
                            contentAlignment = Alignment.Center
                        ) {
                            if(square.isBomb) Image(painter = painterResource(id = R.drawable.ic_bomb), contentDescription = "bomb")
                            else if(square.nearBombs > 0) Text(text = square.nearBombs.toString(),fontWeight = FontWeight.Bold,fontSize = 18.sp)
                        }
                    }


                }
            }

        }
    }
}



