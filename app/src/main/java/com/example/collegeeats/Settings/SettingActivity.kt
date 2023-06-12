package com.example.collegeeats.Settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.collegeeats.R
import com.example.collegeeats.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        showFragment(SettingFragment(), supportFragmentManager)

    }
}