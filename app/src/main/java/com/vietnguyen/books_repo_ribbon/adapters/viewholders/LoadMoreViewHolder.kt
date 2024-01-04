package com.vietnguyen.books_repo_ribbon.adapters.viewholders

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.fashiongallery.R

class LoadMoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val linearProgressIndicator: ProgressBar =
        view.findViewById(R.id.progress_bar_load_more)
}