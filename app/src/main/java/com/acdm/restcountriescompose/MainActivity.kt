package com.acdm.restcountriescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.acdm.restcountriescompose.core.model.Routes
import com.acdm.restcountriescompose.domain.Constans
import com.acdm.restcountriescompose.presentation.ui.TopAppBarScreen
import com.acdm.restcountriescompose.presentation.ui.ViewDetailsCountries
import com.acdm.restcountriescompose.presentation.ui.theme.RestCountriesComposeTheme
import com.acdm.restcountriescompose.presentation.viewmodel.ApiViewModel
import com.acdm.restcountriescompose.presentation.viewmodel.DatabaseViewModel
import com.acdm.restcountriescompose.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val apiViewModel: ApiViewModel by viewModels()
    private val databaseViewModel: DatabaseViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestCountriesComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.Screen1.routes
                )
                {
                    composable(Routes.Screen1.routes) {
                        TopAppBarScreen(
                            databaseViewModel = databaseViewModel,
                            apiViewModel = apiViewModel,
                            navController = navController,
                            searchViewModel = searchViewModel
                        )
                    }
                    composable(
                        Routes.Screen2.routes,
                        arguments = listOf(navArgument(Constans.ID) { type = NavType.IntType })
                    ) { backStackEntry ->
                        ViewDetailsCountries(
                            databaseViewModel = databaseViewModel,
                            navController = navController,
                            id = backStackEntry.arguments?.getInt(Constans.ID) ?: 0
                        )
                    }
                }

            }
        }
    }
}
