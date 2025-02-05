package com.example.samir_test.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.samir_test.domain.model.LoanData
import com.example.samir_test.presentation.route.AppRoute
import com.example.samir_test.presentation.screen.HomeScreen
import com.example.samir_test.presentation.screen.DetailScreen
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppRoute.Home.route
    ) {
        composable(
            route = AppRoute.Home.route
        ) {
            HomeScreen(
                onNavigateToDetail = {
                    val json = Gson().toJson(it)
                    val encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
                    navController.navigate("${AppRoute.Detail.route}/$encodedJson")
                }
            )
        }
        composable(
            route = "${AppRoute.Detail.route}/{loanDetail}",
            arguments = listOf(navArgument("loanDetail") { type = NavType.StringType})
        ) { backStackEntry ->
            val loanDetailJson = backStackEntry.arguments?.getString("loanDetail")
            val loanDetail = Gson().fromJson(loanDetailJson, LoanData::class.java)
            DetailScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                loanDetail = loanDetail
            )
        }
    }

}