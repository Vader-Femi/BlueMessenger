package com.femi.bluemessenger.presentation.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.femi.bluemessenger.domain.chat.BluetoothMessage
import com.femi.bluemessenger.ui.theme.BlueMessengerTheme

@Composable
fun ChatMessage(
    message: BluetoothMessage,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = if (message.isFromLocalUser) 15.dp else 0.dp,
                    topEnd = 15.dp,
                    bottomStart = 15.dp,
                    bottomEnd = if (message.isFromLocalUser) 0.dp else 15.dp,
                )
            )
            .background(
                if (message.isFromLocalUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            )
            .padding(16.dp)
    ) {
        Text(
            text = message.senderName,
            fontSize = 10.sp
        )
        Text(
            text = message.message,
            modifier = Modifier.widthIn(max = 250.dp)
        )
    }
}

@Preview
@Composable
fun ChatMessagePreview() {
    BlueMessengerTheme {
        ChatMessage(
            message = BluetoothMessage(
                message = "Hello Compose!",
                senderName = "Preview Sender",
                isFromLocalUser = true
            )
        )
    }
}