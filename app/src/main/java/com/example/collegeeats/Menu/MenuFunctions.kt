package com.example.collegeeats.Menu

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.collegeeats.R
import com.example.collegeeats.Repositories.StoreRepository
import com.example.collegeeats.databinding.FragmentMenuBinding
import kotlinx.coroutines.tasks.await

class MenuFunctions(val context: Context, val binding: FragmentMenuBinding) {

    suspend fun GetStoreData(store_doc_id: String) {
        val document = StoreRepository().getStoreData(store_doc_id).await()
        val id = document.getString("id")
        val name = document.getString("name")
        val rating = document.getString("rating")

        binding.storeName.text = name.toString()
        binding.storeRating.text = rating.toString() + " ⭐"
    }
}

fun getRoundedBitmap(bitmap: Bitmap, cornerRadius: Float, imageView: ImageView) {
    val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(output)
    val color = -0xbdbdbe
    val paint = Paint()
    val rect = Rect(0, 0, bitmap.width, bitmap.height)
    val rectF = RectF(rect)
    paint.isAntiAlias = true
    canvas.drawARGB(0, 0, 0, 0)
    paint.color = color
    canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(bitmap, rect, rect, paint)
    imageView.setImageBitmap(output)
}

fun showFragment(fragment: Fragment, fragment_manager: FragmentManager, store_doc_id: String) {
    val fram = fragment_manager.beginTransaction()
    val bundle = Bundle()
    bundle.putString("store_doc_id", store_doc_id)
    fragment.arguments = bundle
    fram.replace(R.id.menu_frag_container, fragment)
    fram.addToBackStack(null)
    fram.commit()
}