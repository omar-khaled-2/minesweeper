package com.sphinx.minesweeper


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.sphinx.minesweeper.ui.difficulty.DifficultyScreen
import com.sphinx.minesweeper.ui.game.GameScreen
import com.sphinx.minesweeper.ui.last_games.LastGamesScreen
import com.sphinx.minesweeper.ui.menu.MenuScreen
import com.sphinx.minesweeper.ui.settings.SettingsScreen


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MinesweeperNavHost(
    navController: NavHostController = rememberNavController(),
    isDark:Boolean,
    onChangeIsDark:(Boolean) -> Unit
){
    NavHost(navController = navController,startDestination = "menu"){
        composable("menu"){
            MenuScreen(onNavigate = {navController.navigate(it)})
        }
        composable("games"){
            LastGamesScreen(
                onNavigateBack = {navController.navigateUp()}
            )
        }
        composable("settings"){
            SettingsScreen(
                isDark = isDark,
                onChangeIsDark = onChangeIsDark,
                onNavigateBack = {navController.navigateUp()}
            )
        }
        composable("difficulty"){
            DifficultyScreen(
                onSelectDifficulty = { navController.navigate("game/$it") },
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable(
            "game/{difficulty}",
            arguments = listOf(
                navArgument("difficulty") { type = NavType.StringType }
            )
        ){
            GameScreen(
                onEnd = {
                    navController.popBackStack("menu",false)
                }
            )
        }
    }
}