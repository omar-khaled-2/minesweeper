package com.sphinx.minesweeper

import android.util.Log
import java.util.*


class BoardUtility() {



    companion object{
        fun fillMines(startRow:Int,startColumn:Int,board:List<List<Square>>,mines:Int){
            val random = WeightRandom<Pair<Int,Int>>();




            val queue:Queue<Pair<Int,Int>> = LinkedList()



            val isAdded = List(board.size) { MutableList(board[it].size) { false } }
            queue.add(startRow to startColumn)

            var weight = 0

            while (queue.isNotEmpty()){
                var size = queue.size;

                while(size > 0){


                    val (i,j) = queue.remove();

                    size -= 1;

                    if(isAdded[i][j]) continue;

                    isAdded[i][j] = true;


                    Log.i("sss",weight.toString())

                    random.add(i to j,weight)




                    if(i + 1 < board.size) queue.add(i + 1 to j);
                    if(i > 0) queue.add(i - 1 to j);
                    if(j + 1 < board[i].size) queue.add(i to j + 1);
                    if(j > 0) queue.add(i  to j - 1);
                    if(i + 1 < board.size && j + 1 < board[i].size) queue.add(i + 1 to j + 1);
                    if(i > 0 && j > 0)  queue.add(i - 1 to j - 1);
                    if(i > 0 && j + 1 < board[i].size) queue.add(i - 1 to j + 1);
                    if(i + 1 < board.size && j > 0) queue.add(i + 1 to j - 1);


                }
                weight = Math.min(weight + 1,5)
            }





            var remainingMines = mines


            while (remainingMines > 0){
                val (i,j) = random.get()

                Log.i("sss",(i to j).toString())
                board[i][j].isBomb = true;
                if(i + 1 < board.size) board[i + 1][j].nearBombs++
                if(i > 0) board[i - 1][j].nearBombs++
                if(j + 1 < board[i].size) board[i][j + 1].nearBombs++
                if(j > 0) board[i][j - 1].nearBombs++
                if(i + 1 < board.size && j + 1 < board[i].size ) board[i + 1][j + 1].nearBombs++
                if(i > 0 && j > 0) board[i - 1][j - 1].nearBombs++
                if(i > 0 && j + 1 < board[i].size) board[i - 1][j + 1].nearBombs++
                if(i + 1 < board.size && j > 0) board[i + 1][j - 1].nearBombs++
                remainingMines--
            }
            Log.i("parin","2")


        }

    }


}