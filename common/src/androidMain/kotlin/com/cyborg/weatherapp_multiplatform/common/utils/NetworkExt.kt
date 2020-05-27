package com.cyborg.weatherapp_multiplatform.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import com.cyborg.weatherapp_multiplatform.common.di.InjectorCommon

actual class ContextArgs(
    var context: Context
)

@SuppressLint("MissingPermission")
actual fun isNetworkAvailable(): Boolean {
    val connectivityManager =
        InjectorCommon.contextArgs.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting

}