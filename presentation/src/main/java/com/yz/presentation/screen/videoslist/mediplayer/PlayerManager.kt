package com.yz.presentation.screen.videoslist

interface PlayerManager<T> {

    fun play(view: T, url: String, position: Long)

    fun pause()

    fun resume()

    fun release()

    fun getCurrentPosition(): Long


}