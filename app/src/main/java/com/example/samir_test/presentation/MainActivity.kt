package com.example.samir_test.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.samir_test.presentation.component.AppNavigation
import com.example.samir_test.presentation.theme.SamirtestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SamirtestTheme {
                MyApplication()
            }
        }
    }
}

@Composable
fun MyApplication() {
    val navController = rememberNavController()
    AppNavigation(navController = navController)
}