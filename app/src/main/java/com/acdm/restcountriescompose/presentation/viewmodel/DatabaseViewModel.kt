package com.acdm.restcountriescompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acdm.restcountriescompose.core.model.Countries
import com.acdm.restcountriescompose.core.model.toDomainChaAttFromApi
import com.acdm.restcountriescompose.domain.Constans
import com.acdm.restcountriescompose.domain.state.DatabaseState
import com.acdm.restcountriescompose.domain.usecase.database.DbUseCaseGetCountries
import com.acdm.restcountriescompose.domain.usecase.database.DbUseCaseInsertFromApi
import com.acdm.restcountriescompose.domain.usecase.database.DbUseCaseSearchCountries
import com.acdm.restcountriescompose.presentation.intent.CountriesIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val insertFromApi: DbUseCaseInsertFromApi,
    private val getCountriesDatabase: DbUseCaseGetCountries,
    private val searchViewModel: DbUseCaseSearchCountries
): ViewModel() {
    private val _stateDatabase = MutableStateFlow(DatabaseState())
    val stateDatabase: StateFlow<DatabaseState> = _stateDatabase


    fun processIntent(intent: CountriesIntent) {
        when (intent) {
            is CountriesIntent.ReadDatabase -> readInDatabase()
            is CountriesIntent.SaveDatabase -> saveDatabase(intent.countries)
            else -> {}
        }

    }

    private fun saveDatabase(countries: Countries) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: List<Countries> = listOf(countries)
            try {
                insertFromApi.invoke(response.map {it.toDomainChaAttFromApi()})
                _stateDatabase.value = _stateDatabase.value.copy(isSuccessInDatabase = true)
            } catch (e: Exception) {
                _stateDatabase.value = _stateDatabase.value.copy(isSuccessInDatabase = false)
            }

        }
    }

    private fun readInDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getCountriesDatabase.invoke()
                if (response.isNotEmpty()) {
                    _stateDatabase.value = _stateDatabase.value.copy(
                        isGetInDatabase = true,
                        countriesGetDatabase = response
                    )
                }
            } catch (e: Exception) {
                _stateDatabase.value = _stateDatabase.value.copy(isGetInDatabase = false)
            }
        }
    }

    fun resetObject(){
        _stateDatabase.value = _stateDatabase.value.copy(
            countriesGetSearchDatabase = null
        )
    }



    fun searchCountries(search: String) {
        viewModelScope.launch {
            try {
                val countries = searchViewModel.invoke(search)
                _stateDatabase.value = _stateDatabase.value.copy(countriesGetSearchDatabase = countries, isSearch = true)
            } catch (e: Exception) {
                _stateDatabase.value = _stateDatabase.value.copy(
                    error = e.message ?: Constans.UNKNOWN_ERROR,
                )
            }
        }
    }
}