package com.example.ecommerceapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragment : Fragment() {

    private lateinit var tvUserName: TextView
    private lateinit var tvUserPhone: TextView
    private lateinit var tvUserEmail: TextView
    private lateinit var tvUserAddress: TextView
    private lateinit var btnLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        tvUserName = view.findViewById(R.id.tvUserName)
        tvUserPhone = view.findViewById(R.id.tvUserPhone)
        tvUserEmail = view.findViewById(R.id.tvUserEmail)
        tvUserAddress = view.findViewById(R.id.tvUserAddress)
        btnLogout = view.findViewById(R.id.btnLogout)

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if (userId != null) {
            FirebaseFirestore.getInstance()
                .collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        tvUserName.text = "Name: ${document.getString("name") ?: ""}"
                        tvUserPhone.text = "Phone: ${document.getString("phone") ?: ""}"
                        tvUserEmail.text = "Email: ${document.getString("email") ?: ""}"
                        tvUserAddress.text = "Address: ${document.getString("address") ?: ""}"
                    }
                }
        }

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }

        return view
    }
}