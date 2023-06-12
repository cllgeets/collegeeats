package com.example.collegeeats.authentication.Functions

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.widget.Toast
import com.example.collegeeats.activities.MainActivity
import com.example.collegeeats.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.concurrent.TimeUnit

class SignupFunctions(var binding: FragmentSignupBinding, var context: Context) {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var countDownTimer: CountDownTimer

    fun startTimer() {
        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                val seconds =
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        minutes
                    )
                binding.btnResendEmail.text = String.format("%02d:%02d", minutes, seconds)
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                Toast.makeText(context, "${auth.currentUser?.isEmailVerified}", Toast.LENGTH_SHORT).show()
                if (auth.currentUser?.isEmailVerified == true) {
                    navigateToMainActivity()
                } else {
                    binding.btnResendEmail.isEnabled = true
                    binding.btnResendEmail.setText("RESEND EMAIL")
                }
            }
        }.start()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

}