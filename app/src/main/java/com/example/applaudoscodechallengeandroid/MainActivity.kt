package com.example.applaudoscodechallengeandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.applaudoscodechallengeandroid.common.Constants
import com.example.applaudoscodechallengeandroid.ui.Screen
import com.example.applaudoscodechallengeandroid.ui.theme.ApplaudosCodeChallengeAndroidTheme
import com.example.applaudoscodechallengeandroid.ui.tvshowdetail.TvShowDetailScreen
import com.example.applaudoscodechallengeandroid.ui.tvshowlist.TvShowListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplaudosCodeChallengeAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TvShowList.route
                    ) {
                        composable(route = Screen.TvShowList.route) {
                            TvShowListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.TvShowDetail.route + "/{${Constants.TV_SHOW_ID_KEY}}",
                            arguments = listOf(
                                navArgument(Constants.TV_SHOW_ID_KEY) {
                                    defaultValue = 0
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            TvShowDetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}