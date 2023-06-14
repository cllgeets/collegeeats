package com.example.collegeeats.Menu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collegeeats.CheckOut.CheckoutFragment
import com.example.collegeeats.CommonUtils.PreferencesManager
import com.example.collegeeats.Data.MenuData
import com.example.collegeeats.Repositories.StoreRepository
import com.example.collegeeats.ViewModels.MenuViewModel
import com.example.collegeeats.activities.MainActivity
import com.example.collegeeats.databinding.FragmentMenuBinding
import kotlinx.coroutines.launch

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    var store_doc_id: String? = "";
    private lateinit var menuList: MutableList<MenuData>
    private lateinit var preferencesManager: PreferencesManager
    lateinit var storeRepository: StoreRepository
    lateinit var menuViewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        store_doc_id = arguments?.getString("store_doc_id")

        val appBarLayout = binding.appBarlayout
//        val appBarLayoutParams = appBarLayout.layoutParams as CoordinatorLayout.LayoutParams

        preferencesManager = PreferencesManager(requireContext())
        storeRepository = StoreRepository()
        menuViewModel = MenuViewModel(storeRepository)

        val shimmerFrameLayout = binding.deliveryShimmer
        shimmerFrameLayout.startShimmer()

        menuList = mutableListOf()

        lifecycleScope.launch {
            try {
                MenuFunctions(requireActivity(), binding).GetStoreData(store_doc_id.toString())
            } catch (e: Exception) {

            }
        }

        lifecycleScope.launch {
            try {
                menuViewModel.fetchMenuItems(store_doc_id.toString())
                val menuItems = menuViewModel.menuItems.value
                displayMenuItems(menuItems!!)
            } catch (e: Exception) {

            } finally {
                binding.deliveryShimmer.visibility = View.GONE
                binding.visibleFrag.visibility = View.VISIBLE
                shimmerFrameLayout.stopShimmer()
            }
        }

        binding.backPress.setOnClickListener {
            getBack()
        }

        binding.nextButton.setOnClickListener {
            val adapter = binding.menuRecycler.adapter as MenuAdapter
            val data: MutableMap<String, Int> = adapter.getData()
            val total_item_cart = data.size

            preferencesManager.savePendingOrder(data)
            preferencesManager.saveStoreDocId(store_doc_id.toString())
            preferencesManager.saveTotalItemCart(total_item_cart)

            showFragment2(
                CheckoutFragment(),
                requireActivity().supportFragmentManager,
                store_doc_id.toString()
            )
        }
        return view
    }

    suspend fun displayMenuItems(menuItems: List<MenuData>) {
        binding.menuRecycler.layoutManager = LinearLayoutManager(context)
        val adapter = MenuAdapter(
            menuItems,
            binding.constraint,
            binding.tvTotalPrice,
            binding.tvSelectedDishes,
            menuViewModel,
            viewLifecycleOwner
        )
        binding.menuRecycler.adapter = adapter
    }

    fun getBack(){
        val intent = Intent(requireContext(), MainActivity::class.java)
        requireContext().startActivity(intent)
        requireActivity().finish()
    }
}