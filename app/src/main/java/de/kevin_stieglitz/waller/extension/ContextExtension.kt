package de.kevin_stieglitz.waller.extension

import android.content.Context
import android.net.ConnectivityManager


fun Context.checkConnection(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    if (activeNetwork != null) { // connected to the internet
        if (activeNetwork.type == ConnectivityManager.TYPE_WIFI || activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
            return true
        }
    }
    return false
}