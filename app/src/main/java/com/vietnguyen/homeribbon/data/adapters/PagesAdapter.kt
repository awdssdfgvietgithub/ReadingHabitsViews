package com.vietnguyen.homeribbon.data.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vietnguyen.homeribbon.views.fragments.ViewAllBooksInRepoFragment
import com.vietnguyen.homeribbon.views.fragments.ViewImageBooksInRepoFragment
import com.vietnguyen.homeribbon.views.fragments.ViewWordBooksInRepoFragment

private val listFragments = listOf(
    ViewAllBooksInRepoFragment(),
    ViewWordBooksInRepoFragment(),
    ViewImageBooksInRepoFragment(),
)

class PagesAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = listFragments.size

    override fun createFragment(position: Int): Fragment {
        return listFragments.getOrNull(position) ?: ViewAllBooksInRepoFragment()
    }
}