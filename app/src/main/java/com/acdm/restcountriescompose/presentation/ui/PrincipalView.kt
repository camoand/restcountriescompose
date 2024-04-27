package com.acdm.restcountriescompose.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.acdm.restcountriescompose.presentation.viewmodel.ApiViewModel

@Composable
fun ContentPrincipalView(
    apiViewModel: ApiViewModel
){

    val countriesStates by apiViewModel.state.collectAsState()

    if (countriesStates.isLoadingApi) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(Modifier.size(50.dp))
        }
    }else if (countriesStates.countries != null){
        Box(modifier = Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
        ) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                LazyVerticalGrid(columns = GridCells.Adaptive(120.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),

                    content = {
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
    }
}