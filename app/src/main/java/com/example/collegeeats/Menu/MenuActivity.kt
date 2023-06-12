package com.example.collegeeats.Menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.collegeeats.CheckOut.Constants
import com.example.collegeeats.CheckOut.CheckoutFragment
import com.example.collegeeats.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root

        val store_doc_id = intent.getStringExtra("store_doc_id").toString()
        val order_cart = intent.getBooleanExtra("order_cart", false)

        if (order_cart == false){
            Constants.store_doc_id = store_doc_id
            showFragment(MenuFragment(), supportFragmentManager, store_doc_id)
        }else{
            showFragment(CheckoutFragment(), supportFragmentManager, store_doc_id)
        }
        setContentView(view)
    }
}