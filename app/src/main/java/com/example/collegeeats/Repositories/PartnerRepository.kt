package com.example.collegeeats.Repositories

import com.example.collegeeats.Data.Partner
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class PartnerRepository {
    private val db = FirebaseFirestore.getInstance()

    fun getPartnerById(partnerId: String): Flow<Result<Partner>> = callbackFlow {
        val docRef = db.collection("Users").document(partnerId)
        val listener = docRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySend(Result.failure(error)).isFailure
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val fullName = snapshot.getString("full_name")
                val phoneNumber = snapshot.getString("phone_number")
                val profilePhoto = snapshot.getString("profile_photo")
                val upiId = snapshot.getString("upi_id")

                val partner = Partner(fullName, phoneNumber, profilePhoto, upiId)
                trySend(Result.success(partner)).isSuccess
            } else {
                trySend(Result.failure(Exception("Partner not found"))).isFailure
            }
        }

        awaitClose { listener.remove() }
    }
}