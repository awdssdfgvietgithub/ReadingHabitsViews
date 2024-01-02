package com.vietnguyen.books_repo_ribbon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vietnguyen.data.models.BookModel
import com.vietnguyen.data.models.Category.IMAGE_BOOK
import com.vietnguyen.data.models.Category.WORD_BOOK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: activityViewModels(): khoi tao viewmodel voi param
class ListBookViewModel(/**private val page: Int**/) : ViewModel() {

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


    fun fetchAllBooks() = viewModelScope.launch {
        val dataMock = withContext(Dispatchers.Default) {
            async {
                BookModel.mock()
            }.await()
        }

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
        val dataMock = withContext(Dispatchers.Default) {
            async {
                BookModel.mock().filter { it.category == WORD_BOOK }
            }.await()
        }

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
        val dataMock = withContext(Dispatchers.Default) {
            async {
                BookModel.mock().filter { it.category == IMAGE_BOOK }
            }.await()
        }

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
}