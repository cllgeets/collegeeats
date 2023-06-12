package com.example.collegeeats.Delivery

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.collegeeats.Menu.MenuActivity
import com.example.collegeeats.R
import com.example.collegeeats.databinding.StoreItemBinding

class StoreAdapter(private val stores: List<Store>) :
    RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {
    private lateinit var binding: StoreItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
         binding = StoreItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StoreViewHolder(binding)
    }

    override fun getItemCount() = stores.size

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = stores[position]
        holder.bind(store)

        binding.itemCardView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MenuActivity::class.java)
            intent.putExtra("store_doc_id", store.id.toString())
            holder.itemView.context.startActivity(intent)
        }
    }

    inner class StoreViewHolder(private val binding: StoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(store: Store) {

            binding.storeName.text = store.name
            binding.storeStatus.text = store.delivering_status
            binding.storeRating.text = store.rating + " ⭐"

            Glide.with(itemView)
                .load(store.storeImage)
                .centerCrop()
                .placeholder(R.drawable.food_mock)
                .into(binding.storeImage)
        }
    }
}
