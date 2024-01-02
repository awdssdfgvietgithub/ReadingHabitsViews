package com.vietnguyen.books_repo_ribbon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.vietnguyen.data.models.BookModel

// Diff util

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val books: MutableList<BookModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.book_item_layout, parent, false)

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val bookModel = books.getOrNull(position)
        bookModel?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount() = books.size

    fun setBooksDataToList(books: List<BookModel>) {
        if (this.books.isNotEmpty()) {
            this.books.clear()
        }

        this.books.addAll(books)
        notifyItemRangeInserted(0, books.count())
    }


    inner class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val bookImageView: ImageView = view.findViewById(R.id.book_image_view)
        private val bookCategoryTextView: TextView = view.findViewById(R.id.book_category_text_view)
        private val bookViewsTextView: TextView = view.findViewById(R.id.book_views_text_view)
        private val bookAuthorTextView: TextView = view.findViewById(R.id.book_author_text_view)
        private val bookNameTextView: TextView = view.findViewById(R.id.book_name_text_view)

        fun bind(bookModel: BookModel) {
            bookModel.apply {
                bookAuthorTextView.text = authorName ?: "N/a"
                bookImageView.setImageResource(image ?: R.drawable.temp_book)
                bookViewsTextView.text = views ?: "N/a"
                bookCategoryTextView.text = category?.categoryName ?: "N/a"
                bookNameTextView.text = name ?: "N/a"
            }
        }
    }
}