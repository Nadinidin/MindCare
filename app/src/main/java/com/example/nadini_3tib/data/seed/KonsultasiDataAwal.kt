package com.example.nadini_3tib.data.seed

import com.example.nadini_3tib.data.entity.KonsultasiEntity

object KonsultasiDataAwal {

    val daftarPsikiater = listOf(
        KonsultasiEntity(
            nama = "Dr. Andini Putri, Sp.KJ",
            pesan = "Halo, ada yang ingin kamu ceritakan?",
            avatarUrl = "https://i.pravatar.cc/150?img=47",
            kategori = "Psikiater",
            distance = "12 km",
            travelTime = "25 menit"
        ),
        KonsultasiEntity(
            nama = "Dr. Rizky Pratama, Sp.KJ",
            pesan = "Siap membantu kesehatan mentalmu.",
            avatarUrl = "https://i.pravatar.cc/150?img=12",
            kategori = "Psikiater",
            distance = "5 km",
            travelTime = "12 menit"
        ),
        KonsultasiEntity(
            nama = "Dr. Maya Lestari, Sp.KJ",
            pesan = "Mari bicarakan apa yang kamu rasakan.",
            avatarUrl = "https://i.pravatar.cc/150?img=44",
            kategori = "Psikiater",
            distance = "8 km",
            travelTime = "18 menit"
        ),
        KonsultasiEntity(
            nama = "Dr. Fajar Ramadhan, Sp.KJ",
            pesan = "Kesehatan mentalmu adalah prioritas.",
            avatarUrl = "https://i.pravatar.cc/150?img=13",
            kategori = "Psikiater",
            distance = "15 km",
            travelTime = "30 menit"
        )
    )

    val daftarPsikolog = listOf(
        KonsultasiEntity(
            nama = "Psikolog Budi Santoso, M.Psi.",
            pesan = "Jangan ragu untuk berbagi cerita.",
            avatarUrl = "https://i.pravatar.cc/150?img=12",
            kategori = "Psikolog",
            distance = "4 km",
            travelTime = "10 menit"
        ),
        KonsultasiEntity(
            nama = "Psikolog Siti Rahma, M.Psi.",
            pesan = "Bagaimana kabarmu hari ini?",
            avatarUrl = "https://i.pravatar.cc/150?img=44",
            kategori = "Psikolog",
            distance = "7 km",
            travelTime = "15 menit"
        ),
        KonsultasiEntity(
            nama = "Psikolog Nadia Putri, M.Psi.",
            pesan = "Aku siap mendengarkan ceritamu.",
            avatarUrl = "https://i.pravatar.cc/150?img=47",
            kategori = "Psikolog",
            distance = "10 km",
            travelTime = "20 menit"
        ),
        KonsultasiEntity(
            nama = "Psikolog Dimas Pratama, M.Psi.",
            pesan = "Mari kenali dirimu lebih baik.",
            avatarUrl = "https://i.pravatar.cc/150?img=13",
            kategori = "Psikolog",
            distance = "6 km",
            travelTime = "14 menit"
        )
    )
}