package com.acdm.restcountriescompose.core.data

import com.acdm.restcountriescompose.core.model.Countries
import com.acdm.restcountriescompose.domain.Constans
import retrofit2.http.GET

interface ApiService {

    @GET(Constans.COUNTRIES)
    suspend fun getCountries(): Countries

}