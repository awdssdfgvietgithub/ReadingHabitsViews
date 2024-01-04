package com.vietnguyen.data.models

import androidx.recyclerview.widget.DiffUtil
import com.annguyenhoang.fashiongallery.R
import com.vietnguyen.data.models.Category.*
import java.util.UUID

data class BookModel(
    val id: String = UUID.randomUUID().toString(),
    val image: Int? = R.drawable.temp_book,
    val name: String? = "Shin-Cậu bé bút chì - Tập 7",
    val category: Category? = WORD_BOOK,
    val authorName: String? = "Yoshito Usui",
    val views: String? = "12.345",
    val allPages: Int = 1,
    val currentPage: Int? = null,
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<BookModel>() {
            override fun areItemsTheSame(oldItem: BookModel, newItem: BookModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: BookModel, newItem: BookModel): Boolean {
                return oldItem == newItem
            }
        }

        fun mock() = buildList {
            add(BookModel(name = "Sách", category = WORD_BOOK, currentPage = 150, allPages = 200))
            repeat(10) { index ->
                add(BookModel(name = "Sách $index", category = WORD_BOOK))
            }
        }
    }
}
