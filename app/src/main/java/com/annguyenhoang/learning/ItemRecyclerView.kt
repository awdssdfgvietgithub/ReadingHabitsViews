package com.annguyenhoang.learning

enum class ItemType(val viewType: Int) {
    BOOK(0),
    AUTHOR(1)
}

interface ItemRecyclerView {
    val id: String
    val viewType: ItemType
}