package com.sphinx.minesweeper.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Game::class],version = 1,exportSchema = false)
abstract class MinesweeperDatabase: RoomDatabase() {
    abstract fun minesweeperDao():MinesweeperDao

    companion object{
        fun create(context: Context) = Room.databaseBuilder(
                context,
                MinesweeperDatabase::class.java,
            "minesweeper_database"
            ).build()
    }
}