package com.kanyideveloper.haliyaanga.screens.home

import android.widget.Space
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kanyideveloper.haliyaanga.R
import com.kanyideveloper.haliyaanga.screens.common.StandardToolbar
import com.kanyideveloper.haliyaanga.ui.theme.SecondaryPrimaryDark
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    //viewModel: HomeViewModel = hiltViewModel()
) {
    //val state = viewModel.state.value

    Column(
        Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        StandardToolbar(
            navigator = navigator,
            title = {
                //Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        //Text(text = "Hali ya Anga", color = Color.White)
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(5.dp))

                            Text(text = "Bungoma, Kenya", color = Color.White, fontSize = 18.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Fri, 16 April, 05:56AM",
                        fontSize = 12.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
            navActions = {
/*                IconButton(onClick = {
                    //navigator.navigate(SearchScreenDestination)
                }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        tint = Color.White
                    )
                }*/
            }
        )


        Spacer(modifier = Modifier.height(24.dp))

        Image(
            modifier = Modifier
                .align(CenterHorizontally)
                .size(150.dp),
            painter = painterResource(id = R.drawable.ic_cloud_day_forecast_rain_rainy_icon),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "21${0x00B0.toChar()}",
            fontSize = 40.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sunny",
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
                        top = 10.dp,
                        bottom = 10.dp
                    )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "6 km/h",
                    fontSize = 16.sp,
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
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "30%",
                    fontSize = 16.sp,
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
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "6%",
                    fontSize = 16.sp,
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
            fontSize = 16.sp,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(10) {
                DailyForecast()
            }
        }


/*        if (state.isLoading) {
            CircularProgressIndicator()
        }*/
    }
}

@Composable
fun DailyForecast(
    degree: Float = 0f,
    icon: Int = 0,
    time: String = ""
) {
    Card(
        modifier = Modifier
            .size(width = 100.dp, height = 150.dp)
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
                text = "21${0x00B0.toChar()}",
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = R.drawable.ic_cloud_day_forecast_rain_rainy_icon),
                contentDescription = null
            )
            Text(
                text = "7:00AM",
                fontSize = 14.sp,
                color = Color.LightGray,
                textAlign = TextAlign.Center
            )
        }
    }
}