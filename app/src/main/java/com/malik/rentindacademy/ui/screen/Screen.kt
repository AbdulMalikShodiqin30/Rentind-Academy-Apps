package com.malik.rentindacademy.ui.screen

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Detail: Screen("home/{classId}") {
        fun createRoute(classId: String) = "home/$classId"
    }
    object About: Screen("about")
}