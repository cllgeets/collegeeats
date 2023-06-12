package com.example.collegeeats.Delivery

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.collegeeats.Location.LocationDialogUtils
import com.example.collegeeats.Location.LocationFunctions
import com.example.collegeeats.Location.LocationUtils
import com.example.collegeeats.Location.LocationUtils.isLocationEnabled
import com.example.collegeeats.Settings.SettingActivity
import com.example.collegeeats.databinding.FragmentDeliveryBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth

class DeliveryFragment : Fragment() {
    private lateinit var dialog: Dialog
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var viewModel: StoreViewModel

    private lateinit var binding: FragmentDeliveryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentDeliveryBinding.inflate(inflater, container, false)
        val view = binding.root

        dialog = Dialog(requireActivity())
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        val shimmerFrameLayout = binding.deliveryShimmer
        shimmerFrameLayout.startShimmer()

        if (isNetworkConnected(requireActivity())){
            LocationFunctions(requireContext()).checkLocation(requireActivity(), dialog, requireActivity(), fusedLocationClient)

            val recyclerView: RecyclerView = binding.storeList

           var storeAdapter: StoreAdapter
            binding.storeList.layoutManager = LinearLayoutManager(context)
            storeAdapter = StoreAdapter(emptyList())
            binding.storeList.adapter = storeAdapter

            viewModel = ViewModelProvider(this).get(StoreViewModel::class.java)
            viewModel.getStores(shimmerFrameLayout, binding).observe(viewLifecycleOwner, Observer { stores ->
                storeAdapter = StoreAdapter(stores)
                recyclerView.adapter = storeAdapter
            })
            LocationFunctions(requireActivity()).screenTopLocationShowing(FirebaseAuth.getInstance().currentUser!!.uid, binding.userLocation)
        }else{
            Toast.makeText(requireActivity(), "No Internet.", Toast.LENGTH_SHORT).show()
        }

        binding.profileIcon.setOnClickListener {
            val intent = Intent(requireActivity(), SettingActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        if (isLocationEnabled(requireActivity())) {
            dialog.dismiss()
        } else {
            dialog.show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LocationUtils.REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                LocationDialogUtils.showLocationPermissionDeniedDialog(
                    requireActivity(),
                    DialogInterface.OnClickListener { _, _ ->
                        LocationUtils.requestLocationPermission(requireActivity())
                    })
            }

        }
    }
}