package com.example.nadini_3tib.Home.TesKesehatan

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.nadini_3tib.R
import com.example.nadini_3tib.databinding.ActivityTesLainnyaBinding

class TesLainnyaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTesLainnyaBinding

    private var nomor = 0
    private val jawaban = IntArray(10) { -1 }

    private val jenisTes by lazy {
        intent.getStringExtra("jenis_tes") ?: "perawatan"
    }

    private val judul: String
        get() = when (jenisTes) {
            "perawatan" -> "Tes Perawatan Diri"
            "stres" -> "Tes Stres"
            "kecemasan" -> "Tes Kecemasan"
            "remaja" -> "Tes Remaja"
            else -> "Tes Evaluasi Diri"
        }

    private val pertanyaan: List<String>
        get() = when (jenisTes) {
            "perawatan" -> listOf(
                "Saya menjaga kebersihan diri setiap hari.",
                "Saya mengonsumsi makanan bergizi.",
                "Saya minum air putih yang cukup.",
                "Saya memiliki waktu istirahat yang cukup.",
                "Saya melakukan aktivitas fisik atau olahraga.",
                "Saya menjaga kebersihan lingkungan.",
                "Saya menyediakan waktu untuk melakukan hal yang disukai.",
                "Saya mampu mengungkapkan perasaan dengan baik.",
                "Saya meluangkan waktu untuk beristirahat ketika lelah.",
                "Saya memperhatikan kondisi kesehatan diri."
            )

            "stres" -> listOf(
                "Saya merasa kewalahan menghadapi aktivitas sehari-hari.",
                "Saya merasa sulit mengendalikan banyaknya tanggung jawab.",
                "Saya merasa mudah marah atau tersinggung.",
                "Saya sulit beristirahat karena memikirkan masalah.",
                "Saya merasa kehabisan energi akibat tekanan.",
                "Saya sulit berkonsentrasi ketika menghadapi masalah.",
                "Saya merasa terbebani oleh tugas atau pekerjaan.",
                "Saya merasa sulit mengatur waktu.",
                "Saya merasa tegang ketika menghadapi tuntutan.",
                "Saya merasa kesulitan menenangkan pikiran."
            )

            "kecemasan" -> listOf(
                "Saya merasa khawatir terhadap sesuatu yang belum terjadi.",
                "Saya sulit menghentikan pikiran yang mengkhawatirkan.",
                "Saya merasa gelisah tanpa alasan yang jelas.",
                "Saya merasa takut menghadapi situasi tertentu.",
                "Saya sulit merasa tenang.",
                "Saya sering memikirkan kemungkinan buruk.",
                "Saya merasa gugup ketika menghadapi orang lain.",
                "Saya sulit tidur karena pikiran yang mengganggu.",
                "Saya merasa khawatir secara berlebihan.",
                "Saya sulit mengendalikan rasa cemas."
            )

            else -> listOf(
                "Saya merasa nyaman dengan diri sendiri.",
                "Saya dapat mengelola emosi dengan baik.",
                "Saya memiliki hubungan yang baik dengan keluarga.",
                "Saya merasa diterima oleh teman-teman.",
                "Saya mampu menghadapi masalah sehari-hari.",
                "Saya memiliki semangat untuk belajar.",
                "Saya dapat mengatur waktu belajar dan istirahat.",
                "Saya berani menyampaikan pendapat.",
                "Saya memiliki kegiatan positif di luar belajar.",
                "Saya mengetahui kepada siapa harus meminta bantuan."
            )
        }

    private val pilihan = listOf(
        "Tidak pernah",
        "Beberapa kali",
        "Sering",
        "Hampir selalu"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        binding = ActivityTesLainnyaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = judul
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        tampilkanPertanyaan()

        binding.btnSebelumnya.setOnClickListener {
            simpanJawaban()

            if (nomor > 0) {
                nomor--
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

            if (nomor < pertanyaan.lastIndex) {
                nomor++
                tampilkanPertanyaan()
            } else {
                tampilkanHasil()
            }
        }
    }

    private fun tampilkanPertanyaan() {
        binding.txtNomor.text = "Pertanyaan ${nomor + 1} dari 10"
        binding.progressTes.progress = nomor + 1
        binding.txtPertanyaan.text = pertanyaan[nomor]

        binding.radioGroupJawaban.clearCheck()

        when (jawaban[nomor]) {
            0 -> binding.jawaban0.isChecked = true
            1 -> binding.jawaban1.isChecked = true
            2 -> binding.jawaban2.isChecked = true
            3 -> binding.jawaban3.isChecked = true
        }

        binding.btnSebelumnya.isEnabled = nomor > 0

        binding.btnBerikutnya.text =
            if (nomor == pertanyaan.lastIndex) "Lihat Hasil"
            else "Berikutnya"
    }

    private fun simpanJawaban(): Boolean {
        val nilai = when (binding.radioGroupJawaban.checkedRadioButtonId) {
            R.id.jawaban0 -> 0
            R.id.jawaban1 -> 1
            R.id.jawaban2 -> 2
            R.id.jawaban3 -> 3
            else -> -1
        }

        if (nilai == -1) return false

        jawaban[nomor] = nilai
        return true
    }

    private fun tampilkanHasil() {
        val total = jawaban.sum()

        val kategori = if (jenisTes == "perawatan" ||
            jenisTes == "remaja"
        ) {
            when (total) {
                in 0..9 -> "Perlu Ditingkatkan"
                in 10..19 -> "Cukup"
                in 20..30 -> "Baik"
                else -> "Sangat Baik"
            }
        } else {
            when (total) {
                in 0..9 -> "Rendah"
                in 10..19 -> "Sedang"
                in 20..30 -> "Tinggi"
                else -> "Sangat Tinggi"
            }
        }

        val penjelasan = when (jenisTes) {
            "perawatan" ->
                "Skor menunjukkan gambaran kebiasaan perawatan diri. " +
                        "Gunakan hasil ini untuk mengenali kebiasaan yang masih dapat ditingkatkan."

            "stres" ->
                "Skor menunjukkan frekuensi pengalaman terkait tekanan dan stres. " +
                        "Pertimbangkan untuk beristirahat dan mencari dukungan jika diperlukan."

            "kecemasan" ->
                "Skor menunjukkan frekuensi pengalaman terkait rasa khawatir dan cemas. " +
                        "Pertimbangkan mencari dukungan jika hal tersebut mengganggu aktivitas."

            else ->
                "Skor merupakan bahan refleksi mengenai kesejahteraan dan kebiasaan positif remaja."
        }

        AlertDialog.Builder(this)
            .setTitle("Hasil $judul")
            .setMessage(
                "Total skor: $total dari 30\n\n" +
                        "Kategori: $kategori\n\n" +
                        "$penjelasan\n\n" +
                        "Hasil ini merupakan evaluasi mandiri, bukan diagnosis medis."
            )
            .setPositiveButton("Selesai") { _, _ ->
                finish()
            }
            .show()
    }
}
