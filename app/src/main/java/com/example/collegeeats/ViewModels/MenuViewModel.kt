package com.example.collegeeats.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.collegeeats.Data.MenuData
import com.example.collegeeats.Repositories.StoreRepository

class MenuViewModel(private val storeRepository: StoreRepository) : ViewModel() {
    private val _selectedDishes = MutableLiveData<MutableMap<String, Int>>()
    val selectedDishes: LiveData<MutableMap<String, Int>> = _selectedDishes

    private val _menuItems = MutableLiveData<List<MenuData>>()
    val menuItems: LiveData<List<MenuData>> = _menuItems

    suspend fun fetchMenuItems(storeId: String) {
        val menuItems = storeRepository.getMenuItems(storeId)
        _menuItems.value = menuItems
    }

    fun addDishToCart(dishId: String) {
        val currentSelectedDishes = _selectedDishes.value ?: emptyMap()
        val updatedSelectedDishes = currentSelectedDishes.toMutableMap()
        updatedSelectedDishes[dishId] = (updatedSelectedDishes[dishId] ?: 0) + 1
        _selectedDishes.value = updatedSelectedDishes
    }

    fun removeDishFromCart(dishId: String) {
        val currentSelectedDishes = _selectedDishes.value ?: emptyMap()
        val updatedSelectedDishes = currentSelectedDishes.toMutableMap()
        updatedSelectedDishes[dishId]?.let { count ->
            if (count > 1) {
                updatedSelectedDishes[dishId] = count - 1
            } else {
                updatedSelectedDishes.remove(dishId)
            }
            _selectedDishes.value = updatedSelectedDishes
        }
    }
}
