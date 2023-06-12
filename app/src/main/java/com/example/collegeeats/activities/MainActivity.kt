package com.example.collegeeats.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import com.example.collegeeats.CommonUtils.TokenManager
import com.example.collegeeats.Delivery.DeliveryFragment
import com.example.collegeeats.Delivery.showFragment
import com.example.collegeeats.R
import com.example.collegeeats.databinding.ActivityMainBinding
import com.example.collegeeats.map.MapFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        showFragment(DeliveryFragment(), supportFragmentManager)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.delivery -> {
                    showFragment(MapFragment(), supportFragmentManager)
                    true
                }
                R.id.history -> {
                    showFragment(DeliveryFragment(), supportFragmentManager)
                    true
                }
                R.id.pending -> {
                    showFragment(DeliveryFragment(), supportFragmentManager)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
    fun enableLocation(view: View) {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(intent)
    }
}