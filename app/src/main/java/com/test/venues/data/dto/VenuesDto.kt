package com.test.venues.data.dto

import java.security.cert.Extension

data class VenuesDto(
    val meta: Meta,
    val response: Response
)

fun VenuesDto.toList():List<Venue>{
    return this.response.venues
}