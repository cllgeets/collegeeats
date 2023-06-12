package com.example.collegeeats.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.collegeeats.CommonUtils.Constants
import com.example.collegeeats.CommonUtils.TokenManager
import com.example.collegeeats.authentication.loginActivity
import com.example.collegeeats.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class splashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 2000L
    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        Handler(Looper.getMainLooper()).postDelayed({
            val currentUser = auth.currentUser
            if (currentUser != null && currentUser.isEmailVerified) {
                moveToMainActivity()
            } else{
                moveToLoginActivity()
            }
        }, SPLASH_TIME_OUT)
    }

    private fun moveToLoginActivity() {
        val intent = Intent(this, loginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveToMainActivity() {
        val uid = Constants.UID

        if (uid != null) {
            val tokenManager = TokenManager(this)

            GlobalScope.launch(Dispatchers.IO) {
                var token = tokenManager.getToken()

                var retry = 0;

                // Retry token generation until a token is obtained
                while (token.isNullOrEmpty() && retry <= 2) {
                    token = tokenManager.generateToken(uid)
                    retry++;
                }

                runOnUiThread {
                    val intent = Intent(this@splashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        } else {
            moveToLoginActivity()
        }
    }
}

