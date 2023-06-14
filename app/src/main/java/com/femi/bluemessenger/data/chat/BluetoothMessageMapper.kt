package com.femi.bluemessenger.data.chat

import com.femi.bluemessenger.domain.chat.BluetoothMessage

fun BluetoothMessage.toByteArray(): ByteArray {
    return "$senderName#$message".encodeToByteArray()
}

fun String.toBluetoothMessage(isFromLocalUser: Boolean): BluetoothMessage{
    val name = substringBeforeLast("#")
    val message = substringAfter("#")

    return BluetoothMessage(
        senderName = name,
        message = message,
        isFromLocalUser = isFromLocalUser
    )
}