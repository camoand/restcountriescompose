package com.acdm.restcountriescompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acdm.restcountriescompose.core.model.Countries
import com.acdm.restcountriescompose.core.model.toDomainChaAttFromApi
import com.acdm.restcountriescompose.domain.state.DatabaseState
import com.acdm.restcountriescompose.domain.usecase.DbUseCaseGetCountries
import com.acdm.restcountriescompose.domain.usecase.DbUseCaseInsertFromApi
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
    private val getCountriesDatabase: DbUseCaseGetCountries
): ViewModel() {
    private val _stateDatabase = MutableStateFlow(DatabaseState())
    val stateDatabase: StateFlow<DatabaseState> = _stateDatabase


    fun processIntent(intent: CountriesIntent) {
        when (intent) {
         //   is CountriesIntent.FavCharacter -> saveCharacterFav(intent.charactersFav)
            is CountriesIntent.ReadDatabase -> readInDatabase()
           // is CountriesIntent.ReadDatabaseFavotite -> readInDatabaseFavorite()
            is CountriesIntent.SaveDatabase -> saveDatabase(intent.countries)
          //  is CountriesIntent.noFavCharacter -> deleteCharacterFav(intent.charactersFav)
            else -> {}
        }

    }

    private fun saveDatabase(countries: Countries) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: List<Countries> = listOf(countries)
            try {
                insertFromApi.invoke(response.map { it.toDomainChaAttFromApi() })
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
                        isGetInDatabaseFavorite = false,
                        countriesGetDatabase = response
                    )
                }
            } catch (e: Exception) {
                _stateDatabase.value = _stateDatabase.value.copy(isGetInDatabase = false)
            }
        }
    }

}