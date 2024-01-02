package com.vietnguyen.booksreporibbon

import com.vietnguyen.booksreporibbon.data.models.BookModel

enum class FetchingStatus() {
    LOADING,
    SUCCESS,
    EMPTY,
    ERROR
}

data class BooksRepoUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val data: List<BookModel> = listOf(),
)
