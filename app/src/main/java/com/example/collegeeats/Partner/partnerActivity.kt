package com.example.collegeeats.Partner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.collegeeats.R
import com.example.collegeeats.databinding.ActivityPartnerBinding

class partnerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPartnerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPartnerBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        val order_id = intent.getStringExtra("order_doc_id") ?: ""



        showFragment(NeworderFragment(), supportFragmentManager, order_id)
    }
}