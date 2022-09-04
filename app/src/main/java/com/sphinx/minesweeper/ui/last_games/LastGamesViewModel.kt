package com.sphinx.minesweeper.ui.last_games

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sphinx.minesweeper.MinesweeperApplication
import com.sphinx.minesweeper.data.room.Game
import kotlinx.coroutines.launch

class LastGamesViewModel(application: Application):AndroidViewModel(application) {

    var games by mutableStateOf<List<Game>>(emptyList())
        private set

    init{
        getLastGames()
    }



    private fun getLastGames(){
        viewModelScope.launch {
            games = getApplication<MinesweeperApplication>().minesweeperRepository.games()
        }
    }

    fun clearGames(){
        viewModelScope.launch {
            getApplication<MinesweeperApplication>().minesweeperRepository.clearGames()
            games = emptyList()
        }
    }
}