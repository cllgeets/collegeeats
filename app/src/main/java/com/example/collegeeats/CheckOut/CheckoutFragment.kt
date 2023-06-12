package com.example.collegeeats.CheckOut

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.collegeeats.Notification.NewOrderNotificationUtils
import com.example.collegeeats.Repositories.OrderRepository
import com.example.collegeeats.OrderTrack.OrdertrackActivity
import com.example.collegeeats.Repositories.UserRepository
import com.example.collegeeats.databinding.FragmentPaymentBinding
import com.google.common.reflect.TypeToken
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CheckoutFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PaymentDishesAdapter
    private lateinit var dishesList: MutableList<MutableList<String>>
    var store_doc_id = "";
    var totalPrice = 0
    var parterfee = 0
    var grandprice = 0
    private lateinit var dishdata: MutableList<String>
    private val orderRepository = OrderRepository()
    val userRepository = UserRepository()
    private val notificationUtils = NewOrderNotificationUtils()
    lateinit var checkoutUtils: CheckoutUtils
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        val view = binding.root

        store_doc_id = arguments?.getString("store_doc_id").toString()
        Log.d("store", store_doc_id)

        checkoutUtils = CheckoutUtils(requireContext(), binding)
        checkoutUtils.initialisation_views()
        checkoutUtils.changeAddress()


        recyclerView = binding.payRecycler
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = PaymentDishesAdapter()
        recyclerView.adapter = adapter
        dishesList = mutableListOf()
        dishdata = mutableListOf()

        val sharedPreferences =
            context?.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val json = sharedPreferences?.getString("pending_order", "")

        val mapType = object : TypeToken<MutableMap<String, Int>>() {}.type
        val dishesMap = Gson().fromJson<MutableMap<String, Int>>(json, mapType)

        CalculatePrices(dishesMap)

        CoroutineScope(Dispatchers.Main).launch {
            val manualAddress = userRepository.getUserManualAddress(Firebase.auth.uid.toString())
            if (!manualAddress.isNullOrEmpty()) {
                binding.deliveryLocation.text = manualAddress
            }
        }

        binding.searchPartner.setOnClickListener {
            binding.progressBarLayout.visibility = View.VISIBLE
            binding.progressBar.visibility = View.VISIBLE
            var address: String = ""
            FirebaseFirestore.getInstance().collection("Users")
                .document(Firebase.auth.uid.toString()).get().addOnSuccessListener {
                    it
                    address = it.get("manual_address").toString()
                    if (address.isNotEmpty()) {
                        CoroutineScope(Dispatchers.Main).launch {
                            try {
                                val orderId = createOrder(address)
                                if (orderId != null) {
                                    val success = addOrderDetailsAndSendNotification(orderId)
                                    if (success) {
                                        val intent = Intent(
                                            requireActivity(),
                                            OrdertrackActivity::class.java
                                        )
                                        intent.putExtra("order_doc_id", orderId)
                                        requireActivity().startActivity(intent)
                                        requireActivity().finish()
                                    } else {
                                        Log.d("error", "1")
                                    }
                                } else {
                                    Log.d("error", "2")
                                }
                            } catch (e: Exception) {
                                Log.d("error", "3")
                            }
                        }
                    }
                }
        }

        return view
    }

    private suspend fun createOrder(address: String): String? {
        val order = hashMapOf(
            "accepted" to false,
            "payment_status" to false,
            "delivery_status" to false,
            "delivered_time" to "",
            "accepted_time" to "",
            "order_start_time" to FieldValue.serverTimestamp(),
            "grand_price" to grandprice.toString(),
            "address" to address,
            "user_id" to Firebase.auth.uid,
            "store_id" to store_doc_id
        )

        return orderRepository.createOrder(order)
    }

    private suspend fun addOrderDetailsAndSendNotification(orderId: String): Boolean {
        var success = true

        for ((index, list) in dishesList.withIndex()) {
            val details = mutableListOf("doc_$index")
            details.addAll(list)

            val result = orderRepository.addOrderDetails(orderId, details)
            if (!result) {
                success = false
                break
            }
        }

        //Uncomment after fixing the API
//        if (success) {
//            val result = notificationUtils.sendNotification(orderId)
//            if (!result) {
//                success = false
//            }
//        }

        return success
    }

    fun CalculatePrices(dishesMap: MutableMap<String, Int>) {
        CoroutineScope(Dispatchers.Main).launch {
            for ((documentId, quantity) in dishesMap) {
                val dishData = OrderRepository().fetchDishPrice(store_doc_id, documentId)
                if (dishData != null) {
                    val (price, name) = dishData
                    val id = documentId
                    val total: Int = price.toInt() * quantity
                    totalPrice += total
                    dishdata = mutableListOf(
                        id.toString(),
                        name.toString(),
                        price.toString(),
                        quantity.toString(),
                        total.toString()
                    )
                    dishesList.add(dishdata)
                    adapter.setDishesList(dishdata)

                    grandprice = parterfee + totalPrice
                    CheckoutUtils(requireContext(), binding).handling_views(
                        grandprice,
                        parterfee,
                        totalPrice
                    )
                }
            }
        }
    }


}
