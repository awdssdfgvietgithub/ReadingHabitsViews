package com.vietnguyen.books_repo_ribbon.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vietnguyen.books_repo_ribbon.components.view_all_books.ViewAllBooksInRepoFragment
import com.vietnguyen.books_repo_ribbon.components.view_image_books.ViewImageBooksInRepoFragment
import com.vietnguyen.books_repo_ribbon.components.view_word_books.ViewWordBooksInRepoFragment

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