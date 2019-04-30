package main

import android.app.Application
import data.PlayerJSONStore
import data.PlayerMemStore
import data.PlayerStore
import data.firebase.PlayerFireStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class MainApp : Application(), AnkoLogger {

    lateinit var players: PlayerStore

    override fun onCreate() {
        super.onCreate()
        //players = PlayerJSONStore(applicationContext)
        players = PlayerFireStore(applicationContext)
        info("Player started")
    }
}