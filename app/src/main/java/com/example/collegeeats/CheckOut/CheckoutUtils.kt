package com.example.collegeeats.CheckOut

import android.content.Context
import android.view.View
import com.example.collegeeats.Repositories.AddressRepository
import com.example.collegeeats.databinding.FragmentPaymentBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckoutUtils(val context: Context, val binding: FragmentPaymentBinding) {
    private val addressRepository = AddressRepository()

    fun changeAddress() {
        binding.changeLocation.setOnClickListener {
            val bottomSheet = binding.eventBottomSheet
            var behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.isDraggable = true
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            behavior.peekHeight = 500
            binding.addAddress.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.Main).launch {
                val manualAddress =
                    addressRepository.getManualAddress(FirebaseAuth.getInstance().uid.toString())
                if (!manualAddress.isNullOrEmpty()) {
                    binding.manualLocationFirebase.visibility = View.VISIBLE
                    binding.manualAddressFirebase.text = manualAddress
                    binding.selectThis.setOnClickListener {
                        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        binding.deliveryLocation.text = manualAddress
                        binding.addAddress.visibility = View.GONE
                        behavior.peekHeight = 0
                        binding.manualAddressFirebase.visibility = View.GONE
                    }
                }
            }

            binding.addManualAddress.setOnClickListener {
                val room_no = binding.roomNumberEditText.text.toString()
                val hostel_name = binding.hostelNameEditText.text.toString()
                val college_name = binding.collegeNameEditText.text.toString()
                val address = "$room_no, $hostel_name, $college_name"

                CoroutineScope(Dispatchers.Main).launch {
                    val success = addressRepository.updateManualAddress(
                        FirebaseAuth.getInstance().uid.toString(),
                        address
                    )
                    if (success) {
                        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        behavior.peekHeight = 0
                        binding.deliveryLocation.text = address
                        binding.addAddress.visibility = View.GONE
                        binding.manualAddressFirebase.visibility = View.GONE
                    }
                }
            }
        }
    }

    fun initialisation_views() {
        val bottomSheet = binding.eventBottomSheet
        var behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.peekHeight = 0
        behavior.isDraggable = true
        behavior.state = BottomSheetBehavior.STATE_HIDDEN

        binding.addressClose.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            behavior.peekHeight = 0
        }
    }

    fun handling_views(grandprice: Int, parterfee: Int, totalPrice: Int) {
        binding.visibleFrag.visibility = View.VISIBLE
        binding.bottomVisibleFrag.visibility = View.VISIBLE
        binding.deliveryShimmer.stopShimmer()
        binding.deliveryShimmer.visibility = View.GONE

        binding.tvTotalSubtotal.text = "₹" + totalPrice.toString()
        binding.tvPartnerFee.text = "₹" + parterfee.toString()
        binding.tvGrandTotal.text = "₹" + grandprice.toString()
    }
}