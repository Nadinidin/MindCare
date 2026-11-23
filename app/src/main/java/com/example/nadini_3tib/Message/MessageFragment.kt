package com.example.nadini_3tib.Message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nadini_3tib.data.database.KonsultasiDatabase
import com.example.nadini_3tib.data.repository.KonsultasiRepository
import com.example.nadini_3tib.databinding.FragmentMessageBinding
import kotlinx.coroutines.launch

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: KonsultasiRepository
    private var kategoriAktif = "Psikiater"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "Konsultasi"
        binding.toolbar.subtitle = "Layanan kesehatan mental"

        val database = KonsultasiDatabase.getDatabase(requireContext())
        repository = KonsultasiRepository(database.konsultasiDao())

        binding.recyclerMessageItems.layoutManager =
            GridLayoutManager(requireContext(), 2)

        binding.tabKonsultasi.addTab(
            binding.tabKonsultasi.newTab().setText("Psikiater")
        )

        binding.tabKonsultasi.addTab(
            binding.tabKonsultasi.newTab().setText("Psikolog")
        )

        lifecycleScope.launch {

            repository.isiDataAwalJikaKosong()

            tampilkanData("Psikiater")

        }

        binding.fabTambahKonsultasi.setOnClickListener {
            val intent = android.content.Intent(
                requireContext(),
                KonsultasiFormActivity::class.java
            )
            startActivity(intent)
        }

        binding.tabKonsultasi.addOnTabSelectedListener(
            object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {

                override fun onTabSelected(
                    tab: com.google.android.material.tabs.TabLayout.Tab
                ) {
                    kategoriAktif = if (tab.position == 0) {
                        "Psikiater"
                    } else {
                        "Psikolog"
                    }

                    lifecycleScope.launch {
                        tampilkanData(kategoriAktif)
                    }
                }

                override fun onTabUnselected(
                    tab: com.google.android.material.tabs.TabLayout.Tab
                ) {
                }

                override fun onTabReselected(
                    tab: com.google.android.material.tabs.TabLayout.Tab
                ) {
                }
            }
        )
    }

    private suspend fun tampilkanData(kategori: String) {

        val dataDatabase = repository.getKonsultasiByKategori(kategori)

        val daftarModel = dataDatabase.map { data ->
            MessageModel(
                senderName = data.nama,
                messageText = data.pesan,
                avatarUrl = data.avatarUrl,
                distance = data.distance,
                travelTime = data.travelTime,
                kategori = data.kategori,
                id = data.id
            )
        }

        binding.recyclerMessageItems.adapter = MessageAdapter(
            requireContext(),
            daftarModel
        ) { id ->

            androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Hapus Tenaga Profesional")
                .setMessage("Apakah kamu yakin ingin menghapus data ini?")
                .setPositiveButton("Hapus") { _, _ ->

                    lifecycleScope.launch {
                        repository.hapusKonsultasiById(id)
                        tampilkanData(kategoriAktif)
                    }
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()

        if (::repository.isInitialized && _binding != null) {
            lifecycleScope.launch {
                tampilkanData(kategoriAktif)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
