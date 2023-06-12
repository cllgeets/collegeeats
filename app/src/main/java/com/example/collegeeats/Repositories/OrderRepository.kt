package com.example.collegeeats.Repositories

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
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
}
