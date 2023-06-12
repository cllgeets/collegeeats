package com.example.collegeeats.Partner

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegeeats.R
import com.example.collegeeats.databinding.FragmentNeworderBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class PartnersUtils(val context: Context, val binding: FragmentNeworderBinding,val activity: Activity) {
    val textView = TextView(context)


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.M)
    fun handle_swipe(order_id: String, accepted: Boolean) {
        val seekBar = binding.myseek
        textView.textSize = 18f
        textView.gravity = Gravity.CENTER
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        binding.relativeLayout.addView(textView, params)

        if (accepted == true){
            textView.text = "Already Accepted"
            textView.setTextColor(context.getColor(R.color.Red))
            seekBar.isEnabled = false
        }else{
            textView.text = "Swipe to Accept"
            textView.setTextColor(context.getColor(R.color.white))
        }

        seekBar.isClickable = false

        seekBar.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_MOVE) {
                    val progress = (event.x / v!!.width * seekBar.max).toInt()
                    seekBar.progress = progress
                    return true
                }
                if (event?.action == MotionEvent.ACTION_UP) {
                    if (seekBar.progress < 80) {
                        seekBar.progress = 0
                    }

                    return true
                }
                return false
            }
        })

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Detect progress change
                if (progress > 80) {
                    if (seekBar != null) {
                        seekBar.progress = 100
                        binding.reject.visibility = View.GONE
                        textView.text = "ACCEPTED"
                        seekBar.isEnabled  = false
                        val db = FirebaseFirestore.getInstance()
                        val ref = db.collection("orders").document(order_id)
                        ref.update("accepted", true)
                        ref.update("accepted_time", FieldValue.serverTimestamp())
                        ref.update("partner_id", Firebase.auth.uid.toString())
                        LocationCheck(context, activity).all_location_related()
                        LocationCheck(context, activity).startLocationCheck()
                        LocationCheck(context, activity).check_back_location(order_id)

                        val sharedPreferences = context?.getSharedPreferences("partner", Context.MODE_PRIVATE)
                        val editor = sharedPreferences!!.edit()
                        editor.putString("order_id", order_id)
                        editor.apply()

                    }
                    Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show()
                }
//                else{
//                    if (seekBar != null) {
//                        seekBar.progress = 0
//                    }
//                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Do nothing
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Do nothing
            }
        })

        seekBar.isClickable = false
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun retrievedata(order_id: String) {
        val db = FirebaseFirestore.getInstance()

        val ref = db.collection("orders").document(order_id)
        ref.get().addOnSuccessListener { orderDoc ->
            val accepted = orderDoc.getBoolean("accepted") ?: true
            val address = orderDoc.getString("address")
            val storeId = orderDoc.getString("store_id")
            val userId = orderDoc.getString("user_id")
            val grandprice = orderDoc.getString("grand_price")
            handle_swipe(order_id, accepted)

            // get user data
            db.collection("Users").document(userId!!).get()
                .addOnSuccessListener { userDoc ->
                    val fullName = userDoc.getString("full_name")
                    val phoneNumber = userDoc.getString("phone_number")

                    db.collection("stores").document(storeId!!).get()
                        .addOnSuccessListener { storeDoc ->
                            val storeName = storeDoc.getString("name")

                            ref.collection("order_details").get()
                                .addOnSuccessListener { documents ->

                                    val orderDetailsList = mutableListOf<OrderDetails>()
                                    for (document in documents) {
                                        val dishlist =
                                            document.get("order_details") as ArrayList<String>


                                        val orderDetail = arrayToDish(dishlist)

                                        Log.d("123", orderDetail.toString())

                                        orderDetailsList.add(orderDetail)
                                    }
                                    binding.recyclerView.layoutManager =
                                        LinearLayoutManager(context)
                                    binding.recyclerView.adapter =
                                        OrderDetailsAdapter(orderDetailsList)

                                    binding.tvStoreName.text = storeName
                                    binding.userName.text = fullName
                                    binding.userAddress.text = address
                                    binding.progressBar.visibility = View.GONE
                                    binding.invisible.visibility = View.VISIBLE
                                    binding.tvGrandTotal.text = "₹" + grandprice.toString()

                                    binding.callUser.setOnClickListener {
                                        val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                                            data = Uri.parse("tel:$phoneNumber")
                                        }
                                        context.startActivity(dialIntent)
                                    }
                                }
                        }
                        .addOnFailureListener { exception ->

                        }
                }
        }
    }
}

fun arrayToDish(array: ArrayList<String>): OrderDetails {
    return OrderDetails(
        array[0],
        array[1],
        array[2],
        array[3],
        array[4]
    )
}

fun showFragment(fragment: Fragment, fragment_manager: FragmentManager, order_id: String) {
    val fram = fragment_manager.beginTransaction()
    val bundle = Bundle()
    bundle.putString("order_doc_id", order_id)
    fragment.arguments = bundle
    fram.replace(R.id.partner_container, fragment)
    fram.commit()
}