package com.vietnguyen.data.extensions

import androidx.core.view.isVisible
import com.facebook.shimmer.ShimmerFrameLayout

fun ShimmerFrameLayout.startAndVisibility(isStart: Boolean, isVisible: Boolean) {
    if (isStart) {
        startShimmer()
    } else {
        stopShimmer()
    }

    this.isVisible = isVisible
}
