package com.example.collegeeats.Menu

data class MenuData(
    var id: String ="",
    val name: String="",
    val price: String="",
    val description: String="",
    val menu_image: String="",
    val rating: String="",
    val veg_status: Boolean? = false
)
