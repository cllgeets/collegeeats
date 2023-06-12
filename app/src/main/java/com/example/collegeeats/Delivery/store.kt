package com.example.collegeeats.Delivery

import com.google.firebase.firestore.DocumentId

data class Store(
    @DocumentId
    val id: String,
    val name: String,
    val rating: String,
    val delivering_status: String,
    val storeImage: String,
    )
