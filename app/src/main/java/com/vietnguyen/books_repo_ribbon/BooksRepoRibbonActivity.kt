package com.vietnguyen.books_repo_ribbon

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import androidx.core.view.updatePadding
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.annguyenhoang.fashiongallery.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.internal.EdgeToEdgeUtils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vietnguyen.books_repo_ribbon.SortBottomSheetDialogFragment.Companion.TAG
import com.vietnguyen.books_repo_ribbon.adapters.PagesAdapter
import com.vietnguyen.data.models.CategoryModel


class BooksRepoRibbonActivity : FragmentActivity() {
    private val tabsList = CategoryModel.mock()
    private lateinit var tabLayout: TabLayout
    private lateinit var sortButton: LinearLayout
    private lateinit var viewPager: ViewPager2

    private lateinit var rvBooks: RecyclerView
    private lateinit var floatingButton: FloatingActionButton

//    private val booksAdapter by lazy {
//        BooksAdapter()
//    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_ribbon)
        addControls()
//        setUpRvBooks()

//        var books = listOf(
//            Book(id = "123", bookName = "book"),
//            Book(id = "234", bookName = "book"),
//        )
//
//        floatingButton.setOnClickListener {
//            val newList = books.toMutableList()
//            val newBook = books[0].copy(bookName = "Book Name Da change")
//            books += newBook
//
//            booksAdapter.submitList(newList)
//        }

        tabLayout.setOnApplyWindowInsetsListener { view, insets ->
            view.updatePadding(
                top = when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                        view.rootWindowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars()).top
                    }

                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1 -> {
                        view.rootWindowInsets?.stableInsetTop ?: 0
                    }

                    else -> 0
                }
            )
            insets
        }

        sortButton.setOnClickListener {
//            val bottomSheetDialog = BottomSheetDialog(this)
//
//            val view = layoutInflater.inflate(R.layout.bottom_sheet_view, null)
//
//            bottomSheetDialog.window?.let { bottomSheetWindow ->
//                setDecorFitsSystemWindows(bottomSheetWindow, true)
//                EdgeToEdgeUtils.setLightStatusBar(
//                    bottomSheetWindow,
//                    true
//                )
//                EdgeToEdgeUtils.setLightNavigationBar(
//                    bottomSheetWindow,
//                    false
//                )
//                EdgeToEdgeUtils.applyEdgeToEdge(
//                    bottomSheetWindow,
//                    true,
//                    this.resources.getColor(R.color.white, null),
//                    this.resources.getColor(R.color.white, null),
//                )
//            }
//
//            val buttonClose = view.findViewById<LinearLayout>(R.id.close_button)
//
//            buttonClose.setOnClickListener {
//                bottomSheetDialog.dismiss()
//            }
//
//            bottomSheetDialog.setContentView(view)
//
//            bottomSheetDialog.show()

            val bottomSheetDialogFragment = SortBottomSheetDialogFragment()
            bottomSheetDialogFragment.show(supportFragmentManager, TAG)
        }

        val pagerAdapter = PagesAdapter(this)
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabsList.getOrNull(position)?.tabName ?: "Tất cả"
        }.attach()
    }

    private fun addControls() {
//        rvBooks = findViewById(R.id.rv_books)
//        floatingButton = findViewById(R.id.btn_change)
        tabLayout = findViewById(R.id.tab_layout)
        sortButton = findViewById(R.id.sort_button)
        viewPager = findViewById(R.id.view_pager_viet)
    }
}

class SortBottomSheetDialogFragment() :
    BottomSheetDialogFragment(R.layout.bottom_sheet_view) {
    private lateinit var closeButton: LinearLayout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls(view)

        closeButton.setOnClickListener {
            dismiss()
        }
    }

    override fun getTheme() = R.style.CustomBottomSheetDialogTheme

    private fun addControls(view: View) {
        closeButton = view.findViewById(R.id.close_button)
    }

    companion object {
        const val TAG = "SortBottomSheetDialogFragment"
    }
}



