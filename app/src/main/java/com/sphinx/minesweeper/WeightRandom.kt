package com.sphinx.minesweeper

import android.util.Log
import kotlin.random.Random


fun <T> MutableList<T>.swap(i:Int,j:Int){
    val z = this[i];
    this[i] = this[j];
    this[j] = z;
}

class WeightRandom<T> {
    private val items = mutableListOf<T>();
    private val weights = mutableListOf<Int>();
    private var size = 0

    fun add(item:T,weight:Int){
        items.add(item);
        weights.add(weight);
        size += weight
    }

    fun get():T{
        var random = (1..size).random()




        var i = 0;
        while(random > weights[i]){
            random -= weights[i]
            i++
        }

        weights.swap(i,weights.size - 1);
        items.swap(i,items.size - 1);

        size -= weights.removeLast()

        return items.removeLast()

    }



}