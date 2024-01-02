package com.vietnguyen.books_repo_ribbon.components.view_all_books

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.annguyenhoang.fashiongallery.R
import com.vietnguyen.books_repo_ribbon.FetchingStatus
import com.vietnguyen.books_repo_ribbon.adapters.BookAdapter
import com.vietnguyen.data.models.BookModel
import com.vietnguyen.books_repo_ribbon.ListBookViewModel

class ViewAllBooksInRepoFragment : Fragment(R.layout.fragment_view_all_books_in_repo) {

    private lateinit var bookAdapter: BookAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var booksData: List<BookModel>
    private val viewModel: ListBookViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls(view = view)
        viewModel.allBooksData.observe(viewLifecycleOwner) { uiState ->
            when (uiState.fetchingStatus) {
                FetchingStatus.LOADING -> {

                }

                FetchingStatus.SUCCESS -> {
                    booksData = uiState.data ?: listOf()
                    val linearLayoutManager = LinearLayoutManager(view.context)
                    displayRecyclerView(booksData = booksData, layout = linearLayoutManager)
                }

                else -> {

                }
            }
        }

        viewModel.fetchAllBooks()
    }

    private fun displayRecyclerView(booksData: List<BookModel>, layout: LayoutManager) {
        bookAdapter = BookAdapter()
        bookAdapter.setBooksDataToList(booksData)

        recyclerView.apply {
            layoutManager = layout
            adapter = bookAdapter
        }
    }

    private fun addControls(view: View) {
        recyclerView = view.findViewById(R.id.all_books_recycler_view)
    }
}