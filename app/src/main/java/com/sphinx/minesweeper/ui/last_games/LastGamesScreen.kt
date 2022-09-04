package com.sphinx.minesweeper.ui.last_games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@ExperimentalMaterialApi
@Composable
fun LastGamesScreen(
    lastGamesViewModel: LastGamesViewModel = viewModel(),
    onNavigateBack:() -> Unit
) {
    var alertClearing by remember {
        mutableStateOf(false)
    }

    if(alertClearing) AlertDialog(
        onDismissRequest = { alertClearing = false },
        title = {
            Text(text = "Confirm")
        },
        text = {
            Text(text = "Are you sure you want delele all games")
        },
        confirmButton = {
            Button(onClick = { lastGamesViewModel.clearGames() }) {
                Text(text = "clear")
            }
        }

    )
            Scaffold(
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(onClick = onNavigateBack) {
                                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                            }
                        },
                        title = {
                            Text(text = "Last Games")
                        },
                        actions = {
                            if(lastGamesViewModel.games.isNotEmpty()) IconButton(onClick = { alertClearing = true }) {
                                Icon(imageVector = Icons.Filled.Delete, contentDescription = "clear all")
                            }
                        }
                    )
                }
            ) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp),contentPadding = PaddingValues(10.dp),modifier = Modifier.fillMaxSize()){
                    items(items = lastGamesViewModel.games){
                        Surface(
                            elevation = 2.dp,
                            shape = MaterialTheme.shapes.medium,

                            ) {
                            ListItem(
                                text = {
                                    Text(text = it.difficulty,fontWeight = FontWeight.Bold)
                                },

                                trailing = {
                                    Text(text = it.duration.toString() + " S",style = MaterialTheme.typography.h5)
                                },
                                icon = {
                                    if(it.progress == 1f) Icon(imageVector = Icons.Filled.EmojiEvents, contentDescription = null)
                                    else CircularProgressIndicator(progress = it.progress)
                                },
                                modifier = Modifier.padding(vertical = 10.dp),
                                secondaryText = {
                                    Text(text = "${it.progress * 100} %")
                                }
                            )
                        }

                    }
                }
            }
        }
