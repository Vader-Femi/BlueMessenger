package com.femi.bluemessenger.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.femi.bluemessenger.presentation.BluetoothUiState
import com.femi.bluemessenger.presentation.uicomponents.ScanScreen

@Composable
fun NavigationComponent(
    navController: NavHostController,
    state: BluetoothUiState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.ScanBluetoothScreen.route,
        route = Screen.BluetoothRoute.route
    ) {
        composable(route = Screen.ScanBluetoothScreen.route) {
            ScanScreen(
                state = state,
                widthSizeClass = widthSizeClass,
                onStartScan = onStartScan,
                onStopScan = onStopScan
            )
        }
    }
}