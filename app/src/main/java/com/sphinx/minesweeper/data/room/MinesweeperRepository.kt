package com.sphinx.minesweeper.data.room

class MinesweeperRepository(private val minesweeperDao: MinesweeperDao) {
    suspend fun games() = minesweeperDao.games()

    suspend fun clearGames() = minesweeperDao.clearGames()

    suspend fun insertGame(game: Game) = minesweeperDao.insertGame(game)
//    suspend fun hightscores() = minesweeperDao.highscores()
}