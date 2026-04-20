package com.example.ecommerceapp

object CartManager {
    private val cartMap = mutableMapOf<Product, Int>()

    fun addItem(product: Product) {
        cartMap[product] = (cartMap[product] ?: 0) + 1
    }

    fun increaseQuantity(product: Product) {
        cartMap[product] = (cartMap[product] ?: 0) + 1
    }

    fun decreaseQuantity(product: Product) {
        val currentQty = cartMap[product] ?: 0
        if (currentQty > 1) {
            cartMap[product] = currentQty - 1
        } else {
            cartMap.remove(product)
        }
    }

    fun removeItem(product: Product) {
        cartMap.remove(product)
    }

    fun getCartItems(): List<Pair<Product, Int>> {
        return cartMap.toList()
    }

    fun getTotalPrice(): Double {
        return cartMap.entries.sumOf { it.key.price * it.value }
    }

    fun clearCart() {
        cartMap.clear()
    }

    fun isEmpty(): Boolean {
        return cartMap.isEmpty()
    }
}