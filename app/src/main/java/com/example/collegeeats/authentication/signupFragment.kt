package com.example.collegeeats.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.collegeeats.authentication.Functions.SignupFunctions
import com.example.collegeeats.databinding.FragmentSignupBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class signupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private  var auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = FragmentSignupBinding.inflate(inflater, container, false)
        val view = binding.root

        SignupFunctions(binding, requireActivity()).startTimer()
        binding.btnResendEmail.isEnabled = false

        binding.btnResendEmail.setOnClickListener {
            auth.currentUser?.sendEmailVerification()
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        binding.btnResendEmail.isEnabled = false
                        SignupFunctions(binding, requireActivity()).startTimer()
                    } else {
                        Snackbar.make(view, "Failed to send verification email.", Snackbar.LENGTH_LONG)
                            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show()
                    }
                }
        }

        binding.backPress.setOnClickListener {
            val intent = Intent(activity, loginActivity::class.java)
            activity?.finish()
            startActivity(intent)
        }
        return view
    }

}