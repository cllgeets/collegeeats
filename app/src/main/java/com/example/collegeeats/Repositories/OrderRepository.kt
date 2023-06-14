package com.example.collegeeats.Repositories

import com.example.collegeeats.Data.Order
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class OrderRepository {
    private val db = Firebase.firestore

    suspend fun createOrder(order: HashMap<String, Any?>): String? = withContext(Dispatchers.IO) {
        return@withContext try {
            val documentReference = db.collection("orders").add(order).await()
            documentReference.id
        } catch (e: Exception) {
            null
        }
    }

    suspend fun addOrderDetails(orderId: String, details: MutableList<String>): Boolean =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val documentReference = db.collection("orders").document(orderId)
                    .collection("order_details").document()
                documentReference.set(hashMapOf("order_details" to details)).await()
                true
            } catch (e: Exception) {
                false
            }
        }
        suspend fun fetchDishPrice(storeId: String, menuId: String): Pair<String, String>? =
            withContext(Dispatchers.IO) {
                return@withContext try {
                    val document = db.collection("stores").document(storeId)
                        .collection("Menu").document(menuId).get().await()

                    if (document != null && document.exists()) {
                        val price = document.getString("price").toString()
                        val name = document.getString("name").toString()
                        Pair(price, name)
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }

    fun getOrderById(orderId: String): Flow<Result<Order>> = callbackFlow {
        val docRef = db.collection("orders").document(orderId)
        val listener = docRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySend(Result.failure(error)).isFailure
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val accepted = snapshot.getBoolean("accepted")
                val paymentStatus = snapshot.getBoolean("payment_status")
                val partnerId = snapshot.getString("partner_id")

                val order = Order(accepted, paymentStatus, partnerId)
                trySend(Result.success(order)).isSuccess
            } else {
                trySend(Result.failure(Exception("Order not found"))).isFailure
            }
        }

        awaitClose { listener.remove() }
    }
}
