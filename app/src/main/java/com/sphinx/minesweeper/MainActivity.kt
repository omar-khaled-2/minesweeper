package com.sphinx.minesweeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sphinx.minesweeper.ui.game.GameViewModel
import com.sphinx.minesweeper.ui.theme.MinesweeperTheme



class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinesweeperTheme(darkTheme = viewModel.isDark) {
                Surface(color = MaterialTheme.colors.background) {
                    App(isDark = viewModel.isDark, onChangeIsDark = {viewModel.onIsDarkChange(it)})
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun App(
    isDark:Boolean,
    onChangeIsDark:(Boolean) -> Unit
){
    val systemUiController = rememberSystemUiController()
    val systemColor = MaterialTheme.colors.primary
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = systemColor,
            darkIcons = isDark
        )
    }
    MinesweeperNavHost(
        isDark = isDark,
        onChangeIsDark = onChangeIsDark
    )
}