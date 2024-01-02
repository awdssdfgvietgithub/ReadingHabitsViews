package com.vietnguyen.books_repo_ribbon.components.view_image_books

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.vietnguyen.books_repo_ribbon.FetchingStatus
import com.vietnguyen.books_repo_ribbon.adapters.BookAdapter
import com.vietnguyen.data.models.BookModel
import com.vietnguyen.books_repo_ribbon.ListBookViewModel

class ViewImageBooksInRepoFragment : Fragment(R.layout.fragment_view_image_books_in_repo) {
    private lateinit var bookAdapter: BookAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var booksData: List<BookModel>
    private val viewModel: ListBookViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls(view = view)
        viewModel.imageBooksData.observe(viewLifecycleOwner) { uiState ->
            when (uiState.fetchingStatus) {
                FetchingStatus.LOADING -> {

                }

                FetchingStatus.SUCCESS -> {
                    booksData = uiState.data
                    val linearLayoutManager = LinearLayoutManager(view.context)
                    displayRecyclerView(booksData = booksData, layout = linearLayoutManager)
                }

                else -> {

                }
            }
        }

        viewModel.fetchImageBooks()
    }

    private fun displayRecyclerView(
        booksData: List<BookModel>,
        layout: RecyclerView.LayoutManager,
    ) {
        bookAdapter = BookAdapter().also {
            it.setBooksDataToList(booksData)
        }

        recyclerView.apply {
            layoutManager = layout
            adapter = bookAdapter
        }
    }

    private fun addControls(view: View) {
        recyclerView = view.findViewById(R.id.image_books_recycler_view)
    }
}