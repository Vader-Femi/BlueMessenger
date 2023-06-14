package com.femi.bluemessenger.presentation.uicomponents

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.femi.bluemessenger.R
import com.femi.bluemessenger.domain.chat.BluetoothDevice
import com.femi.bluemessenger.presentation.BluetoothUiState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScanScreen(
    state: BluetoothUiState,
    widthSizeClass: WindowWidthSizeClass,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onDeviceClicked: (BluetoothDevice) -> Unit,
    onStartServer: () -> Unit,
    onDisconnect: () -> Unit,
    onSendMessage: (message: String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current

        LaunchedEffect(key1 = state.errorMessage) {
            state.errorMessage?.let { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
        LaunchedEffect(key1 = state.isConnected) {
            if (state.isConnected) {
                Toast.makeText(context, R.string.connected, Toast.LENGTH_SHORT).show()
            }
        }

        when {
            state.isConnecting -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    Text(text = stringResource(R.string.connecting))
                }

            }
            state.isConnected -> {
                ChatScreen(
                    state = state,
                    onDisconnect = onDisconnect,
                    onSendMessage = onSendMessage
                )
            }

        }

        when (widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                PhoneBluetoothDeviceList(
                    pairedDevices = state.pairedDevices,
                    scannedDevices = state.scannedDevices,
                    onDeviceClicked = onDeviceClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }

            else -> {
                TabletBluetoothDeviceList(
                    pairedDevices = state.pairedDevices,
                    scannedDevices = state.scannedDevices,
                    onDeviceClicked = onDeviceClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }

        bottomButtons(
            onStartScan = onStartScan,
            onStopScan = onStopScan,
            onStartServer = onStartServer
         )

    }
}

@Composable
fun PhoneBluetoothDeviceList(
    pairedDevices: List<BluetoothDevice>,
    scannedDevices: List<BluetoothDevice>,
    onDeviceClicked: (BluetoothDevice) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {

        item {
            Text(
                text = "Scanned Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        items(scannedDevices) { device ->
            Text(
                text = device.name ?: device.address,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDeviceClicked(device) }
                    .padding(14.dp)
            )
        }


        item {
            Text(
                text = "Paired Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        items(pairedDevices) { device ->
            Text(
                text = device.name ?: device.address,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDeviceClicked(device) }
                    .padding(14.dp)
            )
        }

    }

}

@Composable
fun TabletBluetoothDeviceList(
    pairedDevices: List<BluetoothDevice>,
    scannedDevices: List<BluetoothDevice>,
    onDeviceClicked: (BluetoothDevice) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            item {
                Text(
                    text = "Scanned Devices",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(scannedDevices) { device ->
                Text(
                    text = device.name ?: device.address,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDeviceClicked(device) }
                        .padding(14.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            item {
                Text(
                    text = "Paired Devices",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(pairedDevices) { device ->
                Text(
                    text = device.name ?: device.address,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDeviceClicked(device) }
                        .padding(14.dp)
                )
            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun bottomButtons(
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onStartServer: () -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onStartScan,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(4.dp),
        ) {
            Text(text = "Start Scan")
        }
        Button(
            onClick = onStopScan,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(4.dp),
        ) {
            Text(text = "Stop Scan")
        }
        Button(
            onClick = onStartServer,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(4.dp),
        ) {
            Text(text = "Start Server")
        }
    }
}