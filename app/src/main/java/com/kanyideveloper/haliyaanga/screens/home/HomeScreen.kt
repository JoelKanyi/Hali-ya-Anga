package com.kanyideveloper.haliyaanga.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.ehsanmsz.mszprogressindicator.progressindicator.*
import com.kanyideveloper.haliyaanga.R
import com.kanyideveloper.haliyaanga.model.Forecastday
import com.kanyideveloper.haliyaanga.screens.common.StandardToolbar
import com.kanyideveloper.haliyaanga.ui.theme.MyBlue
import com.kanyideveloper.haliyaanga.ui.theme.SecondaryPrimaryDark
import com.kanyideveloper.haliyaanga.util.dayOfTheWeek
import com.kanyideveloper.haliyaanga.util.formatDate
import com.kanyideveloper.haliyaanga.util.formatTime
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        StandardToolbar(
            navigator = navigator,
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(14.dp),
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                text = "${state.data?.location?.name}, ${state.data?.location?.country}",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = formatDate(System.currentTimeMillis()),
                        fontSize = 12.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
        )

        Spacer(modifier = Modifier.height(8.dp))

        val imageUrl = "https://${state.data?.current?.condition?.icon?.removeRange(0, 2)}"

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
                .align(CenterHorizontally)
                .size(100.dp),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${state.data?.current?.tempC}${0x00B0.toChar()}",
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
            state.data?.current?.condition?.text?.let {
                Text(
                    text = it,
                    style = typography.body1.merge(),
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
                    text = "${state.data?.current?.windKph} km/h",
                    fontSize = 14.sp,
                    color = Color.LightGray,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = "Humidity",
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${state.data?.current?.humidity}%",
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
                    text = "${state.data?.current?.precipMm} mm",
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
                val forecasts: List<Forecastday> = state.data?.forecast?.forecastday ?: emptyList()
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

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            text = "Daily Forecast",
            fontSize = 14.sp,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            content = {
                val forecasts: List<Forecastday> = state.data?.forecast?.forecastday ?: emptyList()
                items(forecasts) { forecast ->
                    DailyForecast(
                        degrees = forecast.day.avgtempC.toFloat(),
                        icon = "https://${forecast.day.condition.icon.removeRange(0, 2)}",
                        day = dayOfTheWeek(forecast.date)
                    )
                }
            }
        )

        if (state.isLoading) {
            BallClipRotateMultipleProgressIndicator(
                modifier = Modifier.size(100.dp).align(CenterHorizontally),
                color = MyBlue,
                animationDuration = 800,
                animationDelay = 200,
                minGap = 30.dp,
                maxGap = 40.dp,
                strokeWidth = 1.5.dp,
                //ballCount = 4
            )
        }
    }
}

@Composable
fun HourlyForecast(
    degrees: Float,
    icon: String,
    time: String
) {
    Card(
        modifier = Modifier
            .size(width = 90.dp, height = 140.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = SecondaryPrimaryDark,
        elevation = 5.dp
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
            Text(
                text = formatTime(time),
                fontSize = 12.sp,
                color = Color.LightGray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun DailyForecast(
    degrees: Float,
    icon: String,
    day: String
) {
    Card(
        modifier = Modifier
            .size(width = 90.dp, height = 140.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = SecondaryPrimaryDark,
        elevation = 5.dp
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = day,
                fontSize = 14.sp,
                color = Color.LightGray,
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
            Text(
                text = "${degrees}${0x00B0.toChar()}",
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}