package com.test.venues.presentation.Dashboard.Home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.test.venues.common.Resource
import com.test.venues.data.dto.toList
import com.test.venues.domain.use_case.venues_usecase.VenuesUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VenuesViewModel @Inject constructor(
    private val venuesUsecase: VenuesUsecase
) :ViewModel() {


    private val _state = mutableStateOf(VenuesState())
    val state: State<VenuesState> = _state

    fun start(lat:Double,lng:Double){
        venuesUsecase(lat,lng).onEach {
            Log.i("datadata","done")
            when(it){
                is Resource.Success->{
                    _state.value = VenuesState(false,it.data)
                }

                is Resource.Error -> {
                    _state.value = VenuesState(error = it.message ?: "error", isLoading = false)
                }

                is Resource.Loading -> _state.value = VenuesState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }

}