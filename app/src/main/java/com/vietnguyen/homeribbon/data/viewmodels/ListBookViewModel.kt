package com.vietnguyen.homeribbon.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vietnguyen.homeribbon.data.models.BookModel
import com.vietnguyen.homeribbon.data.models.Category.IMAGE_BOOK
import com.vietnguyen.homeribbon.data.models.Category.WORD_BOOK
import kotlinx.coroutines.launch

class ListBookViewModel : ViewModel() {
    private var _allBooksData = MutableLiveData<List<BookModel>>()
    val allBooksData: MutableLiveData<List<BookModel>> get() = _allBooksData

    private var _wordBooksData = MutableLiveData<List<BookModel>>()
    val wordBooksData: MutableLiveData<List<BookModel>> get() = _wordBooksData

    private var _imageBooksData = MutableLiveData<List<BookModel>>()
    val imageBooksData: MutableLiveData<List<BookModel>> get() = _imageBooksData


    fun fetchBooks() =
        viewModelScope.launch {
            getAllBooks()
            getWordBooks()
            getImageBooks()
        }

    private fun getAllBooks() =
        viewModelScope.launch {
            _allBooksData.value = BookModel.mock()
        }

    private fun getWordBooks() =
        viewModelScope.launch {
            _wordBooksData.value = BookModel.mock().filter {
                it.category == WORD_BOOK
            }
        }

    private fun getImageBooks() =
        viewModelScope.launch {
            _imageBooksData.value = BookModel.mock().filter {
                it.category == IMAGE_BOOK
            }
        }

}