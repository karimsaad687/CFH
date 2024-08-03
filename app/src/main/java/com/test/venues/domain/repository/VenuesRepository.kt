package com.test.venues.domain.repository

import com.test.venues.data.dto.VenuesDto

interface VenuesRepository {
    suspend fun Venues(lat: Double, lng: Double): VenuesDto
}