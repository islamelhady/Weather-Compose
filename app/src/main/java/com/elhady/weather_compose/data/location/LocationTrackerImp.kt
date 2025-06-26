package com.elhady.weather_compose.data.location

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.elhady.weather_compose.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class LocationTrackerImp(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {

    override suspend fun getCurrentLocation(): Location? {
        // 1. Check for permissions first
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasFineLocationPermission && !hasCoarseLocationPermission) {
            return null // Can't get location if we don't have permission
        }

        // 2. Use the modern getCurrentLocation() which actively fetches a fresh location
        return suspendCancellableCoroutine { continuation ->
            locationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY, // Request a highly accurate location
                CancellationTokenSource().token // Allows for cancellation
            ).addOnSuccessListener { location ->
                continuation.resume(location) // On success, resume with the location object
            }.addOnFailureListener {
                continuation.resume(null) // On failure, resume with null
            }.addOnCanceledListener {
                continuation.cancel() // Cancel the coroutine if the request is cancelled
            }
        }
    }
}