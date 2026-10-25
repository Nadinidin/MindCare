package com.example.nadini_3tib.Message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nadini_3tib.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

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

        val daftarPesan = listOf(
            MessageModel(
                "Dr. Andini Putri, M.Psi.",
                "Halo, ada yang ingin kamu ceritakan?",
                "https://i.pravatar.cc/150?img=47"
            ),
            MessageModel(
                "Psikolog Budi Santoso",
                "Jangan ragu untuk berbagi cerita.",
                "https://i.pravatar.cc/150?img=12"
            ),
            MessageModel(
                "Dr. Siti Rahma, M.Psi.",
                "Bagaimana kabarmu hari ini?",
                "https://i.pravatar.cc/150?img=44"
            )
        )

        binding.listMessageItems.apply {
            adapter = MessageAdapter(
                requireContext(),
                daftarPesan
            )
            setBackgroundColor(android.graphics.Color.TRANSPARENT)
            divider = null
            dividerHeight = 0
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
