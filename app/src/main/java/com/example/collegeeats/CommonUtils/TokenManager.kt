package com.example.collegeeats.CommonUtils

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class TokenManager(private val context: Context) {
    private var client = OkHttpClient()
    private val sharedPreferences = context.getSharedPreferences("PrivateEats", Context.MODE_PRIVATE)

    init {
        val trustAllCertificates: Array<TrustManager> = arrayOf(
            object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            }
        )

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCertificates, SecureRandom())

        client = OkHttpClient.Builder()
            .hostnameVerifier { _, _ -> true }
            .sslSocketFactory(sslContext.socketFactory, trustAllCertificates[0] as X509TrustManager)
            .build()
    }
    suspend fun generateToken(uid: String): String? {
        val url = Constants.API_BASE_URL + "/generate-token"
        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val requestBody = "{\"uid_id\":\"$uid\"}".toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .post(requestBody)
            .build()

        return withContext(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val token = response.body?.string()
                    saveToken(token)
                    token

                } else {
                    handleRequestError()
                    null
                }
            } catch (e: IOException) {
                Log.d("error", e.toString())
                handleNetworkError()
                null
            }
        }
    }

    private suspend fun handleRequestError() {
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Failed to generate token. Please try again later.", Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun handleNetworkError() {
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Network error. Please check your internet connection.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveToken(token: String?) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }
}
