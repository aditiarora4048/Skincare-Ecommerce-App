package com.example.ecommerceapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartFragment : Fragment() {

    private lateinit var adapter: CartAdapter
    private lateinit var tvTotal: TextView
    private lateinit var tvEmpty: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerCart)
        tvTotal = view.findViewById(R.id.tvCartTotal)
        tvEmpty = view.findViewById(R.id.tvCartEmpty)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = CartAdapter(CartManager.getCartItems()) {
            updateCartUI()
        }

        recyclerView.adapter = adapter
        updateCartUI()

        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.updateItems(CartManager.getCartItems())
        updateCartUI()
    }

    private fun updateCartUI() {
        val items = CartManager.getCartItems()
        tvEmpty.visibility = if (items.isEmpty()) View.VISIBLE else View.GONE
        tvTotal.text = "Total: ₹${CartManager.getTotalPrice()}"
    }
}