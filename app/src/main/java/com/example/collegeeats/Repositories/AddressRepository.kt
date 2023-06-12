package com.example.collegeeats.Repositories

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AddressRepository {
    private val db = Firebase.firestore

    suspend fun getManualAddress(userId: String): String? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val document = db.collection("Users").document(userId).get().await()
                val manualAddress = document.getString("manual_address")
                manualAddress?.takeIf { it.isNotEmpty() }
            } catch (e: Exception) {
                null
            }
        }

    //this will fetch live location address stored in database
    suspend fun getLocationAddress(userId: String): String? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val document = db.collection("Users").document(userId).get().await()
                val locationAddress = document.getString("location_address")
                locationAddress?.takeIf { it.isNotEmpty() }
            } catch (e: Exception) {
                null
            }
        }

    suspend fun updateManualAddress(userId: String, address: String): Boolean =
        withContext(Dispatchers.IO) {
            return@withContext try {
                db.collection("Users").document(userId)
                    .update("manual_address", address).await()
                true
            } catch (e: Exception) {
                false
            }
        }

    suspend fun updateLocationAddress(userId: String, address: String): Boolean =
        withContext(Dispatchers.IO) {
            return@withContext try {
                db.collection("Users").document(userId)
                    .update("location_address", address).await()
                true
            } catch (e: Exception) {
                false
            }
        }
}
