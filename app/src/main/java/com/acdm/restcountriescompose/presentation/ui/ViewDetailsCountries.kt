package com.acdm.restcountriescompose.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.acdm.restcountriescompose.core.database.entity.CountriesFromApiEntity
import com.acdm.restcountriescompose.core.model.Routes
import com.acdm.restcountriescompose.presentation.viewmodel.DatabaseViewModel

@Composable
fun ViewDetailsCountries(
    databaseViewModel: DatabaseViewModel,
    navController: NavHostController,
    id: Int
) {
    val countriesStates by databaseViewModel.stateDatabase.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Close",
            tint = Color.Black,
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    navController.navigate(Routes.Screen1.routes) {
                        popUpTo(
                            Routes.Screen2.routes
                        ) {
                            inclusive = true
                        }
                    }
                }
        )
        Spacer(modifier = Modifier.padding(5.dp))
        CardCountries(countriesStates.countriesGetDatabase!!, id)

    }

}
@Composable
fun CardCountries(countriesFromApiEntity: List<CountriesFromApiEntity>, id: Int) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .height(200.dp)
            .width(300.dp)
    ) {
        AsyncImage(
            model = countriesFromApiEntity[id].flags,
            contentDescription = "flags Countries",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
    }
    Spacer(modifier = Modifier.padding(4.dp))
    Text(
        text = countriesFromApiEntity[id].name,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.padding(5.dp))
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 8.dp)
    ) {
        TextViewDescription(
           atribute = "Nombre Oficial: ",
           description = countriesFromApiEntity[id].nameOfficial
       )
        TextViewDescription(
            atribute = "capital: ",
            description = countriesFromApiEntity[id].capital
        )
        TextViewDescription(
            atribute = "region: ",
            description = countriesFromApiEntity[id].region
        )
        TextViewDescription(
            atribute = "subregion: ",
            description = countriesFromApiEntity[id].subregion
        )

        TextViewDescription(
            atribute = "Fronteras: ",
            description = countriesFromApiEntity[id].borders
        )

        TextViewDescription(
             atribute = "Area: ",
             description = countriesFromApiEntity[id].area + "Km"
        )
        TextViewDescription(
            atribute = "Población: ",
            description = countriesFromApiEntity[id].population + " de habitantes"
        )
    }
}

@Composable
fun TextViewDescription(atribute: String, description: String) {
    Spacer(modifier = Modifier.padding(8.dp))
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                )
            ) {
                append(atribute)
            }
            withStyle(
                style = SpanStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace,
                )
            ) {
                append(description)
            }

        }
    )
}