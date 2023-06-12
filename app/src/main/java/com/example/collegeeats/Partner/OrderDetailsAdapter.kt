package com.example.collegeeats.Partner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.collegeeats.databinding.BasicDishItemBinding

class OrderDetailsAdapter(private val dishesList: MutableList<OrderDetails>) : RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder>() {
    private lateinit var binding: BasicDishItemBinding

    class ViewHolder(binding: BasicDishItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView = binding.tvDishName
        val priceTextView = binding.tvDishTotalPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = BasicDishItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dish = dishesList[position]
        holder.nameTextView.text = dish.dishName + "(" + dish.quantity + ")"
        holder.priceTextView.text = "₹" + dish.totalPrice.toString()
    }

    override fun getItemCount(): Int {
        return dishesList.size
    }
}
