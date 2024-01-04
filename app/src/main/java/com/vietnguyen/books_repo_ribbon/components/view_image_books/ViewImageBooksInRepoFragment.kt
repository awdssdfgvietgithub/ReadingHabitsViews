package com.vietnguyen.books_repo_ribbon.components.view_image_books

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.facebook.shimmer.ShimmerFrameLayout
import com.vietnguyen.books_repo_ribbon.FetchingStatus
import com.vietnguyen.books_repo_ribbon.ListBookViewModel
import com.vietnguyen.books_repo_ribbon.adapters.BookAdapter
import com.vietnguyen.books_repo_ribbon.components.TabFragment
import com.vietnguyen.data.extensions.startAndVisibility
import com.vietnguyen.data.models.BookModel
import com.vietnguyen.data.models.Category.IMAGE_BOOK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ViewImageBooksInRepoFragment :
    TabFragment(
        layoutId = R.layout.fragment_view_image_books_in_repo,
        bookAdapter = BookAdapter()
    ) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var booksData: List<BookModel>
    private lateinit var emptyStageLayout: LinearLayout
    private lateinit var newBooksLoadMore: MutableList<BookModel>
    private lateinit var loadMoreJob: Job
    private lateinit var coroutineScope: CoroutineScope
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private var isCalledApi: Boolean = false
    private var isLoading: Boolean = false
    private val viewModel: ListBookViewModel by activityViewModels {
        ListBookViewModel.Factory
    }

    override fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.image_books_recycler_view)
        emptyStageLayout = view.findViewById(R.id.empty_stage_layout)
        newBooksLoadMore = mutableListOf()
        loadMoreJob = Job()
        coroutineScope = CoroutineScope(Dispatchers.Main)
        shimmerFrameLayout = view.findViewById(R.id.shimmer_frame_layout)

        initRecyclerView(recyclerView = recyclerView)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                if (!isLoading) {
                    if (linearLayoutManager != null &&
                        linearLayoutManager.findLastCompletelyVisibleItemPosition() == (booksData.count() - 1)
                    ) {
                        loadMore()
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
        viewModel.imageBooksData.observe(viewLifecycleOwner) { uiState ->
            when (uiState.fetchingStatus) {
                FetchingStatus.LOADING -> {
                    recyclerView.visibility = View.GONE
                    shimmerFrameLayout.startAndVisibility(isStart = true, isVisible = true)
                    emptyStageLayout.visibility = View.GONE
                }

                FetchingStatus.SUCCESS -> {
                    recyclerView.visibility = View.VISIBLE
                    shimmerFrameLayout.startAndVisibility(isStart = false, isVisible = false)
                    emptyStageLayout.visibility = View.GONE

                    booksData = uiState.data
                    if (!isCalledApi) {
                        bookAdapter.submitList(booksData)
                        isCalledApi = true
                    }
                }

                FetchingStatus.EMPTY -> {
                    recyclerView.visibility = View.GONE
                    shimmerFrameLayout.startAndVisibility(isStart = false, isVisible = false)
                    emptyStageLayout.visibility = View.VISIBLE
                }

                else -> {
                    recyclerView.visibility = View.GONE
                }
            }

            when (uiState.isLoadingMore) {
                true -> {
                    isLoading = true
                }

                false -> {
                    isLoading = false

                    booksData = uiState.data
                    bookAdapter.submitList(booksData)
                }
            }
        }

        viewModel.fetchImageBooks()
    }

    private fun loadMore() {
        loadMoreJob.cancel()
        newBooksLoadMore.clear()

        newBooksLoadMore =
            generateNewBooks(start = booksData.count(), specificCategory = IMAGE_BOOK)
        loadMoreJob = viewModel.loadMore(newBooks = newBooksLoadMore, bookType = IMAGE_BOOK)
        isLoading = false
    }
}