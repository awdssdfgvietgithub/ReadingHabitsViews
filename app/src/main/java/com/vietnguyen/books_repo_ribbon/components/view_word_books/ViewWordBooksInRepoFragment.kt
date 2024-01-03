package com.vietnguyen.books_repo_ribbon.components.view_word_books

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.vietnguyen.books_repo_ribbon.FetchingStatus
import com.vietnguyen.books_repo_ribbon.ListBookViewModel
import com.vietnguyen.books_repo_ribbon.adapters.BookAdapter
import com.vietnguyen.books_repo_ribbon.components.TabFragment
import com.vietnguyen.data.models.BookModel
import com.vietnguyen.data.models.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ViewWordBooksInRepoFragment :
    TabFragment(
        layoutId = R.layout.fragment_view_word_books_in_repo,
        bookAdapter = BookAdapter()
    ) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var booksData: List<BookModel>
    private lateinit var newBooksLoadMore: MutableList<BookModel>
    private lateinit var progressBarLoadMore: ProgressBar
    private lateinit var progressBarLoadData: ProgressBar
    private lateinit var nothing: TextView
    private lateinit var loadMoreJob: Job
    private lateinit var coroutineScope: CoroutineScope
    private var isCalledApi: Boolean = false
    private val viewModel: ListBookViewModel by activityViewModels()

    override fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.word_books_recycler_view)
        progressBarLoadMore = view.findViewById(R.id.progress_bar_load_more)
        progressBarLoadData = view.findViewById(R.id.progress_bar_load_data)
        nothing = view.findViewById(R.id.nothing_text_view)
        newBooksLoadMore = mutableListOf()
        loadMoreJob = Job()
        coroutineScope = CoroutineScope(Dispatchers.Main)

        initRecyclerView(recyclerView = recyclerView)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (isRecyclerViewAtTheEnd(recyclerView = recyclerView, newState = newState)) {
                    newBooksLoadMore.clear()
                    newBooksLoadMore = generateNewBooks(start = booksData.count())
                    loadMoreJob =
                        viewModel.loadMore(
                            newBooks = newBooksLoadMore,
                            bookType = Category.WORD_BOOK
                        )
                } else {
                    progressBarLoadMore.visibility = View.GONE;
                    if (loadMoreJob.isActive) {
                        loadMoreJob.cancel()
                    }
                }
            }
        })

        eventClickButton(
            viewModel = viewModel,
            coroutineScope = coroutineScope
        )
    }

    override fun viewControls() {
        viewModel.wordBooksData.observe(viewLifecycleOwner) { uiState ->
            when (uiState.fetchingStatus) {
                FetchingStatus.LOADING -> {
                    recyclerView.visibility = View.GONE
                    progressBarLoadData.visibility = View.VISIBLE
                    nothing.visibility = View.GONE
                }

                FetchingStatus.SUCCESS -> {
                    recyclerView.visibility = View.VISIBLE
                    progressBarLoadData.visibility = View.GONE
                    nothing.visibility = View.GONE

                    booksData = uiState.data
                    if (!isCalledApi) {
                        bookAdapter.setBooksDataToList(booksData)
                        isCalledApi = true
                    }
                }

                FetchingStatus.EMPTY -> {
                    recyclerView.visibility = View.GONE
                    progressBarLoadData.visibility = View.GONE
                    nothing.visibility = View.VISIBLE
                }

                else -> {
                    recyclerView.visibility = View.GONE
                }
            }

            when (uiState.isLoadingMore) {
                true -> {
                    progressBarLoadMore.visibility = View.VISIBLE;
                }

                false -> {
                    progressBarLoadMore.visibility = View.GONE;
                    if (newBooksLoadMore.isNotEmpty()) {
                        bookAdapter.addBooks(
                            newBooks = newBooksLoadMore,
                            lastIndex = booksData.count()
                        )
                    }
                }
            }
        }

        viewModel.fetchWordBooks()
    }
}