package com.vietnguyen.homeribbon.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.vietnguyen.homeribbon.data.adapters.BookAdapter
import com.vietnguyen.homeribbon.data.models.BookModel
import com.vietnguyen.homeribbon.data.viewmodels.ListBookViewModel

class ViewWordBooksInRepoFragment : Fragment() {
    private lateinit var bookAdapter: BookAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var booksData: List<BookModel>
    private val viewModel: ListBookViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_view_word_books_in_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls(view = view)

        booksData = viewModel.wordBooksData.value ?: listOf()
        val linearLayoutManager = LinearLayoutManager(view.context)
        displayRecyclerView(booksData = booksData, layout = linearLayoutManager)
    }

    private fun displayRecyclerView(booksData: List<BookModel>, layout: RecyclerView.LayoutManager) {
        bookAdapter = BookAdapter(booksData)

        recyclerView.apply {
            layoutManager = layout
            adapter = bookAdapter
        }
    }

    private fun addControls(view: View) {
        recyclerView = view.findViewById(R.id.word_books_recycler_view)
    }
}