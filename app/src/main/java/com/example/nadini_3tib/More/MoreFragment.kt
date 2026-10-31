package com.example.nadini_3tib.More

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.nadini_3tib.LoginActivity
import com.example.nadini_3tib.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    private val dataList = listOf(
        mapOf(
            "title" to "Profil Pengguna",
            "desc" to "Lihat informasi akun kamu"
        ),
        mapOf(
            "title" to "Keluar dari Akun",
            "desc" to "Logout dari aplikasi"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext()
            .getSharedPreferences("user_pref", MODE_PRIVATE)

        val adapter = SimpleAdapter(
            requireContext(),
            dataList,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding.listViewItems.adapter = adapter
        binding.listViewItems.visibility = View.VISIBLE
        binding.listViewItems.setBackgroundColor(android.graphics.Color.TRANSPARENT)

        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->

            when (position) {
                0 -> {
                    val username = sharedPref.getString(
                        "username",
                        null
                    )

                    val pesan = if (username.isNullOrBlank()) {
                        "Username belum tersedia"
                    } else {
                        "Username: $username"
                    }

                    Toast.makeText(
                        requireContext(),
                        pesan,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                1 -> {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Keluar dari MindCare?")
                        .setMessage(
                            "Apakah kamu yakin ingin keluar dari akunmu?"
                        )
                        .setPositiveButton("Ya, Logout") { dialog, _ ->
                            sharedPref.edit().clear().apply()

                            dialog.dismiss()

                            startActivity(
                                Intent(
                                    requireContext(),
                                    LoginActivity::class.java
                                )
                            )

                            requireActivity().finish()
                        }
                        .setNegativeButton("Batal", null)
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
