package com.example.collegeeats.Dao

import com.google.firebase.firestore.FieldValue

data class UserDao(
    val birthday: String = "",
    val created_at: FieldValue = FieldValue.serverTimestamp(),
    val email_id: String = "",
    val first_name: String = "",
    val last_name: String = "",
    val full_name: String = "",
    val gender: String = "",
    val id: String = "",
    val last_activity: FieldValue = FieldValue.serverTimestamp(),
    val location_address: String = "",
    val manual_address: String = "",
    val phone_number: String = "",
    val profile_completed: String = "",
    val profile_photo: String = "",
    val updated_at: FieldValue = FieldValue.serverTimestamp()
)