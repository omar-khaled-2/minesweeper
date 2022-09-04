package com.sphinx.minesweeper

import android.app.Application
import android.util.Log
import com.sphinx.minesweeper.data.room.MinesweeperDatabase
import com.sphinx.minesweeper.data.room.MinesweeperRepository

class MinesweeperApplication:Application() {
    private val database by lazy {
        MinesweeperDatabase.create(this.applicationContext)
    }

    val minesweeperRepository by lazy {
        MinesweeperRepository(database.minesweeperDao())
    }
}