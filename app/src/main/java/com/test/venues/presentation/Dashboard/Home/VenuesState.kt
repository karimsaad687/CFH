package com.test.venues.presentation.Dashboard.Home

import com.test.venues.data.dto.Venue
import com.test.venues.data.dto.VenuesDto

data class VenuesState(
    val isLoading: Boolean? = false,
    val data: VenuesDto? = null,
    val error: String = ""
)
