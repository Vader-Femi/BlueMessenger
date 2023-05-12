package com.femi.bluemessenger.navigation

sealed class Screen(val route: String) {
    object BluetoothRoute : Screen("bluetooth_route")
    object ScanBluetoothScreen : Screen("scan_bluetooth_screen")

    fun withArgs(vararg args: Pair<String, String>): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("?${arg.first}=${arg.second}")
            }
        }
    }
}
