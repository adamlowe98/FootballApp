package main

import android.app.Application
import data.PlayerJSONStore
import data.PlayerMemStore
import data.PlayerModel
import data.PlayerStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class MainApp : Application(), AnkoLogger {

    lateinit var players: PlayerStore

    override fun onCreate() {
        super.onCreate()
        players = PlayerJSONStore(applicationContext)
        info("Player started")
    }

    companion object {
        var playersList: ArrayList<PlayerModel>? = null

    }
}
