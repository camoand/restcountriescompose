package com.acdm.restcountriescompose.domain.state

import com.acdm.restcountriescompose.core.model.Countries
import com.acdm.restcountriescompose.domain.Constans

data class ApiState(
    val isLoadingApi: Boolean = false,
    val countries: Countries? = null,
    val error: String = Constans.ERROR_MESSAGE
)