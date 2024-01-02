package com.vietnguyen.books_repo_ribbon.components

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class TabFragment(layoutId: Int) : Fragment(layoutId) {

    abstract fun initViews(view: View)
    abstract fun viewControls()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        viewControls()
    }

}