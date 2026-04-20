package com.example.ecommerceapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_products, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerProducts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val products = listOf(
            Product(
                1,
                "Vitamin C Serum",
                799.0,
                "Brightens skin and reduces dullness",
                R.drawable.serum
            ),
            Product(
                2,
                "Sunscreen SPF 50",
                599.0,
                "Protects skin from harmful UV rays",
                R.drawable.sunscreen
            ),
            Product(
                3,
                "Face Wash",
                299.0,
                "Gentle cleanser for daily skincare",
                R.drawable.facewash
            ),
            Product(
                4,
                "Moisturizer",
                499.0,
                "Hydrating cream for soft smooth skin",
                R.drawable.moisturizer
            ),
            Product(
                5,
                "Night Cream",
                699.0,
                "Repairs and nourishes skin overnight",
                R.drawable.nightcream
            ),
            Product(
                6,
                "Aloe Vera Gel",
                249.0,
                "Soothes and hydrates irritated skin",
                R.drawable.alovera
            )
        )

        recyclerView.adapter = ProductAdapter(products)
        return view
    }
}