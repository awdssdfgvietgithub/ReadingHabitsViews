package com.vietnguyen.homeribbon.data.models

data class TabModel(
    val id: Int,
    val tabName: String,
) {
    companion object {
        fun mock() = listOf(
            TabModel(0, "Tất cả"),
            TabModel(1, "Sách chữ"),
            TabModel(2, "Sách hình"),
//            TabModel(3, "Sách nói")
        )
    }
}
