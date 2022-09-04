package com.sphinx.minesweeper.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val difficulty:String,
    val duration:Int,
    val progress:Float,
)
