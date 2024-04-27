package com.acdm.restcountriescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.acdm.restcountriescompose.presentation.ui.ContentPrincipalView
import com.acdm.restcountriescompose.presentation.ui.theme.RestCountriesComposeTheme
import com.acdm.restcountriescompose.presentation.viewmodel.ApiViewModel
import com.acdm.restcountriescompose.presentation.viewmodel.DatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val apiViewModel: ApiViewModel by viewModels()
    private val databaseViewModel: DatabaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestCountriesComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    ContentPrincipalView(
                        apiViewModel = apiViewModel,
                        databaseViewModel = databaseViewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}
