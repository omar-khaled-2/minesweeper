package com.sphinx.minesweeper.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(
    isDark:Boolean,
    onChangeIsDark:(Boolean) -> Unit,
    onNavigateBack:() -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text(text = "Settings")
                }
            )
        }
 
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            ListItem(
                text = {
                    Text(text = "Dark Mode")
                },
                trailing = {
                    Switch(checked = isDark, onCheckedChange = onChangeIsDark)
                }
            )
        }
    }
}