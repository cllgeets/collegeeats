package com.example.collegeeats.Settings

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.collegeeats.Delivery.delivery_functions
import com.example.collegeeats.R
import com.example.collegeeats.activities.MainActivity
import com.example.collegeeats.databinding.FragmentSettingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        val view = binding.root

        get_back()

        Settingutils(binding, requireActivity()).calculate_profile_completion(view)

        binding.profileClicker.setOnClickListener {
            showFragment_type2(EditprofileFragment(), requireActivity().supportFragmentManager)
        }

        delivery_functions(requireActivity()).logout(binding.logOut, requireActivity())

        val ref = FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().uid.toString())
        ref.get().addOnSuccessListener { it ->
            val partner = it.getBoolean("partner")
            if (partner == true){
                binding.subscribe.isChecked = partner
            }else{
                binding.subscribe.isChecked = false
            }
        }

        binding.subscribe.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ref.update("partner", true)
                FirebaseMessaging.getInstance().subscribeToTopic("partner")
            } else {
                ref.update("partner", false)
                FirebaseMessaging.getInstance().unsubscribeFromTopic("partner")
            }
        }
        return view
    }

    fun get_back() {
        binding.goBack.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
    }

}