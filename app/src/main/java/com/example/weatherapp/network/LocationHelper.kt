package com.example.weatherapp.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.*

class LocationHelper(context: Context) {

    private val client: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun getLocation(onResult: (Double, Double) -> Unit) {

        client.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            null
        ).addOnSuccessListener { location: Location? ->

            if (location != null) {
                onResult(location.latitude, location.longitude)
            } else {
                client.lastLocation.addOnSuccessListener { lastLocation: Location? ->
                    lastLocation?.let {
                        onResult(it.latitude, it.longitude)
                    }
                }
            }
        }
    }
}