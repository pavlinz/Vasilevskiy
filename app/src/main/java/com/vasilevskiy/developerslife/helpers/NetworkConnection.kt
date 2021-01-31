@file:Suppress("DEPRECATION")

package com.vasilevskiy.developerslife.helpers

import android.content.Context
import android.net.wifi.WifiManager

class NetworkConnection(private val context: Context) {

    var wifiManager: WifiManager? = context.getSystemService(Context.WIFI_SERVICE) as WifiManager?

    fun checkConnection() : Boolean? = wifiManager?.isWifiEnabled
}