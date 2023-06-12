package com.example.collegeeats.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.collegeeats.authentication.Functions.SigninFunctions
import com.example.collegeeats.databinding.ActivityLoginBinding

class loginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        SigninFunctions(this, this.supportFragmentManager).showFragment(loginFragment())
    }
}