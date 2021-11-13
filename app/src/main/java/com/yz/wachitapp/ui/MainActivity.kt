package com.yz.wachitapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yz.presentation.screen.videoslist.VideosListScreen
import com.yz.wachitapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, VideosListScreen())
                .commitNow()
        }
    }

}