package com.vietnguyen.homeribbon

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.annguyenhoang.fashiongallery.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vietnguyen.homeribbon.data.adapters.PagesAdapter
import com.vietnguyen.homeribbon.data.models.TabModel

class BooksRibbonActivity : FragmentActivity() {
    private val tabsList = TabModel.mock()

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_ribbon)
        addControls()

        val pagerAdapter = PagesAdapter(this)
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabsList.getOrNull(position)?.tabName ?: "Tất cả"
        }.attach()
    }

    private fun addControls() {
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager_viet)
    }
}