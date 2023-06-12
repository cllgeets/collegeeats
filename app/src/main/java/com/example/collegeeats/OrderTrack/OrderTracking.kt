package com.example.collegeeats.OrderTrack

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.collegeeats.databinding.FragmentOrderTrackingBinding
import com.google.firebase.firestore.FirebaseFirestore


class OrderTracking : Fragment() {
    private lateinit var binding: FragmentOrderTrackingBinding
    var order_doc_id: String =""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderTrackingBinding.inflate(inflater, container, false)
        val view  = binding.root

        order_doc_id = requireArguments().getString("order_doc_id").toString()

        OrderTrackutils(requireContext(), binding).initialisation_views()

        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("orders").document(order_doc_id.toString())

       val list =  docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                val accepted = snapshot.getBoolean("accepted")
                val paymentStatus = snapshot.getBoolean("payment_status")

                if (accepted == true) {

                    val sharedPreferences = requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("track_order", true)
                    editor.putString("order_doc_id", order_doc_id)
                    editor.apply()

                    binding.visibleFrag.visibility = View.VISIBLE
                    binding.deliveryShimmer.visibility = View.GONE
                    binding.tvStatus.text = "Your Order is Accepted"
                    docRef.get().addOnSuccessListener {it ->
                        val partner_id = it.get("partner_id").toString()
                        FirebaseFirestore.getInstance().collection("Users").document(partner_id).get().addOnSuccessListener {it ->
                            val name = it.get("full_name").toString()
                            val phone_number = it.get("phone_number").toString()
                            val profile_photo = it.get("profile_photo").toString()
                            val upi_id = it.get("upi_id").toString()

                            binding.name.text = name
                            Glide.with(this)
                                .load(profile_photo)
                                .placeholder(com.example.collegeeats.R.drawable.account)
                                .error(com.example.collegeeats.R.drawable.account)
                                .into(binding.partnerImage)

                            binding.callPartner.setOnClickListener {
                                val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:$phone_number")
                                }
                                requireContext().startActivity(dialIntent)
                            }
                            binding.pay.setOnClickListener {
                                val uri = Uri.Builder()
                                    .scheme("upi")
                                    .authority("pay")
                                    .appendQueryParameter("pa", upi_id)
                                    .appendQueryParameter("pn", "SHIVANI MEENA")
                                    .appendQueryParameter("am", "1")
                                    .appendQueryParameter("cu", "INR")
                                    .appendQueryParameter("mc", "0000")
                                    .appendQueryParameter("mode", "04")
                                    .appendQueryParameter("purpose", "00")
                                    .build()
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = uri
                                intent.setPackage(pay.GOOGLE_TEZ_PACKAGE_NAME)
                                startActivityForResult(intent, pay.TEZ_REQUEST_CODE)
                            }
                        }
                    }
                } else {
                    binding.tvStatus.text = "No partner available"
                }
            } else {
                Log.d(TAG, "Current data null")
            }
        }

        Handler().postDelayed({
            list.remove()
            binding.tvStatus.text = "No partner available, Try Again"
        }, 90000)

        binding.choosePaymentMethod.setOnClickListener {
            OrderTrackutils(requireContext(), binding).payment_adapter()
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pay.TEZ_REQUEST_CODE) {
            // Process based on the data in response.
            Log.d("result", data!!.getStringExtra("Status")!!)
        }
    }


}

object pay {
     const val TEZ_REQUEST_CODE = 123
     const val GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user"
}
