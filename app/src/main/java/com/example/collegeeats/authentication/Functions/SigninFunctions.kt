package com.example.collegeeats.authentication.Functions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.collegeeats.Repositories.UserRepository
import com.example.collegeeats.CommonUtils.DialogManager
import com.example.collegeeats.R
import com.example.collegeeats.activities.MainActivity
import com.example.collegeeats.authentication.signupFragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SigninFunctions(var context: Context, var fragment_manager: FragmentManager) {
    private var auth: FirebaseAuth = Firebase.auth
    val dialog = DialogManager(context);
    private val userRepository = UserRepository()
    fun signInWithEmailAndPassword(email: String, password: String, view: View, activity: Activity) {
        dialog.show()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        if (user.isEmailVerified) {
                            dialog.dismiss()
                            Snackbar.make(view, "Sign in successful.", Snackbar.LENGTH_LONG)
                                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show()
                            goToNextActivity(activity)
                        } else {
                            sendVerificationEmail(user)
                        }
                    }
                } else {
                    dialog.dismiss()
                    signUpWithEmailAndPassword(email, password, view)
                }
            }
    }

    private fun signUpWithEmailAndPassword(email: String, password: String, view: View) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        CoroutineScope(Dispatchers.Main).launch {
                            val userImage = userRepository.chooseRandomProfile()
                            userRepository.writeUserDataToFirestore(user.uid, email, userImage)
                            sendVerificationEmail(user)
                        }
                    }
                } else {
                    dialog.dismiss()
                    Snackbar.make(view, "Authentication failed.", Snackbar.LENGTH_LONG)
                        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show()
                }
            }
    }

    private fun sendVerificationEmail(user: FirebaseUser) {
        user.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    dialog.dismiss()
                    Toast.makeText(context, "Verification email sent.", Toast.LENGTH_SHORT).show()
                    showFragment(signupFragment())
                } else {
                    dialog.dismiss()
                    Log.e("Authentication", "sendEmailVerification", task.exception)
                }
            }
    }

    private fun goToNextActivity(activity: Activity) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        activity.finish()
    }


    fun showFragment(fragment: Fragment) {
        val fram = fragment_manager.beginTransaction()
        fram.replace(R.id.container, fragment)
        fram.commit()
    }
}

fun checkValue(input: EditText): String? {
    val value = input.text.toString()
    if(value.isBlank()) {
        input.error = "Required Field!"
        return null
    }
    input.error = null
    return value
}
