package com.acdm.restcountriescompose.domain.state

import com.acdm.restcountriescompose.core.model.Countries

data class ApiState(
    val isLoadingApi: Boolean = false,
    val countries: Countries? = null,
    val error: String
)