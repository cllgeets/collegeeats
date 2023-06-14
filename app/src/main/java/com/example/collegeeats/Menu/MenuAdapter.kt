package com.example.collegeeats.Menu

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.collegeeats.Data.MenuData
import com.example.collegeeats.R
import com.example.collegeeats.ViewModels.MenuViewModel
import com.example.collegeeats.databinding.MenuItemBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuAdapter(private val menu: List<MenuData>, private val constraintLayout: ConstraintLayout, private val price: TextView, private val items: TextView, private val viewModel: MenuViewModel, private val lifecycleOwner: LifecycleOwner,) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    private lateinit var binding: MenuItemBinding
    private val selectedDishes = mutableMapOf<String, Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = menu.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = menu[position]
        holder.bind(item)
        Log.d("123", item.toString())
    }

    inner class ViewHolder(private val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

       private val addTextView = binding.addTextView
        private val minusImageView = binding.minusImageView
        private val plusImageView = binding.plusImageView

        suspend fun loadImage(imageUrl: String) {
            withContext(Dispatchers.IO) {
                try {
                    val requestOptions = RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.food_mock)

                    Glide.with(itemView)
                        .load(imageUrl)
                        .apply(requestOptions)
                        .into(binding.storeImage)
                        .waitForLayout()
                } catch (e: Exception) {
                    // Handle error here
                    withContext(Dispatchers.Main) {
                        // Show placeholder image or error state
                    }
                }
            }
        }

        fun bind(item: MenuData) {
            binding.dishName.text = item.name
            binding.description.text = item.description
            binding.dishPrice.text = "₹" + item.price.toString()
            binding.vegan.setImageResource(if (item.veg_status == true) R.drawable.veg else R.drawable.non_veg)

            Glide.with(itemView)
                .load(item.menu_image.toString())
                .centerCrop()
                .placeholder(R.drawable.food_mock)
                .into(binding.storeImage)


            val imageView = binding.storeImage
            val imageUrl = item.menu_image
            Glide.with(itemView.context).load(imageUrl).placeholder(R.color.Gray).into(imageView)

            viewModel.selectedDishes.observe(lifecycleOwner) { selectedDishes ->
                if (selectedDishes.containsKey(item.id)) {
                    val count = selectedDishes[item.id]!!
                    addTextView.text = count.toString()
                    minusImageView.visibility = View.VISIBLE
                    plusImageView.visibility = View.VISIBLE
                } else {
                    minusImageView.visibility = View.GONE
                    plusImageView.visibility = View.GONE
                    addTextView.text = "Add"
                }
                updateBottomView()
            }

            addTextView.setOnClickListener {
                viewModel.addDishToCart(item.id)
            }

            plusImageView.setOnClickListener {
                viewModel.addDishToCart(item.id)
            }

            minusImageView.setOnClickListener {
                viewModel.removeDishFromCart(item.id)
            }
        }
    }
    fun getData(): MutableMap<String, Int>{
        return viewModel.selectedDishes.value!!
    }

    private fun updateBottomView() {
        val selectedDishes = viewModel.selectedDishes.value ?: emptyMap()
        var totalDishes = 0
        var totalPrice = 0.0

        for ((id, count) in selectedDishes) {
            val dish = menu.find { it.id == id }
            if (dish != null) {
                totalDishes += count
                totalPrice += count * dish.price.toInt()
            }
        }

        if (totalDishes > 0) {
            constraintLayout.visibility = View.VISIBLE
            items.text = "$totalDishes ITEM SELECTED"
            price.text = "₹$totalPrice"
        } else {
            constraintLayout.visibility = View.GONE
        }
    }
}
