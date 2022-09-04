package com.sphinx.minesweeper.ui.game


import android.app.Application
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sphinx.minesweeper.*
import com.sphinx.minesweeper.data.room.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.scheduleAtFixedRate


interface Alert{
    val title:String
    val text:String
    val dismissText:String
    val confirmText:String
    fun confirm()
    fun dismiss()
}


interface Observer{
    fun update();
}








class GameViewModel(application: Application, savedStateHandle: SavedStateHandle):AndroidViewModel(application),Observer{


    private val difficulty = savedStateHandle.get<String>("difficulty")!!


    private val minesweeperGameFactory = MinesweeperGameFactory()
    var time by mutableStateOf(0)
        private set


    private var game = minesweeperGameFactory.create(
            difficulty
        )








    var alert:Alert? by mutableStateOf(null)
        private set


    var isEnd by mutableStateOf(false)
        private set



    private val timer = Timer()

    private var timerTask:TimerTask? = null




    private val winningAlert by lazy { object : Alert {
        override val title = "Winner"
        override val text = "Good Job, Do you wanna play again"
        override val dismissText = "Leave"
        override val confirmText ="Play again"
        override fun confirm() = playAgain()
        override fun dismiss() = end()
    } }


    private val losingAlert by lazy { object : Alert {
        override val title = "Loser"
        override val text
            get() = if(difficulty == Difficult.EASY) "Really?! ,dude it is Easy mode ,play again?" else "Do you wanna try again?"
        override val dismissText = "Leave"
        override val confirmText ="Play again"
        override fun confirm() = playAgain()
        override fun dismiss() = end()


    }  }


    private val quitingAlert by lazy {
        object : Alert {
            override val title = "Quit"
            override val text
                get() = "are you sure? ,you have done ${(progress * 100).toInt()}%"
            override val dismissText = "continue"
            override val confirmText ="Leave"
            override fun confirm() {
                end()
            }
            override fun dismiss(){
                alert = null
            }


        }
    }










    init {
        observeGame()
//        startTimer()
    }






    private fun startTimer(){
        time = 0
        resumeTime()
    }


    fun pauseTimer(){
        timerTask?.cancel()
        timerTask = null
    }

    fun resumeTime(){
//        if(timerTask == null)  timerTask = timer.scheduleAtFixedRate( 1000,1000) { time++ }
    }


    var board by mutableStateOf<List<List<Square>>>(game.board, neverEqualPolicy())



    var progress by mutableStateOf(0f)
        private set


    var remainingFlags by mutableStateOf(0)
        private set


    fun show(i:Int,j:Int) = game.show(i,j)


    private fun onLose() {
        viewModelScope.launch(Dispatchers.IO) {
            alert = losingAlert
            pauseTimer()
            getApplication<MinesweeperApplication>().minesweeperRepository.insertGame(
                Game(
                    0,
                    difficulty,
                    time,
                    progress
                )
            )
        }

    }



    private fun onWin() {
        viewModelScope.launch(Dispatchers.IO) {
            alert = winningAlert
            pauseTimer()
            getApplication<MinesweeperApplication>().minesweeperRepository.insertGame(
                Game(
                    0,
                    difficulty,
                    time,
                    progress
                )
            )
        }
    }


    fun end(){
        alert = null
        isEnd = true
    }

    fun hide(i:Int,j:Int) = game.hide(i,j)

    fun unHide(i:Int,j:Int)=  game.unHide(i,j)


    private fun observeGame(){
        game.addObserver(this)
    }





    fun playAgain(){
        alert = null
        game = minesweeperGameFactory.create(difficulty)
        reset()
        observeGame();
        startTimer()
    }

    private fun reset(){
        board = game.board;
        progress = 0f;
        remainingFlags = 0;
    }

    fun onQuit(){
        alert = quitingAlert
    }

    override fun update() {
        board = game.board;
        progress = game.progress;
        remainingFlags = game.remainingFlags;
        if(progress == 1f) onWin();
        else if(game.isLost) onLose()

    }
}
