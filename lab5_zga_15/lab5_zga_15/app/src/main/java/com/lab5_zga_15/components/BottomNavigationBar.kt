package com.lab5_zga_15.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.lab5_zga_15.navigation.NavigationItem

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(NavigationItem.Settings.route) },
            label = { Text("Settings") },
            icon = {}
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(NavigationItem.ContactList.route) },
            label = { Text("Contacts") },
            icon = {}
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(NavigationItem.About.route) },
            label = { Text("About") },
            icon = {}
        )
    }
}
