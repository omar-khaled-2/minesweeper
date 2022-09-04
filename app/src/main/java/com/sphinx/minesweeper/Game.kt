package com.sphinx.minesweeper

import android.util.Log
import com.sphinx.minesweeper.ui.game.Observer
import java.util.*


open class Observable{

    private val observers = mutableListOf<Observer>();

    fun addObserver(observer: Observer){
        observers.add(observer)
    }

    fun deleteObserver(observer: Observer){
        observers.remove(observer);
    }

    fun notifyObservers(){
        for(observer in observers){
            observer.update();
        }
    }
}

enum class Visiablity{
    INVISIBLE,
    VISIBILE,
    HIDDEN,
}

data class Square(
    var visiablity: Visiablity = Visiablity.INVISIBLE,

){
    var isBomb:Boolean = false
    var nearBombs:Int = 0
}


//class Bomb:Square(){
//    override val isBomb = true;
//
//    companion object{
//        private val instance = Bomb()
//        operator fun invoke(): Bomb {
//            Log.i("bomb","invoke")
//
//            return instance
//        }
//    }
//}
//
//
//class NonBomb:Square(){
//
//    override val isBomb = false
//}




abstract class MinesweeperGame:Observable{

    val board: List<MutableList<Square>>

    private var isFirstShow:Boolean;









    private val mines:Int



    constructor(rows:Int,columns:Int,mines:Int){
        this.mines = mines
        this.board = List(rows) { MutableList(columns) { Square() } }
        this.isFirstShow = true

    }









    fun hide(i:Int, j:Int){
        board[i][j].visiablity = Visiablity.HIDDEN
        notifyObservers()
    }


    fun unHide(i:Int,j:Int){
        board[i][j].visiablity = Visiablity.INVISIBLE
        notifyObservers()
    }



    val remainingFlags:Int
        get(){
            var result = mines
            for(i in board.indices){
                for(square in board[i]){
                   if(square.visiablity == Visiablity.HIDDEN) result--
                }
            }
            return result
        }



    val isLost:Boolean
        get(){
            for(row in board.indices){
                for(square in board[row]) {
                    if (square.visiablity == Visiablity.VISIBILE && square.isBomb) return true
                }
            }
            return false
        }



    val progress:Float
        get() {
            var showings = 0
            for(i in board.indices){
                for(square in board[i]){
                    if(square.visiablity == Visiablity.VISIBILE) showings++
                }
            }
            return showings.toFloat() / (board.size * board[0].size  - mines).toFloat()

        }





    private fun dfs(row: Int, column: Int){
        if(board[row][column].visiablity != Visiablity.INVISIBLE) return
        board[row][column].visiablity = Visiablity.VISIBILE

        if(board[row][column].isBomb || board[row][column].nearBombs > 0) return


        if(row < board.size - 1) dfs(row + 1,column)
        if(row > 0) dfs(row - 1,column)
        if(column < board[row].size - 1) dfs(row,column + 1)
        if(column > 0) dfs(row,column - 1)
        if(row < board.size - 1 && column < board[row].size - 1) dfs(row + 1,column + 1)
        if(row > 0 && column < board[row].size - 1) dfs(row - 1,column + 1)
        if(row < board.size - 1 && column > 0) dfs(row + 1,column - 1)
        if(row > 0 && column > 0) dfs(row - 1,column - 1 )
    }

    fun show(i:Int,j:Int){
        Log.i("show","$i $j")
        if(isFirstShow){
            BoardUtility.fillMines(i,j,board,mines);
            isFirstShow = false
        }
        dfs(i,j)
        notifyObservers()

    }


}


class EasyMinesweeperGame() : MinesweeperGame(12, 6, 10)
class MediumMinesweeperGame() : MinesweeperGame(20, 9, 35)
class HardMinesweeperGame() : MinesweeperGame(28, 13, 75)