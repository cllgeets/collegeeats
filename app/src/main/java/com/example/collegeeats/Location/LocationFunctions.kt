package com.example.collegeeats.Location

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.collegeeats.Delivery.delivery_functions
import com.example.collegeeats.Repositories.AddressRepository
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationFunctions(val context: Context) {

    private val locationRepository = AddressRepository()
    val locationutils = LocationUtils

    fun screenTopLocationShowing(uid: String, textView: TextView) {
        CoroutineScope(Dispatchers.Main).launch {
            val manualAddress = locationRepository.getManualAddress(uid)
            val locationAddress = locationRepository.getLocationAddress(uid)
            textView.text = when {
                manualAddress.isNullOrEmpty() && locationAddress.isNullOrEmpty() -> "Your location here"
                manualAddress.isNullOrEmpty() -> locationAddress
                locationAddress.isNullOrEmpty() -> manualAddress
                else -> manualAddress
            }
        }
    }

    fun checkLocation(
        activity: FragmentActivity,
        dialog: Dialog,
        context: Context,
        fusedLocationClient: FusedLocationProviderClient
    ) {

        if (locationutils.isLocationEnabled(context)) {
            if (locationutils.isLocationPermissionGranted(context)) {
                delivery_functions(context).fetchLocationAndData(fusedLocationClient)
            } else {
                locationutils.requestLocationPermission(activity)
            }
        } else {
            LocationDialogUtils.showLocationDialog(dialog)
        }
    }
}