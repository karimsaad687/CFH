package com.test.venues.domain.use_case.venues_usecase

import android.util.Log
import com.test.venues.common.Resource
import com.test.venues.data.dto.Venue
import com.test.venues.data.dto.VenuesDto
import com.test.venues.domain.repository.VenuesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VenuesUsecase @Inject constructor(
    val venuesRepository: VenuesRepository
) {
    operator fun invoke(lat:Double,lng:Double):Flow<Resource<VenuesDto>> = flow {
        emit(Resource.Loading())
        Log.i("datadata","done 1")
        val result=venuesRepository.Venues(lat, lng)
        emit(Resource.Success(result))
    }
}