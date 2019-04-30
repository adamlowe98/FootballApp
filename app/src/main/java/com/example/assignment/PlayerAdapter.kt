package com.example.assignment


import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import data.PlayerModel
import helpers.readImageFromPath
import kotlinx.android.synthetic.main.activity_player_list.view.*
import kotlinx.android.synthetic.main.card_player.view.*
import org.jetbrains.anko.startActivityForResult

interface PlayerListener {
    fun onPlayerClick(player: PlayerModel)
}


class PlayerAdapter constructor(private var players: List<PlayerModel>,
                                private val listener: PlayerListener) : RecyclerView.Adapter<PlayerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_player, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val player = players[holder.adapterPosition]
        holder.bind(player, listener)
    }

    override fun getItemCount(): Int = players.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(player: PlayerModel, listener : PlayerListener) {
            itemView.playerTitle.text = player.title
            itemView.team.text = player.team
            itemView.cost.text = player.cost
            itemView.Pos.text = player.Pos
            Glide.with(itemView.context).load(player.image).into(itemView.imageIcon);
            itemView.setOnClickListener { listener.onPlayerClick(player) }
        }
    }
}


