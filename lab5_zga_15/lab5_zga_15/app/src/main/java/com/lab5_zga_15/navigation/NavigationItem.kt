package com.lab5_zga_15.navigation

enum class Screen {
    HOME, SETTINGS, CONTACT_LIST, ABOUT, ADD_CONTACT, EDIT_CONTACT
}

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screen.HOME.name)
    data object Settings : NavigationItem(Screen.SETTINGS.name)
    data object ContactList : NavigationItem(Screen.CONTACT_LIST.name)
    data object About : NavigationItem(Screen.ABOUT.name)
    data object AddContact : NavigationItem(Screen.ADD_CONTACT.name)
    data object EditContact : NavigationItem(Screen.EDIT_CONTACT.name)
}
