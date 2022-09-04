package com.sphinx.minesweeper.ui.difficulty

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sphinx.minesweeper.Difficult


@Composable
fun DifficultyScreen(
    onSelectDifficulty:(String) -> Unit,
    onNavigateBack:() -> Unit,
    difficultOptions:List<String> = listOf(Difficult.EASY, Difficult.MEDIUM, Difficult.HARD)
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                },
                title = {
                    Text(text = "Select difficulty")
                }
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp,alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
        ) {
            for(difficultOption in difficultOptions){

                Button(
                    onClick = { onSelectDifficulty(difficultOption) },
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Text(text = difficultOption)

                }
            }
        }
    }
}