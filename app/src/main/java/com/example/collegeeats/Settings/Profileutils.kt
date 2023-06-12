package com.example.collegeeats.Settings

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Spinner
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.collegeeats.R
import com.example.collegeeats.databinding.FragmentEditProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import io.grpc.Compressor
import java.io.ByteArrayOutputStream
import java.io.File
import java.security.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class Profileutils(val binding: FragmentEditProfileBinding, val context: Context) {

    val dialog = Dialog(context)

    fun setupDatePicker() {
        val cal = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                binding.birthdayedittext.setText(SimpleDateFormat("dd/MM/yyyy").format(cal.time))
            }

        val dobPicker = DatePickerDialog(
            context,
            dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )

        binding.birthdayedittext.setOnClickListener {
            dobPicker.show()
        }
    }

    fun getSelectedGender(spinnerGender: Spinner): String {
        return spinnerGender.selectedItem.toString()
    }

    fun getdata() {
        val db = Firebase.firestore
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userDocRef = db.collection("Users").document(currentUser!!.uid)

        userDocRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val profileImage = documentSnapshot.getString("profile_photo")
                    val fullName = documentSnapshot.getString("full_name")
                    val phoneNumber = documentSnapshot.getString("phone_number")
                    val email = documentSnapshot.getString("email_id")
                    val birthday = documentSnapshot.getString("birthday")
                    val gender = documentSnapshot.getString("gender")

                    binding.nameEditText.setText(fullName)
                    binding.phoneNumberEditText.setText(phoneNumber)
                    binding.emailEditText.setText(email)
                    binding.birthdayedittext.setText(birthday)

                    Glide.with(context)
                        .load(profileImage.toString())
                        .centerCrop()
                        .placeholder(R.drawable.account)
                        .apply(RequestOptions.circleCropTransform())
                        .into(binding.profileImage)

                    binding.update.isEnabled = true
                    binding.hidingFrag.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
            }
    }

    fun getCurrentTimestamp(): String {
        val timestamp = System.currentTimeMillis()
        return timestamp.toString()
    }

    fun compressImage(contentResolver: ContentResolver, imageUri: Uri): ByteArray {
        var byteArray: ByteArray = byteArrayOf()

        val source = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.createSource(contentResolver, imageUri)
        } else {
            TODO("VERSION.SDK_INT < P")
        }
        val bitmap = ImageDecoder.decodeBitmap(source)

        var quality = 100
        var streamLength: Int
        val byteArrayOutputStream = ByteArrayOutputStream()
        do {
            byteArrayOutputStream.flush()
            byteArrayOutputStream.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream)
            streamLength = byteArrayOutputStream.toByteArray().size
            quality -= 5
        } while (streamLength > 400 * 1024 && quality >= 0)

        byteArray = byteArrayOutputStream.toByteArray()
        byteArrayOutputStream.close()

        return byteArray
    }

    private fun uploadImage(imageUri: Uri, contentResolver: ContentResolver) {
       val storageRef = FirebaseStorage.getInstance().reference
        val firestoreRef = FirebaseFirestore.getInstance().collection("Users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)

        val imageRef = storageRef.child("images/${FirebaseAuth.getInstance().currentUser!!.uid + getCurrentTimestamp()}")
        imageRef.putBytes(compressImage(contentResolver, imageUri)).addOnSuccessListener { taskSnapshot ->
            // Get image URL and update user profile photo in Firestore
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                firestoreRef.update("profile_photo", uri.toString())
                // Update profile image view
                Glide.with(context)
                    .load(uri)
                    .placeholder(R.drawable.account)
                    .error(R.drawable.account)
                    .into(binding.profileImage)
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(context, "Image upload failed: ${exception.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateProfile(uid: String, spinnerGender: Spinner, imageUri: Uri, contentResolver: ContentResolver) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_process)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.show()

        val userRef = FirebaseFirestore.getInstance().collection("Users").document(uid!!)

        val name = binding.nameEditText.text.toString().trim()
        val phoneNumber = binding.phoneNumberEditText.text.toString().trim()
        val email = binding.emailEditText.text.toString().trim()
        val birthday = binding.birthdayedittext.text.toString().trim()
        val gender = getSelectedGender(spinnerGender).toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }
        if (imageUri.toString().isNotEmpty()){ uploadImage(imageUri, contentResolver)}

        userRef.update(
            "full_name",
            name,
            "phone_number",
            phoneNumber,
            "birthday",
            birthday,
            "gender",
            gender
        )
            .addOnSuccessListener {
                dialog.hide()
                Toast.makeText(context, "profile updated successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                dialog.hide()
                Toast.makeText(context, "Failed to update profile", Toast.LENGTH_SHORT).show()
            }
    }

}