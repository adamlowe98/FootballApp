package com.example.assignment

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import data.PlayerModel
import kotlinx.android.synthetic.main.activity_player_list.*
import main.MainApp
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult


class PlayerListActivity : AppCompatActivity(), PlayerListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_list)
        app = application as MainApp
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PlayerAdapter(app.players.findAll(), this)
        loadPlayers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<PlayerActivity>(200)
            R.id.item_map -> startActivity<PlayerMapsActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPlayerClick(player: PlayerModel) {
        startActivityForResult(intentFor<PlayerActivity>().putExtra("player_edit", player), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadPlayers()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadPlayers() {
        showPlayers(app.players.findAll())
    }

    fun showPlayers(players: List<PlayerModel>) {
        recyclerView.adapter = PlayerAdapter(players, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

}

