package com.example.collegeeats.Partner

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.collegeeats.Location.LocationUtils

class LocationCheck(val context: Context,val  activity: Activity) {
    private val locationHandler = Handler()

    private val locationRunnable = object : Runnable {
        override fun run() {
            if (LocationUtils.isLocationEnabled(context)) {

            } else {
                showLocationDialog(context, activity)
            }
            locationHandler.postDelayed(this, 30000) // Check again after 30 seconds
        }
    }

     fun startLocationCheck() {
        locationHandler.post(locationRunnable)
    }

    private fun stopLocationCheck() {
        locationHandler.removeCallbacks(locationRunnable)
    }

    fun check_back_location(order_id: String){
        val permission = Manifest.permission.ACCESS_BACKGROUND_LOCATION
        val permissionStatus = ActivityCompat.checkSelfPermission(context, permission)

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            startLocationService(context, order_id)
        } else {
            // Permission not granted, show dialog and redirect to settings
            val dialogBuilder = AlertDialog.Builder(context)
                .setTitle("Permission Required")
                .setMessage("The app needs permission to access location in background to function properly. please enable all time access to location")
                .setCancelable(true)
                .setPositiveButton("Grant") { _, _ ->
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", context.packageName, null)
                    intent.data = uri
                    context.startActivity(intent)
                }
                .setNegativeButton("Deny") { _, _ ->
                    // Handle denied permission
                }
            dialogBuilder.show()
        }

    }

    fun all_location_related(){
        if (LocationUtils.isLocationEnabled(context)) {
            if (LocationUtils.isBackgroundLocationPermissionGranted(context)) {

            } else {
                LocationUtils.requestLocationPermission(activity)
            }
        } else {
            showLocationDialog(context, activity)
        }
    }

}

 fun startLocationService(context: Context, order_id: String) {
    val intent = Intent(context, BackgroundLocation::class.java)
     intent.putExtra("order_id", order_id)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context.startForegroundService(intent)
    } else {
        context.startService(intent)
    }
}

fun showLocationDialog(context: Context, activity: Activity) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle("Location is turned off")
        .setMessage("Please turn on location to give realtime location to user")
        .setPositiveButton("OK") { dialog, which ->
            activity.startActivityForResult(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0)
        }
        .setCancelable(true)
        .show()
}
