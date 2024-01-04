package com.vietnguyen.books_repo_ribbon.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.vietnguyen.books_repo_ribbon.adapters.viewholders.BookViewHolder
import com.vietnguyen.books_repo_ribbon.adapters.viewholders.LoadMoreViewHolder
import com.vietnguyen.data.models.BookModel

class BookAdapter : ListAdapter<BookModel, RecyclerView.ViewHolder>(bookDiffUtil) {
    var onButtonClicked: ((LinearProgressIndicator, TextView, LinearLayout) -> Unit)? = null

    override fun submitList(list: List<BookModel>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = when (viewType) {
            VIEW_BOOK_TYPE -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.book_item_layout, parent, false)

                BookViewHolder(view, onButtonClicked)
            }

            else -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.progress_bar_load_more, parent, false)

                LoadMoreViewHolder(view)
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val bookModel = getItem(position)

        when (holder) {
            is BookViewHolder -> {
                bookModel?.let(holder::bind)
            }

            is LoadMoreViewHolder -> {

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount.minus(1)) VIEW_LOAD_TYPE else VIEW_BOOK_TYPE
    }

    companion object {
        const val VIEW_BOOK_TYPE = 0
        const val VIEW_LOAD_TYPE = 1

        val bookDiffUtil = object : DiffUtil.ItemCallback<BookModel>() {
            override fun areItemsTheSame(
                oldItem: BookModel,
                newItem: BookModel,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: BookModel,
                newItem: BookModel,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}