package com.example.collegeeats.Repositories

import com.example.collegeeats.Data.MenuData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class StoreRepository() {
    private val storeCollectionRef = FirebaseFirestore.getInstance().collection("stores")
    fun getStoreData(storeDocId: String): Task<DocumentSnapshot> {
        return storeCollectionRef.document(storeDocId).get()
    }

    suspend fun getMenuItems(storeDocId: String?): List<MenuData> = withContext(Dispatchers.IO) {
        return@withContext suspendCancellableCoroutine<List<MenuData>> { continuation ->
            if (storeDocId != null) {
                storeCollectionRef.document(storeDocId)
                    .collection("Menu").get()
                    .addOnSuccessListener { documents ->
                        val menuItems = documents.mapNotNull {
                            it.toObject(MenuData::class.java)
                        }
                        continuation.resume(menuItems)
                    }.addOnFailureListener { exception ->
                        continuation.resumeWithException(exception)
                    }
            }
        }
    }
}

