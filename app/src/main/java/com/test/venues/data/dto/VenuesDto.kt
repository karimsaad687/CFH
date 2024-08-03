package com.test.venues.data.dto

data class VenuesDto(
    val meta: Meta,
    val response: Response
)

fun VenuesDto.toList(): List<Venue> {
    return this.response.venues
}