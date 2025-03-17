package com.lab5_zga_15.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lab5_zga_15.navigation.NavigationItem
import com.lab5_zga_15.ui.*

@Composable
fun Page(
    title: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (title) {
        "Home" -> HomeColor
        "Settings" -> SettingsColor
        "Contact List" -> Color.Black
        "About" -> AboutColor
        else -> Color.LightGray
    }

    if (title == "Settings") {
        BackHandler {
            navController.popBackStack(NavigationItem.Home.route, false)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))


        if (title != "Contact List") {
            Text(title, fontSize = 36.sp, color = if (backgroundColor == Color.Black) Color.White else Color.Black)
        }


        if (title == "Contact List") {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Contacts List", fontSize = 24.sp, color = Color.White)
                }


                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Add Contact", fontSize = 24.sp, color = Color.White)
                }
            }
        }
    }
}
