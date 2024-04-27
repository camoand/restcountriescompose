package com.acdm.restcountriescompose.presentation.intent

import com.acdm.restcountriescompose.core.model.Countries

sealed class CountriesIntent{

    object FetchCountries: CountriesIntent()
    object ReadDatabase: CountriesIntent()
    data class SaveDatabase(var countries: Countries) : CountriesIntent()

}
