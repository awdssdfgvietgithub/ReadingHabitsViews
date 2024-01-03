package com.vietnguyen.books_repo_ribbon

import com.vietnguyen.data.models.BookModel

enum class FetchingStatus() {
    LOADING,
    SUCCESS,
    EMPTY,
    ERROR
}

data class BooksRepoUIState(
    val fetchingStatus: FetchingStatus = FetchingStatus.LOADING,
    val data: List<BookModel> = listOf(),
    val isLoadingMore: Boolean = false,
)
