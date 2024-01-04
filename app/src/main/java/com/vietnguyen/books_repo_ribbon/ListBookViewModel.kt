package com.vietnguyen.books_repo_ribbon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vietnguyen.data.models.BookModel
import com.vietnguyen.data.models.Category
import com.vietnguyen.data.models.Category.IMAGE_BOOK
import com.vietnguyen.data.models.Category.WORD_BOOK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: activityViewModels(): khoi tao viewmodel voi param
class ListBookViewModel(
    private val test: String,
) : ViewModel() {

    private var _allBooksData = MutableLiveData(
        BooksRepoUIState()
    )
    val allBooksData: LiveData<BooksRepoUIState> get() = _allBooksData

    private var _wordBooksData = MutableLiveData(
        BooksRepoUIState()
    )
    val wordBooksData: LiveData<BooksRepoUIState> get() = _wordBooksData

    private var _imageBooksData = MutableLiveData(
        BooksRepoUIState()
    )
    val imageBooksData: LiveData<BooksRepoUIState> get() = _imageBooksData

    private var allBooksLoadMoreJob: Job = Job()
    private var wordBooksLoadMoreJob: Job = Job()
    private var imageBooksLoadMoreJob: Job = Job()

    init {
        Log.d("VietDebug", test)
    }

    fun fetchAllBooks() = viewModelScope.launch {
        cancelAllJobLoadMore()

        val dataMock = withContext(Dispatchers.Default) {
            async {
                BookModel.mock()
            }.await()
        }

        delay(2000)

        withContext(Dispatchers.Main.immediate) {
            _allBooksData.value?.let { value ->
                if (dataMock.isNotEmpty()) {
                    _allBooksData.value = value.copy(
                        fetchingStatus = FetchingStatus.SUCCESS,
                        data = dataMock
                    )
                } else {
                    _allBooksData.value = value.copy(
                        fetchingStatus = FetchingStatus.EMPTY,
                    )
                }
            }
        }
    }


    fun fetchWordBooks() = viewModelScope.launch {
        cancelAllJobLoadMore()

        val dataMock = withContext(Dispatchers.Default) {
            async {
                BookModel.mock().filter { it.category == WORD_BOOK }
            }.await()
        }

        delay(2000)

        withContext(Dispatchers.Main.immediate) {
            if (dataMock.isNotEmpty()) {
                _wordBooksData.value = _wordBooksData.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = dataMock
                )
            } else {
                _wordBooksData.value = _wordBooksData.value?.copy(
                    fetchingStatus = FetchingStatus.EMPTY,
                )
            }
        }
    }

    fun fetchImageBooks() = viewModelScope.launch {
        cancelAllJobLoadMore()

        val dataMock = withContext(Dispatchers.Default) {
            async {
                BookModel.mock().filter { it.category == IMAGE_BOOK }
            }.await()
        }

        delay(2000)

        withContext(Dispatchers.Main.immediate) {
            if (dataMock.isNotEmpty()) {
                _imageBooksData.value = _imageBooksData.value?.copy(
                    fetchingStatus = FetchingStatus.SUCCESS,
                    data = dataMock
                )
            } else {
                _imageBooksData.value = _imageBooksData.value?.copy(
                    fetchingStatus = FetchingStatus.EMPTY,
                )
            }
        }
    }

    fun loadMore(newBooks: List<BookModel>, bookType: Category? = null) = viewModelScope.launch {
        if (newBooks.isNotEmpty()) {
            when (bookType) {
                WORD_BOOK -> {
                    wordBooksLoadMoreJob =
                        updateState(booksData = _wordBooksData, newBooks = newBooks)
                }

                IMAGE_BOOK -> {
                    imageBooksLoadMoreJob =
                        updateState(booksData = _imageBooksData, newBooks = newBooks)
                }

                else -> {
                    allBooksLoadMoreJob =
                        updateState(booksData = _allBooksData, newBooks = newBooks)
                }
            }
        }
    }

    private fun updateState(
        booksData: MutableLiveData<BooksRepoUIState>,
        newBooks: List<BookModel>,
    ) = viewModelScope.launch {
        booksData.value?.data?.let {
            booksData.value = booksData.value?.copy(
                isLoadingMore = true,
            )
        }

        delay(3000)

        booksData.value?.data?.let { oldBooks ->
            booksData.value = booksData.value?.copy(
                isLoadingMore = false,
                data = oldBooks + newBooks
            )
        }
    }


    suspend fun updateProgress(numberUpdate: Int, currentProgress: Int): Int {
        return withContext(Dispatchers.Default) {
            async {
                delay(2000)
                numberUpdate + currentProgress
            }
        }.await()
    }

    private fun cancelAllJobLoadMore() {
        allBooksLoadMoreJob.cancel()
        wordBooksLoadMoreJob.cancel()
        imageBooksLoadMoreJob.cancel()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Ví dụ gọi repository ở đây và truyền dô viewmodel
                ListBookViewModel(
                    test = "Hello"
                )
            }
        }
    }
}