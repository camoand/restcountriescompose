@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.acdm.restcountriescompose.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acdm.restcountriescompose.domain.Constans
import com.acdm.restcountriescompose.domain.state.SearchWidgetState
import com.acdm.restcountriescompose.presentation.viewmodel.ApiViewModel
import com.acdm.restcountriescompose.presentation.viewmodel.DatabaseViewModel
import com.acdm.restcountriescompose.presentation.viewmodel.SearchViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopAppBarScreen(
    databaseViewModel: DatabaseViewModel,
    apiViewModel: ApiViewModel,
    navController: NavHostController,
    searchViewModel: SearchViewModel
) {

    val searchWidgetState by searchViewModel.searchWidgetState
    val searchTextState by searchViewModel.searchTextState

    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    searchViewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    searchViewModel.updateSearchTextState(newValue = "")
                    searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSE)
                    databaseViewModel.resetObject()
                },
                onSearchClicked = {
                    if (it.trim().isNotEmpty()) {
                        databaseViewModel.searchCountries(it.trim())
                        searchViewModel.updateSearchTextState(newValue = "")
                        searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                    }
                                  },
                onSearchTriggered = {
                    searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        }, bottomBar = {
        }) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(bottom = 8.dp)
        ) {
            ContentPrincipalView(
                databaseViewModel = databaseViewModel,
                apiViewModel = apiViewModel,
                navController = navController,
            )
        }
    }
}

@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSE -> {
            TopAppBar(onSearchClicked = onSearchTriggered)
        }

        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(onSearchClicked: () -> Unit) {
    CenterAlignedTopAppBar(modifier = Modifier.height(50.dp),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        },
        title = { Text(text = Constans.NAME_APP, color = Color.White) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 20.dp,
        contentColor = Color.Black

    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(DefaultAlpha), text = "Busca aqui",
                    color = Color.White,
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = Color.White,
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier.alpha(DefaultAlpha), onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )

                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        onTextChange("")
                    } else {
                        onCloseClicked()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White
                    )

                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchClicked(text)
            }),
        )
    }
}

@Preview
@Composable
fun ViewTopAppBar() {
    TopAppBar(onSearchClicked = {})
}

@Preview
@Composable
fun ViewSearchBar() {
    SearchAppBar(
        text = "hola",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {})
}
