package com.bbeniful.navigationexample.second_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController

@Composable
fun SecondScreen(navController: NavController) {
    val data = navController.currentBackStackEntryFlow.collectAsState(initial = null)
    val text = data.value?.arguments?.getString("data")
    Text(text = text ?: " ")

}