package com.example.ecommerceapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private var cartItems: List<Pair<Product, Int>>,
    private val onCartChanged: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvCartName)
        val tvQty: TextView = itemView.findViewById(R.id.tvCartQty)
        val tvPrice: TextView = itemView.findViewById(R.id.tvCartPrice)
        val btnIncrease: Button = itemView.findViewById(R.id.btnIncrease)
        val btnDecrease: Button = itemView.findViewById(R.id.btnDecrease)
        val btnRemove: Button = itemView.findViewById(R.id.btnRemoveItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val (product, qty) = cartItems[position]

        holder.tvName.text = product.name
        holder.tvQty.text = qty.toString()
        holder.tvPrice.text = "₹${product.price * qty}"

        holder.btnIncrease.setOnClickListener {
            CartManager.increaseQuantity(product)
            updateItems(CartManager.getCartItems())
            onCartChanged()
        }

        holder.btnDecrease.setOnClickListener {
            CartManager.decreaseQuantity(product)
            updateItems(CartManager.getCartItems())
            onCartChanged()
        }

        holder.btnRemove.setOnClickListener {
            CartManager.removeItem(product)
            updateItems(CartManager.getCartItems())
            onCartChanged()
        }
    }

    fun updateItems(newItems: List<Pair<Product, Int>>) {
        cartItems = newItems
        notifyDataSetChanged()
    }
}