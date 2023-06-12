package com.example.collegeeats.CommonUtils

import android.content.Context
import android.content.SharedPreferences
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class PreferencesManager(private val context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

    fun savePendingOrder(data: MutableMap<String, Int>) {
        sharedPreferences.edit {
            putString("pending_order", Gson().toJson(data))
            putBoolean("order_cart", true)
        }
    }

    fun saveStoreDocId(storeDocId: String) {
        sharedPreferences.edit {
            putString("store_doc_id", storeDocId)
        }
    }

    fun saveTotalItemCart(totalItemCart: Int) {
        sharedPreferences.edit {
            putString("total_item_cart", totalItemCart.toString())
        }
    }

    fun getPendingOrder(): MutableMap<String, Int>? {
        val pendingOrderJson = sharedPreferences.getString("pending_order", null)
        return Gson().fromJson(pendingOrderJson, object : TypeToken<MutableMap<String, Int>>() {}.type)
    }

    fun getStoreDocId(): String? {
        return sharedPreferences.getString("store_doc_id", null)
    }

    fun getTotalItemCart(): Int {
        val totalItemCartString = sharedPreferences.getString("total_item_cart", "0")
        return totalItemCartString?.toIntOrNull() ?: 0
    }

    fun clearPendingOrder() {
        sharedPreferences.edit {
            remove("pending_order")
            remove("order_cart")
            remove("store_doc_id")
            remove("total_item_cart")
        }
    }

    private inline fun SharedPreferences.edit(
        commit: Boolean = false,
        action: SharedPreferences.Editor.() -> Unit
    ) {
        val editor = edit()
        action(editor)
        if (commit) {
            editor.commit()
        } else {
            editor.apply()
        }
    }
}
