package com.example.collegeeats.CheckOut

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.collegeeats.databinding.SingleDishItemBinding

class PaymentDishesAdapter() : RecyclerView.Adapter<PaymentDishesAdapter.ViewHolder>() {
    private var dishesList: MutableList<MutableList<String>> = mutableListOf()
    private lateinit var binding: SingleDishItemBinding

    class ViewHolder(binding: SingleDishItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView = binding.foodOrders
        val priceTextView = binding.tvSinglePrice
        val quantityTextView = binding.addTextView
        val totalTextView = binding.tvTotalPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = SingleDishItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dish = dishesList[position]
        holder.nameTextView.text = dish[1]
        holder.priceTextView.text = "₹" + dish[2]
        holder.quantityTextView.text = dish[3]
        holder.totalTextView.text = "₹" + dish[4]
    }

    override fun getItemCount(): Int {
        return dishesList.size
    }

    fun setDishesList(list: MutableList<String>) {
        dishesList.add(list)
        notifyDataSetChanged()
    }
}
