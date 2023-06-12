package com.example.collegeeats.Partner

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.*
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.example.collegeeats.activities.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BackgroundLocation : Service() {

    private val CHANNEL_ID = "ForegroundServiceChannel"
    private val INTERVAL: Long = 15000 // 30 seconds
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var order_id = ""

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()

      order_id =   intent!!.getStringExtra("order_id").toString()
        val notificationIntent = Intent(this, MainActivity::class.java)
         val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_IMMUTABLE else 0

        )

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Active Pickup")
            .setContentText("click to open cllgeeats app...")
            .setSmallIcon(androidx.core.R.drawable.notification_action_background)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)

        Handler(Looper.getMainLooper()).post(object : Runnable {
            override fun run() {
                fetchLocation()
                Handler(Looper.getMainLooper()).postDelayed(this, INTERVAL)
            }
        })

        return START_NOT_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        val lat = location.latitude
                        val lng = location.longitude
                        sendLocationToFirestore(lat, lng)
                    }
                }
    }

    private fun sendLocationToFirestore(lat: Double, lng: Double) {
       val db = Firebase.firestore
        val location = hashMapOf<String, String>(
            "lat" to lat.toString(),
            "lng" to lng.toString()
        )
        db.collection("orders")
            .document(order_id.toString()).update("partner_location", location).addOnSuccessListener {
                Log.d("123", "sent")
            }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        private const val TAG = "LocationForegroundService"
    }
}
