package com.test.venues.data

import com.test.venues.data.dto.VenuesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface VenuesApi {

    @GET("venues/search")
    suspend fun venues(
        @Query("ll") loc: String,
        @Query("client_id") client_id: String? = "4EQRZPSGKBZGFSERGJY055FRW2OSPJRZYR4C3J0JN2CQQFIV",
        @Query("client_secret") client_secret: String? = "AJR4B5LLRONWAJWJJOACHAFLCWS2YJAZMGQNFFZQP0IB3THR",
        @Query("v") v: String? = "20180910"
    ): VenuesDto
}