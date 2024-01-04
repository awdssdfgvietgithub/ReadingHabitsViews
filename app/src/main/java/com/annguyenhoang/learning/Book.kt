package com.annguyenhoang.learning

import java.util.UUID

data class Book(
    val id: String = UUID.randomUUID().toString(),
    val bookName: String = "",
    val authorName: String = "",
    val viewType: ItemType = ItemType.BOOK,
)

