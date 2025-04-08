package com.example.photogallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.photogallery.ui.FullPhotoViewScreen
import com.example.photogallery.ui.ViewGridScreen
import com.example.photogallery.ui.theme.PhotoGalleryTheme

/**
 * Name: Võ Anh Khoa
 * ID: 21110046
 * Class: 02FIE - Tuesday Afternoon
 * Date: 8/4/2025
 * Assignment 6: The photo gallery app
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoGalleryTheme {
                PhotoGalleryApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoGalleryApp(navController: NavHostController = rememberNavController()) {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            )
    ) {
        NavHost(
            navController = navController,
            startDestination = "grid"
        ) {
            composable(route = "grid") {
                ViewGridScreen(navController)
            }
            composable(
                route = "photo/{index}",
                arguments = listOf(
                    navArgument("index") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val index = backStackEntry.arguments?.getInt("index") ?: 0
                FullPhotoViewScreen(index, navController)
            }
        }
    }
}