package com.annguyenhoang.fashiongallery.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CardViewAdapter : RecyclerView.Adapter<CardViewAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(CardView(LayoutInflater.from(parent.context), parent))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(card = Card.DECK[position])
    }

    override fun getItemCount(): Int {
        return Card.DECK.size
    }

    inner class CardViewHolder(private val cardView: CardView) :
        RecyclerView.ViewHolder(cardView.view) {
        fun bind(card: Card) {
            cardView.bind(card)
        }
    }

}