package com.acdm.restcountriescompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acdm.restcountriescompose.domain.Constans
import com.acdm.restcountriescompose.domain.state.ApiState
import com.acdm.restcountriescompose.domain.usecase.ApiUseCase
import com.acdm.restcountriescompose.presentation.intent.CountriesIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject() constructor(
    private val apiUseCase: ApiUseCase
) : ViewModel(){
    private val _state = MutableStateFlow(ApiState())
    val state: StateFlow<ApiState> = _state

    fun processIntent(intent: CountriesIntent){
        when(intent){
            is CountriesIntent.FetchCountries -> fetchCountries()
            else -> {}
        }
    }

    fun fetchCountries() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoadingApi = true)
            try {
                val countries = apiUseCase.invoke()
                _state.value = _state.value.copy(countries = countries, isLoadingApi = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message ?: Constans.UNKNOWN_ERROR,
                    isLoadingApi = false
                )
            }
        }
    }


}