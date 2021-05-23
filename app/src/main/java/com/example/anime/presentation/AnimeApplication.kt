package com.example.anime.presentation

import android.app.Application
import android.content.Context

class AnimeApplication: Application() {
    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}