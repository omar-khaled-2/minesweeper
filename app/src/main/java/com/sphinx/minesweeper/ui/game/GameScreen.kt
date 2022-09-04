package com.sphinx.minesweeper.ui.game

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalFoundationApi
@Composable
fun GameScreen(
    viewModel:GameViewModel = viewModel(),
    onEnd:() -> Unit,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
){




    BackHandler(true) {
        viewModel.onQuit()
    }
    if(viewModel.isEnd) onEnd()

    DisposableEffect(lifecycleOwner) {



        val observer = LifecycleEventObserver { _, event ->
            when(event){
                Lifecycle.Event.ON_RESUME -> viewModel.resumeTime()
                Lifecycle.Event.ON_PAUSE -> viewModel.pauseTimer()

            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


    val progress by animateFloatAsState(targetValue = viewModel.progress)


    if(viewModel.alert != null) {
        AlertDialog(

            title = {
                Text(text = viewModel.alert?.title ?: "")
            },
            text = {
                Text(text = viewModel.alert?.text ?: "")
            },
            onDismissRequest = {
                               viewModel.alert!!.dismiss()
            },
            dismissButton = {
                TextButton(onClick = { viewModel.alert!!.dismiss() } ,colors = ButtonDefaults.textButtonColors(
                    contentColor = LocalContentColor.current
                )) {
                    Text(text = viewModel.alert?.dismissText  ?: "")
                }
            },
            confirmButton = {
                TextButton(onClick = { viewModel.alert!!.confirm() },colors = ButtonDefaults.textButtonColors(
                    contentColor = LocalContentColor.current
                )) {
                    Text(text = viewModel.alert?.confirmText  ?: "")
                }
            },
            backgroundColor = MaterialTheme.colors.primary

        )
    }


    Scaffold(
        topBar = {

            MineSweeperTopBar(
                remainingFlags = viewModel.remainingFlags,
                time = viewModel.time,
                progress = progress,
                modifier = Modifier.padding(20.dp,10.dp)
            )



        },
    ) {

        Board(
            board = viewModel.board,
            show = {i,j -> viewModel.show(i,j)},
            unHide = {i,j -> viewModel.unHide(i,j)},
            modifier = Modifier.padding(20.dp),

            hide = {i,j -> viewModel.hide(i,j)}
        )
    }





}