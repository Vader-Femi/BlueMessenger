package com.femi.bluemessenger.presentation.uicomponents

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
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.femi.bluemessenger.domain.chat.BluetoothDevice
import com.femi.bluemessenger.presentation.BluetoothUiState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScanScreen(
    state: BluetoothUiState,
    widthSizeClass: WindowWidthSizeClass,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        when (widthSizeClass){
            WindowWidthSizeClass.Compact -> {
                PhoneBluetoothDeviceList(
                    pairedDevices = state.pairedDevices,
                    scannedDevices = state.scannedDevices,
                    onDeviceClicked = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
            else -> {
                TabletBluetoothDeviceList(
                    pairedDevices = state.pairedDevices,
                    scannedDevices = state.scannedDevices,
                    onDeviceClicked = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }

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

            repeat(2){
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .padding(4.dp),
                ) {
                    Text(text = "Rand Button ${it + 3}")
                }
            }
        }
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