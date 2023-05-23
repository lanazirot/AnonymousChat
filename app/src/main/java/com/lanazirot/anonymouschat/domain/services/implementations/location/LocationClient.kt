package com.lanazirot.anonymouschat.domain.services.implementations.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.lanazirot.anonymouschat.domain.services.interfaces.location.ILocationClient
import com.lanazirot.anonymouschat.ui.extensions.hasLocationPermission
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class LocationClient(
    private val context: Context,
    private val client: FusedLocationProviderClient
): ILocationClient {
    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(interval: Long): Flow<Location> {
       return callbackFlow {
           if (!context.hasLocationPermission()) {
               throw ILocationClient.LocationException("Missing location permission")
           }

           val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
           val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
           val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

           Log.d("LocationClient", "GPS enabled: $isGpsEnabled")
           Log.d("LocationClient", "Network enabled: $isNetworkEnabled")

           //Check for gps and network, if they are disabled throw an exception
           if (!isGpsEnabled) throw ILocationClient.GPSDisabledException("GPS is disabled")
           if (!isNetworkEnabled) throw ILocationClient.NetworkDisabledException("Network is disabled")

           val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, interval).apply {
               setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
               setWaitForAccurateLocation(true)
           }.build()

           val locationCallback = object : LocationCallback() {
               override fun onLocationResult(result: LocationResult) {
                   super.onLocationResult(result)
                   result.locations.lastOrNull().let { location ->
                       when {
                           location != null -> launch { send(location) }
                           else -> throw ILocationClient.LocationException("Location is null")
                       }
                   }
               }
           }

           client.requestLocationUpdates(
               request,
               locationCallback,
               Looper.getMainLooper()
           )

           awaitClose {
               client.removeLocationUpdates(locationCallback)
           }
       }
    }
}