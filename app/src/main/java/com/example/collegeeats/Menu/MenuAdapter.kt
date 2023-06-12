package com.example.collegeeats.Menu

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.collegeeats.R
import com.example.collegeeats.databinding.MenuItemBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuAdapter(private val menu: List<MenuData>,private val constraintLayout: ConstraintLayout,  private val price: TextView, private val items: TextView, val fragmentManager: FragmentManager) :
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

            if (selectedDishes.containsKey(item.id)) {
                // If dish is already selected, show counter and minus icon
                val count = selectedDishes[item.id]!!
                addTextView.text = count.toString()
                minusImageView.visibility = View.VISIBLE
            } else {
                minusImageView.visibility = View.GONE
            }

            addTextView.setOnClickListener {
                // Show counter and minus icon
                addTextView.text = "1"
                minusImageView.visibility = View.VISIBLE
                plusImageView.visibility = View.VISIBLE

                // Add dish to selectedDishes map
                selectedDishes[item.id] = 1

                // Update bottom view
                updateBottomView()
            }

            plusImageView.setOnClickListener {
                val count = selectedDishes[item.id] ?: 0
                // Increment the count for the dish
                selectedDishes[item.id] = count + 1
                addTextView.text = (count + 1).toString()

                // Update bottom view
                updateBottomView()
            }

            minusImageView.setOnClickListener {
                val count = selectedDishes[item.id] ?: 0
                // Decrement the count for the dish
                if (count > 1) {
                    selectedDishes[item.id] = count - 1
                    addTextView.text = (count - 1).toString()
                } else {
                    // If count is 1, remove the dish from selectedDishes map

                    selectedDishes.remove(item.id)
                    minusImageView.visibility = View.GONE
                    addTextView.visibility = View.VISIBLE
                    plusImageView.visibility =  View.GONE
                    addTextView.text ="ADD"
                }

//                next_button.setOnClickListener {
//                    Log.d("123", "123")
//                    val sharedPreferences =itemView.context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
//                    val editor = sharedPreferences.edit()
//                    editor.putString("pending_order", Gson().toJson(selectedDishes))
//                    editor.apply()
//                    showFragment(CheckoutFragment(), fragmentManager, Constants.store_doc_id)
//                }

                updateBottomView()
        }
    }
}
    fun getData(): MutableMap<String, Int>{
        return selectedDishes
    }
    private fun updateBottomView() {
        var totalDishes = 0
        var totalPrice = 0.0

        for ((id, count) in selectedDishes) {
            val dish = menu.find { it.id == id }
            if (dish != null) {
                totalDishes += count
                totalPrice += count*(dish.price.toInt())
            }
        }

        if (totalDishes > 0) {
            constraintLayout.visibility = View.VISIBLE
            items.text = totalDishes.toString() + " ITEM SELECTED"
            price.text = "₹" + totalPrice.toString()
        } else {
            constraintLayout.visibility = View.GONE
        }
    }
}
