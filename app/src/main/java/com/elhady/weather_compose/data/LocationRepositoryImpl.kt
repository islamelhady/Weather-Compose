package com.elhady.weather_compose.data

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.elhady.weather_compose.domain.entites.Location as WeatherLocation
import com.elhady.weather_compose.domain.repository.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.util.Locale
import kotlin.coroutines.resume

class LocationRepositoryImpl(
    private val context: Context,
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationRepository {

    @Suppress("MissingPermission")
    override suspend fun getLocation(): WeatherLocation {
        if (!hasLocationPermission()) {
            throw SecurityException("Location permission not granted.")
        }
        if (!isLocationServiceEnabled()) {
            throw IllegalStateException("Location services are disabled.")
        }

        val androidLocation: android.location.Location? = suspendCancellableCoroutine { continuation ->
            val cts = CancellationTokenSource()
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cts.token
            ).addOnSuccessListener { location ->
                continuation.resume(location)
            }.addOnFailureListener {
                continuation.resume(null)
            }

            continuation.invokeOnCancellation {
                cts.cancel()
            }
        }

        val location = androidLocation ?: throw Exception("Could not retrieve location.")

        return withContext(Dispatchers.IO) {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = try {
                geocoder.getFromLocation(location.latitude, location.longitude, 1)
            } catch (e: Exception) {
                null
            }

            val cityName = addresses?.firstOrNull()?.let {
                it.locality ?: it.adminArea ?: it.subAdminArea
            } ?: "Unknown Location"

            WeatherLocation(
                latitude = location.latitude,
                longitude = location.longitude,
                city = cityName
            )
        }
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isLocationServiceEnabled(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
}