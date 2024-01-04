package com.vietnguyen.books_repo_ribbon.components

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vietnguyen.books_repo_ribbon.ListBookViewModel
import com.vietnguyen.books_repo_ribbon.adapters.BookAdapter
import com.vietnguyen.books_repo_ribbon.adapters.viewholders.BookViewHolder
import com.vietnguyen.data.models.BookModel
import com.vietnguyen.data.models.Category
import com.vietnguyen.data.models.Category.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.random.Random

abstract class TabFragment(
    layoutId: Int,
    bookAdapter: BookAdapter,
) :
    Fragment(layoutId) {
    var bookAdapter: BookAdapter

    init {
        this.bookAdapter = bookAdapter
    }

    abstract fun initViews(view: View)
    abstract fun viewControls()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        viewControls()
    }

    fun generateNewBooks(start: Int, specificCategory: Category? = null): MutableList<BookModel> {
        val booksList = mutableListOf<BookModel>()
        val rsRandomNumber = randomNumber(1, 5)
        specificCategory?.let {
            for (index in start..(start + rsRandomNumber)) {
                booksList.add(BookModel(name = "Sách $index", category = specificCategory))
            }
        } ?: run {
            for (index in start..(start + rsRandomNumber)) {
                booksList.add(BookModel(name = "Sách $index", category = randomCategory()))
            }
        }
        return booksList
    }

    private fun randomNumber(start: Int, end: Int) =
        (start..end).random()

    private fun randomCategory(): Category {
        val categoryList = listOf(WORD_BOOK, IMAGE_BOOK)
        val randomIndex = Random.nextInt(categoryList.size);
        return categoryList[randomIndex]
    }

    fun isRecyclerViewAtTheEnd(recyclerView: RecyclerView, newState: Int) =
        !recyclerView.canScrollVertically(1) && newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE

    fun eventClickButton(
        viewModel: ListBookViewModel,
        coroutineScope: CoroutineScope,
    ) {
        bookAdapter.onButtonClicked = { linearProgressIndicator, buttonText, button ->
            viewModel.removeFirstItem()
//            button.isClickable = false
//            coroutineScope.launch {
//                linearProgressIndicator.progress = viewModel.updateProgress(
//                    numberUpdate = randomNumber(start = 5, end = 20),
//                    currentProgress = linearProgressIndicator.progress
//                )
//                if (linearProgressIndicator.progress == 100) {
//                    buttonText.text = "Đọc lại"
//                } else if (linearProgressIndicator.progress > 0) {
//                    buttonText.text = "Đọc tiếp"
//                }
//                button.isClickable = true
//            }

        }
    }

    fun initRecyclerView(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setItemViewCacheSize(20)
            setHasFixedSize(true)
            adapter = bookAdapter
        }
    }
}