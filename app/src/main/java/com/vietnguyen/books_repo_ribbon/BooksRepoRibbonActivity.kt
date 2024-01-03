package com.vietnguyen.books_repo_ribbon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vietnguyen.books_repo_ribbon.Book.Companion.diffUtil
import java.util.UUID

class BooksRepoRibbonActivity : FragmentActivity() {
//    private val tabsList = CategoryModel.mock()
//    private lateinit var tabLayout: TabLayout
//    private lateinit var viewPager: ViewPager2

    private lateinit var rvBooks: RecyclerView
    private lateinit var floatingButton: FloatingActionButton

    private val booksAdapter by lazy {
        BooksAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_ribbon)
        addControls()
        setUpRvBooks()

        var books = listOf(
            Book(id = "123", bookName = "book"),
            Book(id = "234", bookName = "book"),
        )

        floatingButton.setOnClickListener {
            val newList = books.toMutableList()
            val newBook = books[0].copy(bookName = "Book Name Da change")
            books += newBook

            booksAdapter.submitList(newList)
        }

//        tabLayout.setOnApplyWindowInsetsListener { view, insets ->
//            view.updatePadding(
//                top = when {
//                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
//                        view.rootWindowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars()).top
//                    }
//
//                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1 -> {
//                        view.rootWindowInsets?.stableInsetTop ?: 0
//                    }
//
//                    else -> 0
//                }
//            )
//            insets
//        }
//
//        val pagerAdapter = PagesAdapter(this)
//        viewPager.adapter = pagerAdapter
//
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = tabsList.getOrNull(position)?.tabName ?: "Tất cả"
//        }.attach()
    }

    private fun addControls() {
        rvBooks = findViewById(R.id.rv_books)
        floatingButton = findViewById(R.id.btn_change)
//        tabLayout = findViewById(R.id.tab_layout)
//        viewPager = findViewById(R.id.view_pager_viet)
    }

    private fun setUpRvBooks() {
        rvBooks.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)

            adapter = this@BooksRepoRibbonActivity.booksAdapter
        }
    }
}

data class Book(
    val id: String = UUID.randomUUID().toString(),
    val bookName: String,
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class BooksAdapter : ListAdapter<Book, BooksAdapter.BookViewHolder>(diffUtil) {

    override fun submitList(list: MutableList<Book>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_book_test, parent, false)

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    inner class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvBookName = view.findViewById<TextView>(R.id.test)

        fun bind(book: Book) {
            tvBookName.text = book.bookName
        }
    }
}


