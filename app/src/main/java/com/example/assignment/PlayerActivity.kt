package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import data.Location
import data.PlayerModel
import helpers.readImage
import helpers.readImageFromPath
import helpers.showImagePicker
import kotlinx.android.synthetic.main.activity_main.*
import main.MainApp
import org.jetbrains.anko.*


class PlayerActivity : AppCompatActivity(), AnkoLogger {

    var player = PlayerModel()
    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2
    var edit = false

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        info("Player Activity started..")

        app = application as MainApp

        if (intent.hasExtra("player_edit")) {
            edit = true
            player = intent.extras.getParcelable<PlayerModel>("player_edit")
            playerTitleET.setText(player.title)
            playerAgeET.setText(player.Age)
            playerTeamET.setText(player.team)
            playerCostET.setText(player.cost)
            playerPosET.setText(player.Pos)
            playerLeagueET.setText(player.League)
            playerImage.setImageBitmap(readImageFromPath(this, player.image))
            if (player.image != null) {
                chooseImage.setText(R.string.change_player_image)
            }
            btnAdd.setText(R.string.save_player)
        }

        btnAdd.setOnClickListener() {
            player.title = playerTitleET.text.toString()
            player.Age = playerAgeET.text.toString()
            player.team = playerTeamET.text.toString()
            player.cost = playerCostET.text.toString()
            player.Pos = playerPosET.text.toString()
            player.League = playerLeagueET.text.toString()
            if (player.title.isEmpty()) {
                toast(R.string.enter_player_title)
            } else {
                if (edit) {
                    app.players.update(player.copy())
                } else {

                    app.players.create(player.copy())
                }
            }
            info("add Button Pressed: $playerTitleET")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

        placemarkLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (player.zoom != 0f) {
                location.lat = player.lat
                location.lng = player.lng
                location.zoom = player.zoom
            }
            startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    player.image = data.getData().toString()
                    playerImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_player_image)
                }


            }
            LOCATION_REQUEST -> {
                if (data != null) {
                    val location = data.extras.getParcelable<Location>("location")
                    player.lat = location.lat
                    player.lng = location.lng
                    player.zoom = location.zoom
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.players.delete(player)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
