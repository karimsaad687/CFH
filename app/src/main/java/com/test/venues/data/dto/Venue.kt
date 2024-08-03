package com.test.venues.data.dto

data class Venue(
    val categories: List<Category>,
    val createdAt: Int,
    val id: String,
    val location: Location,
    val name: String
)