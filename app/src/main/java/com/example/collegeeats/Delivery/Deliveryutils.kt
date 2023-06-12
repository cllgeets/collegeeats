package com.example.collegeeats.Delivery

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.collegeeats.Menu.MenuActivity
import com.example.collegeeats.OrderTrack.OrdertrackActivity
import com.example.collegeeats.R
import com.example.collegeeats.authentication.loginActivity
import com.example.collegeeats.databinding.FragmentDeliveryBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class delivery_functions(val context: Context) {

    fun logout(button: ConstraintLayout, activity: Activity){
        button.setOnClickListener {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_logout)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)
            dialog.show()

            val currentDevice = dialog.findViewById<TextView>(R.id.current_device_text)
            val cancelButton = dialog.findViewById<TextView>(R.id.cancel_button)

            currentDevice.setOnClickListener {
                dialog.dismiss()
                FirebaseAuth.getInstance().signOut()

                val intent = Intent(context, loginActivity::class.java)
                context.startActivity(intent)
                activity.finish()
            }

            cancelButton.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun fetchLocationAndData(fusedLocationClient: FusedLocationProviderClient) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let { updateLocationInFirestore(it) }
        }
    }
    private fun updateLocationInFirestore(location: Location) {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

        if (addresses!!.isNotEmpty()) {
            val address = addresses!![0]
            val addressString = address.getAddressLine(0)

            // Update the location_address field in Firestore for the current user document
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                val userId = currentUser.uid
                val location_address = FirebaseFirestore.getInstance().collection("Users").document(userId)
                location_address.update("location_address", addressString)
                location_address.update("last_activity", FieldValue.serverTimestamp())
            }
        }
    }
}


fun isNetworkConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

fun showFragment(fragment: Fragment, fragment_manager: FragmentManager) {
    val fram = fragment_manager.beginTransaction()
    fram.replace(R.id.home_container, fragment)
    fram.commit()
}

fun check_current_order(context: Context, binding: FragmentDeliveryBinding) {
    val sharedPreferences = context?.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    val checker = sharedPreferences?.getBoolean("track_order", false)
    Log.d("123456", checker.toString())

    if (checker == true) {
        val order_doc_id = sharedPreferences?.getString("order_doc_id", "")
        Log.d("123456", order_doc_id.toString())

        FirebaseFirestore.getInstance().collection("orders").document(order_doc_id.toString()).get().addOnSuccessListener {it ->
            val delivery_status = it.get("delivery_status")
            Log.d("123456", delivery_status.toString())

            if (delivery_status == false){

                binding.bottomTrack.visibility = View.VISIBLE
                val store_doc_id = it.get("store_id").toString()
                FirebaseFirestore.getInstance().collection("stores").document(store_doc_id).get().addOnSuccessListener {it ->
                    val store_name = it.get("name").toString()
                    binding.storeName2.text = store_name

                    val store_image = it.get("storeImage").toString()
                    Glide.with(context)
                        .load(store_image)
                        .placeholder(R.drawable.food_mock)
                        .error(R.drawable.food_mock)
                        .into(binding.ivStorePhoto)

                    binding.trackOrder.setOnClickListener {
                        if (isNetworkConnected(context)) {
                            val intent = Intent(context, OrdertrackActivity::class.java)
                            intent.putExtra("order_doc_id", order_doc_id.toString())
                            context.startActivity(intent)
                        }
                    }
                }
            }
        }

    }
}

@SuppressLint("SetTextI18n")
fun check_cart(context: Context, binding: FragmentDeliveryBinding){
    val sharedPreferences = context?.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    val checker = sharedPreferences?.getBoolean("order_cart", false)
    if (checker == true) {
        binding.bottomCart.visibility = View.VISIBLE

        val store_doc_id = sharedPreferences?.getString("store_doc_id", "")
        val total_item_cart = sharedPreferences?.getString("total_item_cart", "1")
        FirebaseFirestore.getInstance().collection("stores").document(store_doc_id.toString()).get().addOnSuccessListener { it

            binding.itemCart.text = "$total_item_cart items saved in your cart"
            binding.storeName.text = it.get("name").toString()
            val store_image = it.get("storeImage").toString()
            Glide.with(context)
                .load(store_image)
                .placeholder(R.drawable.food_mock)
                .error(R.drawable.food_mock)
                .into(binding.ivStorePhoto)

        }
        binding.openCart.setOnClickListener {
            if (isNetworkConnected(context)) {
                val intent = Intent(context, MenuActivity::class.java)
                intent.putExtra("store_doc_id", store_doc_id.toString())
                intent.putExtra("order_cart", true)
                context.startActivity(intent)
            }
        }
        binding.ivDelete.setOnClickListener {
            val editor = sharedPreferences?.edit()
            editor?.clear()
            editor?.apply()
            binding.bottomCart.visibility = View.GONE
        }
    }
}