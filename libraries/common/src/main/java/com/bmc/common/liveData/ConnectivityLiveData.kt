package com.bmc.common.liveData

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData
import com.bmc.common.utils.NetworkUtil

class ConnectivityLiveData(val context: Context) : LiveData<Boolean>() {

    private val cm: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    init {
        value = NetworkUtil.isInternetAvailable(context)
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cm.registerDefaultNetworkCallback(networkCallback)
        } else {
            val networkRequest = NetworkRequest.Builder().build()
            cm.registerNetworkCallback(networkRequest, networkCallback)
        }
    }

    override fun onInactive() {
        super.onInactive()

        cm.unregisterNetworkCallback(networkCallback)
    }
}
