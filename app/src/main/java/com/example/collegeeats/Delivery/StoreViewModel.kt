package com.example.collegeeats.Delivery

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.collegeeats.Data.Store
import com.example.collegeeats.databinding.FragmentDeliveryBinding
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.firebase.firestore.FirebaseFirestore

class StoreViewModel(application: Application) : AndroidViewModel(application) {

    private val db = FirebaseFirestore.getInstance()

    fun getStores(shimmerFrameLayout: ShimmerFrameLayout, binding: FragmentDeliveryBinding): LiveData<List<Store>> {
        val stores = MutableLiveData<List<Store>>()
        db.collection("stores")
            .get()
            .addOnSuccessListener { result ->
                val storeList = mutableListOf<Store>()
                Log.d("123", storeList.toString())
                for (document in result) {
                    val id = document.id
                    val name = document.getString("name") ?: ""
                    val rating = document.getString("rating") ?: ""
                    val deliveringStatus = document.getString("delivering_status") ?: ""
                    val storeImage = document.getString("storeImage") ?: ""
                    storeList.add(Store(id, name, rating, deliveringStatus, storeImage))
                }

                stores.value = storeList
                check_cart(binding.root.context, binding)
                check_current_order(binding.root.context, binding)
                shimmerFrameLayout.stopShimmer()
                binding.deliveryFragment.visibility = View.VISIBLE
                shimmerFrameLayout.visibility = View.GONE
            }
        return stores
    }
}
