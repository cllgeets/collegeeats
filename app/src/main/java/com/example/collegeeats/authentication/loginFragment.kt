package com.example.collegeeats.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.collegeeats.authentication.Functions.SigninFunctions
import com.example.collegeeats.authentication.Functions.checkValue
import com.example.collegeeats.databinding.FragmentLoginBinding

class loginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.submit.setOnClickListener {
            checkValue(binding.email) ?: return@setOnClickListener
            checkValue(binding.password) ?: return@setOnClickListener

            val email_text = binding.email.text.toString().trim()
            val pass_text = binding.password.text.toString().trim()
            SigninFunctions(requireActivity(), requireActivity().supportFragmentManager).signInWithEmailAndPassword(
                email_text, pass_text, view, requireActivity()
            )
        }

        return view
    }

}