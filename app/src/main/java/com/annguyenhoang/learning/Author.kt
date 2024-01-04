package com.annguyenhoang.learning

import java.util.UUID

data class Author(
    val id: String = UUID.randomUUID().toString(),
    val authorName: String,
    val viewType: ItemType = ItemType.AUTHOR,
)