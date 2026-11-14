package com.example.nadini_3tib.Home.TesKesehatan

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.nadini_3tib.R
import com.example.nadini_3tib.databinding.ActivityPertanyaanTesBinding

class PertanyaanTesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPertanyaanTesBinding

    private var nomorPertanyaan = 0
    private val jawaban = IntArray(9) { -1 }

    private val daftarPertanyaan = listOf(
        "Kurang berminat atau bergairah dalam melakukan kegiatan.",
        "Merasa sedih, murung, tertekan, atau putus asa.",
        "Sulit tidur, sering terbangun, atau tidur terlalu banyak.",
        "Merasa lelah atau kurang bertenaga.",
        "Kurang nafsu makan atau makan terlalu banyak.",
        "Merasa buruk terhadap diri sendiri, merasa gagal, atau mengecewakan diri sendiri maupun keluarga.",
        "Sulit berkonsentrasi pada sesuatu, seperti membaca atau mengikuti kegiatan.",
        "Bergerak atau berbicara sangat lambat sehingga orang lain menyadarinya, atau sebaliknya merasa gelisah dan sulit untuk diam.",
        "Memiliki pikiran bahwa lebih baik kamu mati atau ingin menyakiti diri sendiri."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        binding = ActivityPertanyaanTesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.btnSebelumnya.setOnClickListener {
            simpanJawaban()

            if (nomorPertanyaan > 0) {
                nomorPertanyaan--
                tampilkanPertanyaan()
            }
        }

        binding.btnBerikutnya.setOnClickListener {
            if (!simpanJawaban()) {
                Toast.makeText(
                    this,
                    "Silakan pilih jawaban terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (nomorPertanyaan < daftarPertanyaan.lastIndex) {
                nomorPertanyaan++
                tampilkanPertanyaan()
            } else {
                tampilkanHasil()
            }
        }

        tampilkanPertanyaan()
    }

    private fun simpanJawaban(): Boolean {
        val nilai = when (binding.radioGroupJawaban.checkedRadioButtonId) {
            binding.jawaban0.id -> 0
            binding.jawaban1.id -> 1
            binding.jawaban2.id -> 2
            binding.jawaban3.id -> 3
            else -> -1
        }

        if (nilai == -1) return false

        jawaban[nomorPertanyaan] = nilai
        return true
    }

    private fun tampilkanPertanyaan() {
        binding.txtNomor.text =
            "Pertanyaan ${nomorPertanyaan + 1} dari 9"

        binding.progressTes.progress = nomorPertanyaan + 1

        binding.txtPertanyaan.text =
            daftarPertanyaan[nomorPertanyaan]

        binding.radioGroupJawaban.clearCheck()

        when (jawaban[nomorPertanyaan]) {
            0 -> binding.jawaban0.isChecked = true
            1 -> binding.jawaban1.isChecked = true
            2 -> binding.jawaban2.isChecked = true
            3 -> binding.jawaban3.isChecked = true
        }

        binding.btnSebelumnya.isEnabled = nomorPertanyaan > 0

        binding.btnBerikutnya.text =
            if (nomorPertanyaan == daftarPertanyaan.lastIndex) {
                "Lihat Hasil"
            } else {
                "Berikutnya"
            }
    }

    private fun tampilkanHasil() {
        val totalSkor = jawaban.sum()

        val kategori = when (totalSkor) {
            in 0..4 -> "Minimal"
            in 5..9 -> "Ringan"
            in 10..14 -> "Sedang"
            in 15..19 -> "Sedang hingga berat"
            else -> "Berat"
        }

        val pesan = """
            Total skor: $totalSkor dari 27

            Kategori: $kategori

            Hasil ini merupakan skrining awal, bukan diagnosis medis.
            Jika kamu mengalami keluhan, konsultasikan dengan tenaga profesional kesehatan mental.
        """.trimIndent()

        val hasilDialog = AlertDialog.Builder(this)
            .setTitle("Hasil Tes PHQ-9")
            .setMessage(pesan)
            .setPositiveButton("Selesai") { _, _ ->
                finish()
            }

        if (jawaban[8] > 0) {
            hasilDialog.setNegativeButton("Informasi bantuan") { _, _ ->
                AlertDialog.Builder(this)
                    .setTitle("Kamu tidak sendiri")
                    .setMessage(
                        "Jawaban pada pertanyaan terakhir menunjukkan adanya pikiran untuk menyakiti diri atau lebih baik mati. " +
                                "Segera ceritakan kondisi ini kepada orang yang kamu percaya dan hubungi tenaga kesehatan profesional. " +
                                "Jika kamu merasa tidak aman atau berisiko menyakiti diri sekarang, segera cari bantuan darurat dan jangan sendirian."
                    )
                    .setPositiveButton("Mengerti", null)
                    .show()
            }
        }

        hasilDialog.show()
    }
}
