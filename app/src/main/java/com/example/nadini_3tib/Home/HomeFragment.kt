package com.example.nadini_3tib.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nadini_3tib.Home.TesKesehatan.TesPerkenalanActivity
import com.example.nadini_3tib.Home.HalArtikel.ArtikelActivity
import com.example.nadini_3tib.Home.HalArtikel.LimaActivity
import com.example.nadini_3tib.Home.ruangsehat.SixthActivity
import com.example.nadini_3tib.Home.TesKesehatan.TesKesehatanActivity
import com.example.nadini_3tib.Home.TesKesehatan.TesPerawatanActivity
import com.example.nadini_3tib.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.jvm.java

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext()
            .getSharedPreferences("user_pref", MODE_PRIVATE)

        val username = sharedPref.getString("username", null)

        binding.txtSubtitle.text = if (username.isNullOrBlank()) {
            "Halo! Yuk, jaga kesehatan mentalmu hari ini."
        } else {
            "Halo, $username! Yuk, jaga kesehatan mentalmu hari ini."
        }

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Psikiater adalah dokter yang menangani kesehatan mental.",
                Snackbar.LENGTH_LONG
            ).show()
        }

        binding.btnPsikolog.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Psikolog membantu memahami emosi, pikiran, dan perilaku.",
                Snackbar.LENGTH_LONG
            ).show()
        }

        // Membuka halaman TabLayout Edukasi
        binding.btnArtikel.setOnClickListener {
            startActivity(
                Intent(requireContext(), ArtikelActivity::class.java)
            )
        }

        binding.btnToLima.setOnClickListener {
            startActivity(
                Intent(requireContext(), LimaActivity::class.java)
            )
        }

        binding.btnSixth.setOnClickListener {
            startActivity(
                Intent(requireContext(), SixthActivity::class.java)
            )
        }

        // Tes Kesehatan Mental
        binding.btnTesMental.setOnClickListener {
            startActivity(
                Intent(requireContext(), TesKesehatanActivity::class.java)
            )
        }

        binding.btnTesPerawatan.setOnClickListener {
            startActivity(
                Intent(requireContext(), TesPerawatanActivity::class.java)
            )
        }

// Tes Stres
        binding.btnTesStres.setOnClickListener {
            bukaHalamanPengenalan("stres")
        }

// Tes Kecemasan
        binding.btnTesKecemasan.setOnClickListener {
            bukaHalamanPengenalan("kecemasan")
        }

// Tes Remaja
        binding.btnTesRemaja.setOnClickListener {
            bukaHalamanPengenalan("remaja")
        }
    }
    private fun bukaHalamanPengenalan(jenis: String) {
        val intent = Intent(
            requireContext(),
            TesPerkenalanActivity::class.java
        )

        intent.putExtra("jenis_tes", jenis)
        startActivity(intent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
