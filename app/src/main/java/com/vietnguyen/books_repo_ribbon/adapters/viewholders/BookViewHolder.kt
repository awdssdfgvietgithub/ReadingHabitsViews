package com.vietnguyen.books_repo_ribbon.adapters.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.vietnguyen.data.models.BookModel

class BookViewHolder(
    view: View,
    private var onButtonClicked: ((LinearProgressIndicator, TextView, LinearLayout) -> Unit)? = null,
) : RecyclerView.ViewHolder(view) {
    private val bookImageView: ImageView = view.findViewById(R.id.book_image_view)
    private val bookCategoryTextView: TextView = view.findViewById(R.id.book_category_text_view)
    private val bookViewsTextView: TextView = view.findViewById(R.id.book_views_text_view)
    private val bookAuthorTextView: TextView = view.findViewById(R.id.book_author_text_view)
    private val bookNameTextView: TextView = view.findViewById(R.id.book_name_text_view)
    private val readNowButton: LinearLayout = view.findViewById(R.id.button_read_now)
    private val linearProgressIndicator: LinearProgressIndicator =
        view.findViewById(R.id.linear_progress_indicator)
    private val buttonTextView: TextView = view.findViewById(R.id.button_text)

    fun bind(bookModel: BookModel) {
        bookModel.apply {
            bookAuthorTextView.text = authorName ?: "N/a"
            bookImageView.setImageResource(image ?: R.drawable.temp_book)
            bookViewsTextView.text = views ?: "N/a"
            bookCategoryTextView.text = category?.categoryName ?: "N/a"
            bookNameTextView.text = name ?: "N/a"
        }

        readNowButton.setOnClickListener {
            onButtonClicked?.invoke(linearProgressIndicator, buttonTextView, readNowButton)
        }
    }
}