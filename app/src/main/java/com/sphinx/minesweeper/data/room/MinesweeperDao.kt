package com.sphinx.minesweeper.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MinesweeperDao {
    @Query("SELECT * FROM game_table ORDER BY id DESC")
    suspend fun games():List<Game>


    @Insert
    suspend fun insertGame(game:Game)

    @Query("DELETE FROM game_table")
    suspend fun clearGames()

//    @Query("SELECT difficulty,MIN(duration) as duration FROM game_table WHERE progress == 1 GROUP BY difficulty")
//    suspend fun highscores()
}