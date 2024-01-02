package com.annguyenhoang.fashiongallery.cards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import com.annguyenhoang.fashiongallery.R

/**
 * Inflates and populates a [View] representing a [Card]
 */
class CardView(layoutInflater: LayoutInflater, container: ViewGroup?) {
    val view: View = layoutInflater.inflate(R.layout.item_card_layout, container, false)
    private val textSuite: TextView
    private val textCorner1: TextView
    private val textCorner2: TextView

    init {
        textSuite = view.findViewById(R.id.label_center)
        textCorner1 = view.findViewById(R.id.label_top)
        textCorner2 = view.findViewById(R.id.label_bottom)
    }

    /**
     * Updates the view to represent the passed in Card
     */
    fun bind(card: Card) {
        textSuite.text = card.suit
        val backGroundColorRes = getColorRes(card)
        backGroundColorRes?.let {
            view.setBackgroundResource(it)
        }

        val cornerLabel = card.cornerLabel
        textCorner1.text = cornerLabel
        textCorner2.text = cornerLabel
    }

    @ColorRes
    private fun getColorRes(card: Card): Int? {
        val shade = getShade(card)
        val color = getColor(card)
        return if (shade != null && color != null) {
            COLOR_MAP[color][shade]
        } else {
            null
        }
    }

    private fun getShade(card: Card): Int? {
        return when (card.value) {
            "2", "6", "10", "A" -> 2
            "3", "7", "J" -> 3
            "4", "8", "Q" -> 0
            "5", "9", "K" -> 1
            else -> null // Card value cannot be $card.value
        }
    }

    private fun getColor(card: Card): Int? {
        return when (card.suit) {
            "♣" -> 0
            "♦" -> 1
            "♥" -> 2
            "♠" -> 3
            else -> null // Card suit cannot be $card.suit
        }
    }

    companion object {
        private val COLOR_MAP = arrayOf(
            intArrayOf(R.color.red_100, R.color.red_300, R.color.red_500, R.color.red_700),
            intArrayOf(R.color.blue_100, R.color.blue_300, R.color.blue_500, R.color.blue_700),
            intArrayOf(R.color.green_100, R.color.green_300, R.color.green_500,
                R.color.green_700),
            intArrayOf(R.color.yellow_100, R.color.yellow_300, R.color.yellow_500,
                R.color.yellow_700))
    }

}