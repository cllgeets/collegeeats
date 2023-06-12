package com.example.collegeeats.Notification

data class ApiData(
    val to: String,
    val notification: Notification,
    val data: Data
)

data class Notification(
    val title: String,
    val body: String
)

data class Data(
    val order_id: String
)
