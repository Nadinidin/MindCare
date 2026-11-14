package com.example.nadini_3tib.Home.TesKesehatan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.nadini_3tib.R
import com.example.nadini_3tib.databinding.ActivityTesPerkenalanBinding

class TesPerkenalanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTesPerkenalanBinding

    private val jenisTes by lazy {
        intent.getStringExtra("jenis_tes") ?: "stres"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        binding = ActivityTesPerkenalanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        tampilkanInformasiTes()

        binding.btnMulaiTes.setOnClickListener {
            val intent = Intent(this, TesLainnyaActivity::class.java)
            intent.putExtra("jenis_tes", jenisTes)
            startActivity(intent)
        }
    }

    private fun tampilkanInformasiTes() {

        val judul: String
        val deskripsi: String
        val tentang: String

        when (jenisTes) {
            "stres" -> {
                judul = "Tes Stres"
                deskripsi =
                    "Kenali tingkat tekanan dan stres yang mungkin kamu alami dalam kehidupan sehari-hari."

                tentang =
                    "Tes ini berisi pertanyaan mengenai tekanan aktivitas, tanggung jawab, konsentrasi, dan kemampuan mengelola stres."
            }

            "kecemasan" -> {
                judul = "Tes Kecemasan"
                deskripsi =
                    "Kenali pengalaman rasa khawatir, gelisah, dan kecemasan yang mungkin kamu alami."

                tentang =
                    "Tes ini berisi pertanyaan mengenai kekhawatiran, ketegangan, rasa takut, dan kemampuan menenangkan pikiran."
            }

            else -> {
                judul = "Tes Remaja"
                deskripsi =
                    "Kenali kondisi kesejahteraan diri, emosi, hubungan sosial, dan kebiasaan positif dalam kehidupan remaja."

                tentang =
                    "Tes ini berisi pertanyaan mengenai kepercayaan diri, hubungan keluarga, pertemanan, kegiatan belajar, dan pengelolaan emosi."
            }
        }

        binding.toolbar.title = judul
        binding.txtJudulTes.text = judul
        binding.txtDeskripsiTes.text = deskripsi
        binding.txtTentangTes.text = tentang
    }
}
