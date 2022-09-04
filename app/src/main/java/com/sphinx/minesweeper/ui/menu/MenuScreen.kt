package com.sphinx.minesweeper.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sphinx.minesweeper.R


class ButtonLink(
    val title:String,
    val route:String
)

val buttons = listOf(
    ButtonLink("Start","difficulty"),
    ButtonLink("Settings","settings"),
    ButtonLink("Last Games","games"),
)

@Composable
fun MenuScreen(
    onNavigate:(String) -> Unit
){
    Column(

        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                modifier = Modifier.size(250.dp),

            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp,alignment = Alignment.CenterVertically),
        ) {
            buttons.forEach {
                Button(
                    onClick = { onNavigate(it.route) },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = it.title)
                }
            }
        }

    }
}