package com.example.collegeeats.Settings

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.example.collegeeats.R
import com.example.collegeeats.activities.MainActivity
import com.example.collegeeats.databinding.FragmentEditProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.grpc.Compressor
import java.io.File

class EditprofileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var spinnerGender: Spinner

    var imageUri: Uri = Uri.parse("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        Profileutils(binding, requireActivity()).getdata()

        spinnerGender = binding.genderSpinner
        val genderList = resources.getStringArray(R.array.gender_array)
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, genderList)
        spinnerGender.adapter = genderAdapter

        Profileutils(binding, requireActivity()).setupDatePicker()

        binding.update.setOnClickListener {
            Profileutils(binding, requireActivity()).updateProfile(Firebase.auth.uid.toString(), spinnerGender, imageUri, requireActivity().contentResolver)
        }

        binding.changePhoto.setOnClickListener {
            chooseImage()
        }

        binding.backPress.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(null, 0)
        }
        return view
    }


    private fun chooseImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK && data != null) {
            // Get selected image URI and display in profile image view
            imageUri = data.data!!
            Glide.with(this)
                .load(imageUri)
                .placeholder(R.drawable.account)
                .error(R.drawable.account)
                .into(binding.profileImage)
        }
    }
}