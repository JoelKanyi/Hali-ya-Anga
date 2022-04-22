package com.kanyideveloper.haliyaanga.screens.search

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.kanyideveloper.haliyaanga.R
import com.kanyideveloper.haliyaanga.model.Forecastday
import com.kanyideveloper.haliyaanga.screens.common.StandardToolbar
import com.kanyideveloper.haliyaanga.screens.home.HourlyForecast
import com.kanyideveloper.haliyaanga.ui.theme.MyBlue
import com.kanyideveloper.haliyaanga.ui.theme.SecondaryPrimaryDark
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
@Destination(start = false)
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchResult = viewModel.searchSearch.value
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navigator = navigator,
            title = {
                Text(
                    text = "Search",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false
        )

        SearchBar(
            viewModel = viewModel,
            modifier = Modifier
                .fillMaxWidth()
                .height(67.dp)
                .padding(8.dp),
            onSearch = { searchParam ->
                viewModel.searchAll(searchParam)
                keyboardController?.hide()
            }
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    val imageUrl =
                        "https://${searchResult.data?.current?.condition?.icon?.removeRange(0, 2)}"

                    Timber.d("HomeScreen: $imageUrl")

                    Image(
                        painter = rememberImagePainter(
                            data = imageUrl,
                            builder = {
                                placeholder(R.drawable.ic_cloud_day_forecast_rain_rainy_icon)
                                crossfade(true)
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(100.dp),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "${searchResult.data?.current?.tempC}${0x00B0.toChar()}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(9.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        searchResult.data?.current?.condition?.text?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.body1.merge(),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .clip(
                                        shape = RoundedCornerShape(
                                            size = 8.dp,
                                        ),
                                    )
                                    .background(SecondaryPrimaryDark)
                                    .padding(
                                        start = 18.dp,
                                        end = 18.dp,
                                        top = 8.dp,
                                        bottom = 8.dp
                                    )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Wind",
                                fontSize = 12.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "${searchResult.data?.current?.windKph} km/h",
                                fontSize = 14.sp,
                                color = Color.LightGray,
                                textAlign = TextAlign.Center
                            )
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Humidity",
                                fontSize = 12.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "${searchResult.data?.current?.humidity}%",
                                fontSize = 14.sp,
                                color = Color.LightGray,
                                textAlign = TextAlign.Center
                            )
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Precipitation",
                                fontSize = 12.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "${searchResult.data?.current?.precipMm} mm",
                                fontSize = 14.sp,
                                color = Color.LightGray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Divider(
                        color = Color.LightGray,
                        thickness = 0.3.dp,
                        modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                        text = "Hourly Forecast",
                        fontSize = 14.sp,
                        color = Color.White,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        content = {
                            val forecasts: List<Forecastday> =
                                searchResult.data?.forecast?.forecastday ?: emptyList()
                            items(forecasts) { forecast ->
                                forecast.hour.forEach { hour ->
                                    HourlyForecast(
                                        degrees = hour.tempC.toFloat(),
                                        icon = "https://${hour.condition.icon.removeRange(0, 2)}",
                                        time = hour.time
                                    )
                                }
                            }
                        }
                    )
                }
            }

            if (searchResult.isLoading) {

                CircularProgressIndicator(
                    modifier = Modifier.align(CenterHorizontally),
                    color = MyBlue,
                    strokeWidth = 2.dp
                )
            }
        }
    }
}

@Composable
fun SearchBar(
    viewModel: SearchViewModel,
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {

    val searchTerm = viewModel.searchTerm.value

    TextField(
        value = searchTerm,
        onValueChange = {
            viewModel.setSearchTerm(it)
        },
        placeholder = {
            Text(
                text = "Search...",
                color = Color.LightGray
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, CircleShape)
            .background(Color.Transparent, CircleShape),
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            disabledTextColor = Color.Transparent,
            backgroundColor = SecondaryPrimaryDark,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(color = Color.White),
        maxLines = 1,
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { onSearch(searchTerm) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = Color.LightGray,
                    contentDescription = null
                )
            }
        },
    )
}


@Composable
fun WeatherItem(
    locationName: String = "Kitale",
    date: String = "April 21",
    degrees: Float = 21.2f,
    icon: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = locationName,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )

                Text(
                    text = date,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }


            Text(
                text = "${degrees}${0x00B0.toChar()}",
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Image(
                rememberImagePainter(
                    data = icon,
                    builder = {
                        placeholder(R.drawable.ic_cloud_day_forecast_rain_rainy_icon)
                        crossfade(true)
                    }
                ),
                modifier = Modifier.size(30.dp),
                contentDescription = null
            )
        }
    }
}