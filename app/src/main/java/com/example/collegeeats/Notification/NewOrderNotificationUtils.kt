package com.example.collegeeats.Notification

import com.example.collegeeats.CommonUtils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class NewOrderNotificationUtils{
    private val client = OkHttpClient()

    suspend fun sendNotification(orderId: String): Boolean = withContext(Dispatchers.IO) {
        val url = Constants.API_BASE_URL + "/send-notification"
        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val requestBody = "{\"order_id\":\"$orderId\"}".toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .post(requestBody)
            .build()

        return@withContext try {
            val response = client.newCall(request).execute()
            response.isSuccessful
        } catch (e: IOException) {
            false
        }
    }
}
