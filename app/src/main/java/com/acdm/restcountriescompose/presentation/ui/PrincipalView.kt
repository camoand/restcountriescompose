package com.acdm.restcountriescompose.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity
import com.acdm.restcountriescompose.domain.state.DatabaseState
import com.acdm.restcountriescompose.presentation.intent.CountriesIntent
import com.acdm.restcountriescompose.presentation.viewmodel.ApiViewModel
import com.acdm.restcountriescompose.presentation.viewmodel.DatabaseViewModel

@Composable
fun ContentPrincipalView(
    apiViewModel: ApiViewModel,
    databaseViewModel: DatabaseViewModel,
    navController: NavHostController
) {

    val countriesStates by apiViewModel.state.collectAsState()
    val countriesDatabaseState by databaseViewModel.stateDatabase.collectAsState()

    if (countriesStates.isLoadingApi) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(Modifier.size(50.dp))
        }
    } else if (countriesStates.countries != null) {
        for (i in countriesStates.countries?.indices!!)
            databaseViewModel.processIntent(CountriesIntent.SaveDatabase(countriesStates.countries!![i]))
        Box(modifier = Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
        ) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(1),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    content = {
                        databaseViewModel.processIntent(CountriesIntent.ReadDatabase)
                        if (countriesDatabaseState.isGetInDatabase) {
                            items(countriesDatabaseState.countriesGetDatabase!!.size) {
                                ItemCharacter(
                                    countriesDatabaseState.countriesGetDatabase!![it],
                                    it,
                                    navController,
                                    databaseViewModel,
                                    countriesDatabaseState
                                )
                            }
                        }
                        /*if (!charactersDatabaseStates.isGetInDatabaseFavorite)
                            databaseViewModel.processIntent(CharacterIntent.ReadDatabase)
                        if (charactersDatabaseStates.isGetInDatabase) {
                            items(charactersDatabaseStates.characterGetDatabase!!.size) {
                                ItemCharacter(
                                    charactersDatabaseStates.characterGetDatabase!![it],
                                    it,
                                    navController,
                                    databaseViewModel,
                                    charactersDatabaseStates
                                )
                            }
                        } else if (charactersDatabaseStates.isGetInDatabaseFavorite) {
                            items(charactersDatabaseStates.characterGetDatabaseFavorite!!.size) {
                                ItemCharacter(
                                    charactersDatabaseStates.characterGetDatabaseFavorite!!.map { it.toDomainChaAttFavorite() }[it],
                                    it,
                                    navController,
                                    databaseViewModel,
                                    charactersDatabaseStates
                                )
                            }
                        }*/
                    })
            }
        }
    } else {
        FetchCountriesButton(apiViewModel)
    }
    LaunchedEffect(false) {
        apiViewModel.processIntent(CountriesIntent.FetchCountries)
    }
}

@Composable
fun ItemCharacter(
    countriesFromApiEntity: CountriesFromApiEntity,
    position: Int,
    navController: NavHostController,
    databaseViewModel: DatabaseViewModel,
    charactersDatabaseStates: DatabaseState
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            Card(shape = MaterialTheme.shapes.medium, modifier = Modifier
                .clickable {
                   /* navController.navigate(Routes.Pantalla2.createRoute(position)) {
                        popUpTo(
                            Routes.Pantalla2.createRoute(
                                position
                            )
                        ) {
                            inclusive = false
                        }
                    }*/
                }
                .height(200.dp)
                .width(300.dp)) {
                AsyncImage(
                    model = countriesFromApiEntity.flags,
                    contentDescription = "photoCharacter",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))

        }
        Text(
            text = countriesFromApiEntity.name, textAlign = TextAlign.Center
        )
        Text(
            text = countriesFromApiEntity.capital, textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FetchCountriesButton(apiViewModel: ApiViewModel) {
    val charactersStates by apiViewModel.state.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Error: " + charactersStates.error, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = { apiViewModel.processIntent(CountriesIntent.FetchCountries) }) {
            Text(text = "Volver a cargar")
        }
    }
}