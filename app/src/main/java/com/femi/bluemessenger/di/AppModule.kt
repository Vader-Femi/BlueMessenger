package com.femi.bluemessenger.di

import android.content.Context
import com.femi.bluemessenger.data.chat.AndroidBluetoothController
import com.femi.bluemessenger.domain.chat.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context: Context): BluetoothController{
        return AndroidBluetoothController(context)
    }
}