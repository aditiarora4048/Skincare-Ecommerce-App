package com.example.ecommerceapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class CheckoutFragment : Fragment() {

    private lateinit var tvSummary: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnPlaceOrder: Button
    private lateinit var etAddress: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_checkout, container, false)

        tvSummary = view.findViewById(R.id.tvCheckoutSummary)
        radioGroup = view.findViewById(R.id.radioPaymentGroup)
        btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder)
        etAddress = view.findViewById(R.id.etAddress)

        updateSummary()

        btnPlaceOrder.setOnClickListener {
            val address = etAddress.text.toString().trim()

            if (CartManager.isEmpty()) {
                Toast.makeText(requireContext(), "Cart is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (address.isEmpty()) {
                Toast.makeText(requireContext(), "Enter delivery address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(requireContext(), "Select payment method", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val paymentMethod = view.findViewById<RadioButton>(selectedId).text.toString()

            Toast.makeText(
                requireContext(),
                "Order placed with $paymentMethod",
                Toast.LENGTH_LONG
            ).show()

            CartManager.clearCart()
            updateSummary()
            etAddress.setText("")
            radioGroup.clearCheck()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        updateSummary()
    }

    private fun updateSummary() {
        tvSummary.text = "Payable Amount: ₹${CartManager.getTotalPrice()}"
    }
}