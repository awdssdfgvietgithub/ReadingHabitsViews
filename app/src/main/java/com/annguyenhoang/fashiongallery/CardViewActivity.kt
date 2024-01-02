package com.annguyenhoang.fashiongallery

import android.os.Bundle
import com.annguyenhoang.fashiongallery.cards.CardViewAdapter

/**
 * Shows how to use [ViewPager2.setAdapter] with Views.
 *
 * @see CardFragmentActivity for an example of using {@link ViewPager2} with Fragments.
 */
open class CardViewActivity : BaseCardActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager.adapter = CardViewAdapter()
    }
}