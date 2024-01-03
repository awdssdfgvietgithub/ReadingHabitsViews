package com.vietnguyen.data.models

import com.annguyenhoang.fashiongallery.R
import com.vietnguyen.data.models.Category.*

data class BookModel(
    val image: Int? = R.drawable.temp_book,
    val name: String? = "Shin-Cậu bé bút chì - Tập 7",
    val category: Category? = WORD_BOOK,
    val authorName: String? = "Yoshito Usui",
    val views: String? = "12.345",
) {
    companion object {
        fun mock() = buildList {
            repeat(10) { index ->
                add(BookModel(name = "Sách $index", category = WORD_BOOK))
            }
        }
    }
}
