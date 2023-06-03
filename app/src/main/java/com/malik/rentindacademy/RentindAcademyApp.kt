package com.malik.rentindacademy

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.malik.rentindacademy.ui.screen.Screen
import com.malik.rentindacademy.ui.screen.about.AboutScreen
import com.malik.rentindacademy.ui.screen.detail.DetailScreen
import com.malik.rentindacademy.ui.screen.home.HomeScreen
import com.malik.rentindacademy.ui.theme.Purple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentindAcademyApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .shadow(8.dp),
                title = {
                    Text(
                        text = "Rentind Academy",
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Purple,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White,
                ),
                navigationIcon = {
                    if (currentRoute != Screen.Home.route) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Default.ArrowBack, "backIcon")
                        }
                    }
                },
                actions = {
                    if (currentRoute == Screen.Home.route) {
                        IconButton(onClick = {
                            navController.navigate(Screen.About.route)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "about_page"
                            )
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navigateToDetail = { classId ->
                    navController.navigate(Screen.Detail.createRoute(classId.toString()))
                })
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("classId") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("classId") ?: ""
                DetailScreen(
                    classId = id as Int,
                )
            }
            composable(
                route = Screen.About.route,
            ) {
                AboutScreen(
                    photoUrl = "https://dicoding-web-img.sgp1.cdn.digitaloceanspaces.com/small/avatar/dos:1ed702c2c9af73692e8ee1934233e71320220531104342.png",
                    name = "Abdul Malik Shodiqin",
                    email = "shodiqinam20d@student.unhas.ac.id",
                )
            }
        }
    }
}