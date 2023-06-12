package com.example.collegeeats.OrderTrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.collegeeats.R
import com.example.collegeeats.databinding.ActivityOrdertrackBinding
import com.google.firestore.v1.StructuredQuery.Order

class OrdertrackActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrdertrackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdertrackBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val order_doc_id = intent.getStringExtra("order_doc_id").toString()

        show_ordertacking(OrderTracking(), supportFragmentManager, order_doc_id)
    }
    fun show_ordertacking(fragment: Fragment, fragment_manager: FragmentManager, order_doc_id: String){
        val fram = fragment_manager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("order_doc_id", order_doc_id)
        fragment.arguments = bundle
        fram.replace(R.id.order_track_container, fragment)
        fram.commit()
    }
}