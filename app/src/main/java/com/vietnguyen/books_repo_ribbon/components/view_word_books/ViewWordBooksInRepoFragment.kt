package com.vietnguyen.books_repo_ribbon.components.view_word_books

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.vietnguyen.books_repo_ribbon.FetchingStatus
import com.vietnguyen.books_repo_ribbon.adapters.BookAdapter
import com.vietnguyen.data.models.BookModel
import com.vietnguyen.books_repo_ribbon.ListBookViewModel
import com.vietnguyen.books_repo_ribbon.components.TabFragment

class ViewWordBooksInRepoFragment : TabFragment(R.layout.fragment_view_word_books_in_repo) {

    private lateinit var bookAdapter: BookAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var booksData: List<BookModel>

    private val viewModel: ListBookViewModel by activityViewModels()

    override fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.word_books_recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setItemViewCacheSize(20)
            setHasFixedSize(true)
            bookAdapter = BookAdapter()
            adapter = bookAdapter
        }
    }

    override fun viewControls() {
        viewModel.wordBooksData.observe(viewLifecycleOwner) { uiState ->
            when (uiState.fetchingStatus) {
                FetchingStatus.LOADING -> {

                }

                FetchingStatus.SUCCESS -> {
                    booksData = uiState.data
                    bookAdapter.setBooksDataToList(booksData)
                }

                else -> {

                }
            }
        }

        viewModel.fetchWordBooks()
    }
}