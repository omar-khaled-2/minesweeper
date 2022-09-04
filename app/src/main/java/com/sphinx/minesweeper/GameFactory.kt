package com.sphinx.minesweeper


object Difficult{
    const val EASY = "Easy"
    const val MEDIUM = "Medium"
    const val HARD = "Hard"
}



class MinesweeperGameFactory{
    fun create(difficult:String):MinesweeperGame{
         return when(difficult){
            Difficult.EASY -> EasyMinesweeperGame()
            Difficult.MEDIUM -> MediumMinesweeperGame()
            Difficult.HARD -> HardMinesweeperGame()
            else -> throw UnsupportedOperationException()
        }
    }
}