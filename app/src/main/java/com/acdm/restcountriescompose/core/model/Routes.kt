package com.acdm.restcountriescompose.core.model

sealed class Routes(val routes: String){

    object Screen1:Routes("screen_1")
    object Screen2:Routes("screen_2/{id}"){
        fun createRoute(id:Int) = "screen_2/$id"
    }
}
