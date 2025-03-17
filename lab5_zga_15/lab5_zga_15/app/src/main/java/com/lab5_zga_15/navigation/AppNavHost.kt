package com.lab5_zga_15.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lab5_zga_15.screens.Page

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route,
        modifier = modifier
    ) {
        composable(NavigationItem.Home.route) { Page("Home", navController) }
        composable(NavigationItem.Settings.route) { Page("Settings", navController) }
        composable(NavigationItem.ContactList.route) { Page("Contact List", navController) }
        composable(NavigationItem.About.route) { Page("About", navController) }
    }
}
