package com.femi.bluemessenger.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.femi.bluemessenger.domain.chat.BluetoothDevice
import com.femi.bluemessenger.presentation.BluetoothUiState
import com.femi.bluemessenger.presentation.uicomponents.ChatScreen
import com.femi.bluemessenger.presentation.uicomponents.LoadingScreen
import com.femi.bluemessenger.presentation.uicomponents.ScanScreen

@Composable
fun NavigationComponent(
    navController: NavHostController,
    state: BluetoothUiState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onDeviceClicked: (BluetoothDevice) -> Unit,
    onStartServer: () -> Unit,
    onDisconnect: () -> Unit,
    onSendMessage: (message: String) -> Unit,
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
                onStopScan = onStopScan,
                onDeviceClicked = onDeviceClicked,
                onStartServer = onStartServer
            )
        }
        composable(route = Screen.LoadingScreen.route) {
            LoadingScreen(
                state = state
            )
        }
        composable(route = Screen.ChatScreen.route) {
            ChatScreen(
                state = state,
                onDisconnect = onDisconnect,
                onSendMessage = onSendMessage
            )
        }
    }
}