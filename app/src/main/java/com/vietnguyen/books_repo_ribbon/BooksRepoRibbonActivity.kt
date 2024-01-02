package com.vietnguyen.books_repo_ribbon

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import androidx.activity.enableEdgeToEdge
import androidx.core.view.updatePadding
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.annguyenhoang.fashiongallery.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vietnguyen.books_repo_ribbon.adapters.PagesAdapter
import com.vietnguyen.data.models.CategoryModel

class BooksRepoRibbonActivity : FragmentActivity() {
    private val tabsList = CategoryModel.mock()
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_ribbon)
        addControls()

        tabLayout.setOnApplyWindowInsetsListener { view, insets ->
            view.updatePadding(
                top = when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                        view.rootWindowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars()).top
                    }

                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1 -> {
                        view.rootWindowInsets?.stableInsetTop ?: 0
                    }

                    else -> 0
                }
            )
            insets
        }

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