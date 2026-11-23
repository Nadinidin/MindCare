package com.example.nadini_3tib.Message

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nadini_3tib.data.database.KonsultasiDatabase
import com.example.nadini_3tib.data.entity.KonsultasiEntity
import com.example.nadini_3tib.data.repository.KonsultasiRepository
import com.example.nadini_3tib.databinding.ActivityKonsultasiFormBinding
import kotlinx.coroutines.launch

class KonsultasiFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKonsultasiFormBinding

    private var idKonsultasi = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKonsultasiFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarForm.setNavigationOnClickListener {
            finish()
        }

        val idKonsultasi = intent.getIntExtra("ID", -1)

            if (idKonsultasi != -1) {
            binding.toolbarForm.title = "Edit Tenaga Profesional"

            binding.editNama.setText(intent.getStringExtra("NAMA"))
            binding.editPesan.setText(intent.getStringExtra("PESAN"))
            binding.editAvatarUrl.setText(intent.getStringExtra("AVATAR"))
            binding.editKategori.setText(intent.getStringExtra("KATEGORI"))
            binding.editDistance.setText(intent.getStringExtra("DISTANCE"))
            binding.editTravelTime.setText(intent.getStringExtra("TRAVELTIME"))
        }

        binding.btnSimpan.setOnClickListener {
            simpanData()
        }
    }

    private fun simpanData() {

        val nama = binding.editNama.text.toString().trim()
        val pesan = binding.editPesan.text.toString().trim()
        val avatarUrl = binding.editAvatarUrl.text.toString().trim()
        val kategori = binding.editKategori.text.toString().trim()
        val distance = binding.editDistance.text.toString().trim()
        val travelTime = binding.editTravelTime.text.toString().trim()

        if (nama.isEmpty() || pesan.isEmpty() || kategori.isEmpty()) {
            Toast.makeText(
                this,
                "Nama, pesan, dan kategori wajib diisi",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val database = KonsultasiDatabase.getDatabase(this)
        val repository = KonsultasiRepository(database.konsultasiDao())

        lifecycleScope.launch {

            val data = KonsultasiEntity(
                id = if (idKonsultasi == -1) 0 else idKonsultasi,
                nama = nama,
                pesan = pesan,
                avatarUrl = avatarUrl,
                kategori = kategori,
                distance = distance,
                travelTime = travelTime
            )

            if (idKonsultasi == -1) {
                repository.insertKonsultasi(data)

                Toast.makeText(
                    this@KonsultasiFormActivity,
                    "Data berhasil disimpan",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                repository.updateKonsultasi(data)

                Toast.makeText(
                    this@KonsultasiFormActivity,
                    "Data berhasil diperbarui",
                    Toast.LENGTH_SHORT
                ).show()
            }

            finish()
        }
    }
}