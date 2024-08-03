package com.test.venues.data.dto

data class Response(
    val confident: Boolean,
    val venues: List<Venue>
)