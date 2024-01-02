package com.vietnguyen.booksreporibbon.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.vietnguyen.booksreporibbon.data.models.BookModel

class BookAdapter(private var data: List<BookModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class BookViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        var bookImageView: ImageView
        var bookCategoryTextView: TextView
        var bookViewsTextView: TextView
        var bookAuthorTextView: TextView
        var bookNameTextView: TextView

        init {
            bookImageView = viewHolder.findViewById(R.id.book_image_view)
            bookCategoryTextView = viewHolder.findViewById(R.id.book_category_text_view)
            bookViewsTextView = viewHolder.findViewById(R.id.book_views_text_view)
            bookAuthorTextView = viewHolder.findViewById(R.id.book_author_text_view)
            bookNameTextView = viewHolder.findViewById(R.id.book_name_text_view)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val taskViewHolderTemplate =
            LayoutInflater.from(parent.context).inflate(R.layout.book_item_layout, parent, false)
        return BookViewHolder(taskViewHolderTemplate)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookViewHolder) {

            holder.bookAuthorTextView.text = data.getOrNull(position)?.authorName ?: "N/a"
            holder.bookImageView.setImageResource(
                data.getOrNull(position)?.image ?: R.drawable.temp_book
            )
            holder.bookViewsTextView.text = data.getOrNull(position)?.views ?: "N/a"
            holder.bookCategoryTextView.text =
                data.getOrNull(position)?.category?.categoryName ?: "N/a"
            holder.bookNameTextView.text = data.getOrNull(position)?.name ?: "N/a"
        }
    }
}