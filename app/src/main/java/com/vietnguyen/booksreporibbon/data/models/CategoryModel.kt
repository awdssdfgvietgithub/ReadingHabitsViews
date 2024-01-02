package com.vietnguyen.booksreporibbon.data.models

import com.vietnguyen.booksreporibbon.data.models.Category.*

enum class Category(
    val id: Int,
    val categoryName: String,
) {
    WORD_BOOK(id = 1, categoryName = "Sách chữ"),
    IMAGE_BOOK(id = 2, categoryName = "Sách hình"),
}

data class CategoryModel(
    val id: Int,
    val tabName: String,
) {
    companion object {
        fun mock() = listOf(
            CategoryModel(0, "Tất cả"),
            CategoryModel(1, WORD_BOOK.categoryName),
            CategoryModel(2, IMAGE_BOOK.categoryName),
        )
    }
}
