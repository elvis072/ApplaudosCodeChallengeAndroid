package com.example.applaudoscodechallengeandroid.ui

sealed class Screen(val route: String) {
    object TvShowList : Screen("tvShowList")
    object TvShowDetail : Screen("tvShowDetail")
}
