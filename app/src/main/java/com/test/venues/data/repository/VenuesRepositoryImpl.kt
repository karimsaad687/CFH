package com.test.venues.data.repository

import com.test.venues.data.VenuesApi
import com.test.venues.data.dto.VenuesDto
import com.test.venues.domain.repository.VenuesRepository
import javax.inject.Inject

class VenuesRepositoryImpl @Inject constructor(
    private val venuesApi: VenuesApi
) : VenuesRepository {
    override suspend fun Venues(lat: Double, lng: Double): VenuesDto {
        return venuesApi.venues(loc = "$lat,$lng")
    }
}