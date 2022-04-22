package com.kanyideveloper.haliyaanga.screens.settings

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kanyideveloper.haliyaanga.screens.common.StandardToolbar
import com.kanyideveloper.haliyaanga.ui.theme.MyBlue
import com.kanyideveloper.haliyaanga.ui.theme.SecondaryPrimaryDark
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import javax.inject.Inject

@Destination
@Composable
fun SettingScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val showDialog = remember { mutableStateOf(false) }
    val locations = viewModel.locations.observeAsState().value ?: emptyList()
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        StandardToolbar(
            navigator = navigator,
            title = {
                Text(
                    text = "Location settings",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            },
            showBackArrow = false,
            navActions = {
                Row(
                ) {
                    IconButton(onClick = {
                        showDialog.value = true
                    }) {

                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        )

        LazyColumn(
            verticalArrangement = Arrangement.Top,
        ) {
            items(locations) { location ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(5.dp)
                        .clickable {
                            viewModel.savedToSharedPrefs(location.locationName)
                            Toast
                                .makeText(
                                    context,
                                    "${location.locationName} selected as the default location",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        },
                    backgroundColor = if (location.locationName == viewModel.currentLoc) {
                        MyBlue
                    } else SecondaryPrimaryDark,
                    elevation = 5.dp,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = location.locationName,
                            color = Color.White
                        )
                        if (location.locationName == viewModel.currentLoc) {
                            Icon(imageVector = Icons.Default.Check, tint = Color.White, contentDescription = null)
                        }
                    }
                }
            }
        }


        if (showDialog.value) {
            AlertDialog(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                onDismissRequest = {
                    showDialog.value = false
                },
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Add City",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        TextField(
                            value = viewModel.textFieldValue.value,
                            onValueChange = {
                                viewModel.setTextFieldValue(it)
                            },
                            textStyle = TextStyle(color = Color.White),
                            label = { Text(text = "Enter your city", color = Color.LightGray) },
                            placeholder = { Text(text = "Nairobi", color = Color.LightGray) },
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.insertLocation()
                            showDialog.value = false
                        },
                        colors = ButtonDefaults.buttonColors(MyBlue)
                    ) {
                        Text(text = "Save", color = Color.White)
                    }
                },
                backgroundColor = SecondaryPrimaryDark,
                contentColor = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
        }
    }
}