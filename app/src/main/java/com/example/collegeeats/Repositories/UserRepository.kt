package com.example.collegeeats.Repositories

import com.example.collegeeats.Dao.UserDao
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.random.Random

class UserRepository {
    private val db = Firebase.firestore

    suspend fun writeUserDataToFirestore(uid: String, email: String, userImage: String) {
        val userRef = db.collection("Users").document(uid)

        val newUser = UserDao(
            birthday = "",
            created_at = FieldValue.serverTimestamp(),
            email_id = email,
            first_name = "",
            last_name = "",
            full_name = "",
            gender = "",
            id = uid,
            last_activity = FieldValue.serverTimestamp(),
            location_address = "",
            manual_address = "",
            phone_number = "",
            profile_completed = "",
            profile_photo = userImage,
            updated_at = FieldValue.serverTimestamp()
        )

        try {
            userRef.set(newUser).await()
        } catch (e: Exception) {
            // Handle the exception here (e.g., log or show an error message)
        }
    }

    suspend fun chooseRandomProfile(): String = withContext(Dispatchers.IO) {
        val db = FirebaseFirestore.getInstance()
        val randomImagesRef = db.collection("Users").document("random_images")

        return@withContext try {
            val document = randomImagesRef.get().await()
            val images = document.get("images") as? ArrayList<String>
            images?.let {
                val randomIndex = Random.nextInt(images.size)
                images[randomIndex]
            } ?: ""
        } catch (e: Exception) {
            ""
        }
    }
    suspend fun getUserManualAddress(uid: String): String? {
        return try {
            val document = db.collection("Users").document(uid).get().await()
            document.getString("manual_address")
        } catch (e: Exception) {
            null
        }
    }
}
