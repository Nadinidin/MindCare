package com.example.nadini_3tib.Message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nadini_3tib.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private lateinit var daftarPsikiater: List<MessageModel>
    private lateinit var daftarPsikolog: List<MessageModel>

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


        // Data Psikiater
        daftarPsikiater = listOf(
            MessageModel(
                "Dr. Andini Putri, Sp.KJ",
                "Halo, ada yang ingin kamu ceritakan?",
                "https://i.pravatar.cc/150?img=47",
                "12 km",
                "25 menit"
            ),
            MessageModel(
                "Dr. Rizky Pratama, Sp.KJ",
                "Siap membantu kesehatan mentalmu.",
                "https://i.pravatar.cc/150?img=12",
                "5 km",
                "12 menit"
            ),
            MessageModel(
                "Dr. Maya Lestari, Sp.KJ",
                "Mari bicarakan apa yang kamu rasakan.",
                "https://i.pravatar.cc/150?img=44",
                "8 km",
                "18 menit"
            ),
            MessageModel(
                "Dr. Fajar Ramadhan, Sp.KJ",
                "Kesehatan mentalmu adalah prioritas.",
                "https://i.pravatar.cc/150?img=13",
                "15 km",
                "30 menit"
            )
        )

        // Data Psikolog
        daftarPsikolog = listOf(
            MessageModel(
                "Psikolog Budi Santoso, M.Psi.",
                "Jangan ragu untuk berbagi cerita.",
                "https://i.pravatar.cc/150?img=12",
                "4 km",
                "10 menit"
            ),
            MessageModel(
                "Psikolog Siti Rahma, M.Psi.",
                "Bagaimana kabarmu hari ini?",
                "https://i.pravatar.cc/150?img=44",
                "7 km",
                "15 menit"
            ),
            MessageModel(
                "Psikolog Nadia Putri, M.Psi.",
                "Aku siap mendengarkan ceritamu.",
                "https://i.pravatar.cc/150?img=47",
                "10 km",
                "20 menit"
            ),
            MessageModel(
                "Psikolog Dimas Pratama, M.Psi.",
                "Mari kenali dirimu lebih baik.",
                "https://i.pravatar.cc/150?img=13",
                "6 km",
                "14 menit"
            )
        )

        // Mengatur RecyclerView menjadi Grid 2 kolom
        binding.recyclerMessageItems.layoutManager =
            GridLayoutManager(requireContext(), 2)

        binding.recyclerMessageItems.adapter = MessageAdapter(
            requireContext(),
            daftarPsikiater
        )

        // Membuat TabLayout
        binding.tabKonsultasi.addTab(
            binding.tabKonsultasi.newTab().setText("Psikiater")
        )

        binding.tabKonsultasi.addTab(
            binding.tabKonsultasi.newTab().setText("Psikolog")
        )

        // Mengganti data berdasarkan tab yang dipilih
        binding.tabKonsultasi.addOnTabSelectedListener(
            object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {

                override fun onTabSelected(
                    tab: com.google.android.material.tabs.TabLayout.Tab
                ) {
                    val daftarPilihan = if (tab.position == 0) {
                        daftarPsikiater
                    } else {
                        daftarPsikolog
                    }

                    binding.recyclerMessageItems.adapter = MessageAdapter(
                        requireContext(),
                        daftarPilihan
                    )
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
