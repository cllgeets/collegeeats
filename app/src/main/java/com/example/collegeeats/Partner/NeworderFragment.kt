package com.example.collegeeats.Partner

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.collegeeats.Location.LocationUtils
import com.example.collegeeats.databinding.FragmentNeworderBinding


class NeworderFragment : Fragment() {
  private lateinit var binding: FragmentNeworderBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNeworderBinding.inflate(inflater, container,false)
        val view = binding.root

        val order_id = requireArguments().getString("order_doc_id", "")

        PartnersUtils(requireContext(), binding, requireActivity()).retrievedata(order_id)

        binding.reject.setOnClickListener {
            requireActivity().finish()
        }

        return view
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted, continue with app functionality
            } else {
                Toast.makeText(requireActivity(), "Location permission is required to use this app", Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (LocationUtils.isLocationEnabled(requireContext())) {
                if (LocationUtils.isBackgroundLocationPermissionGranted(requireContext())) {
                    // Location permission granted, continue with app functionality
                } else {
                    LocationUtils.requestLocationPermission(requireActivity())
                }
            } else {
                showLocationDialog(requireContext(), requireActivity())
            }
        }
    }

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}