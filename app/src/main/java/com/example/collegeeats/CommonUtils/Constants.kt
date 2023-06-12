package com.example.collegeeats.CommonUtils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.getInstance

object Constants {
    const val API_BASE_URL = "https://192.168.51.167:8888"

    val UID = FirebaseAuth.getInstance().currentUser?.uid

}