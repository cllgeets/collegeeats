package com.example.collegeeats.Settings

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.collegeeats.R
import com.example.collegeeats.databinding.FragmentEditProfileBinding
import com.example.collegeeats.databinding.FragmentSettingBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class Settingutils(val binding: FragmentSettingBinding, val context: Context) {
    fun get_profile(view: View) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()

        FirebaseFirestore.getInstance().collection("Users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val firstName = document.getString("first_name")
                    val profileImage = document.getString("profile_photo")
                    val profileComplete = document.getString("profile_completed")?.toInt() ?: 0

                    binding.name.text = firstName.toString()

                    Glide.with(context)
                        .load(profileImage.toString())
                        .centerCrop()
                        .placeholder(R.drawable.account)
                        .apply(RequestOptions.circleCropTransform())
                        .into(object : CustomTarget<Drawable>() {
                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                                binding.profileImage.setImageDrawable(resource)
                                binding.progressBar.visibility = View.GONE
                                binding.settingPage.visibility = View.VISIBLE
                            }
                            override fun onLoadCleared(placeholder: Drawable?) {}
                            override fun onLoadFailed(errorDrawable: Drawable?) {
                                super.onLoadFailed(errorDrawable)
                                binding.progressBar.visibility = View.GONE
                            }
                        })

                    binding.progress.text = profileComplete.toString() + "%"
                }
            }
    }

    fun calculate_profile_completion(view: View) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        if (uid != null) {
            val db = FirebaseFirestore.getInstance()
            val userDocRef = db.collection("Users").document(uid)

            userDocRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val user = documentSnapshot.toObject<com.example.collegeeats.Settings.User>()

                    var emptyFieldsCount = 0
                    var totalFieldsCount = 8 // Assuming there are 8 required fields

                    if (user?.birthday.isNullOrEmpty()) {
                        emptyFieldsCount++
                    }
                    if (user?.email_id.isNullOrEmpty()) {
                        emptyFieldsCount++
                    }
                    if (user?.first_name.isNullOrEmpty()) {
                        emptyFieldsCount++
                    }
                    if (user?.last_name.isNullOrEmpty()) {
                        emptyFieldsCount++
                    }
                    if (user?.full_name.isNullOrEmpty()) {
                        emptyFieldsCount++
                    }
                    if (user?.gender.isNullOrEmpty()) {
                        emptyFieldsCount++
                    }
                    if (user?.manual_address.isNullOrEmpty()) {
                        emptyFieldsCount++
                    }
                    if (user?.phone_number.isNullOrEmpty()) {
                        emptyFieldsCount++
                    }

                    val profileCompletePercentage =
                        ((totalFieldsCount - emptyFieldsCount) * 100) / totalFieldsCount

                    Log.d("123 my", profileCompletePercentage.toString())
                    // Set the calculated percentage in the "profile_completed" field of the user's document
                    userDocRef.update("profile_completed", profileCompletePercentage.toString())
                        .addOnSuccessListener {
                            get_profile(view)
                        }
                }
            }
        }
    }
}

fun showFragment(fragment: Fragment, fragment_manager: FragmentManager) {
    val fram = fragment_manager.beginTransaction()
    fram.replace(R.id.FrameSetting_container, fragment)
    fram.commit()
}

fun showFragment_type2(fragment: Fragment, fragment_manager: FragmentManager) {
    val fram = fragment_manager.beginTransaction()
    fram.replace(R.id.FrameSetting_container, fragment)
    fram.addToBackStack(null)
    fram.commit()
}