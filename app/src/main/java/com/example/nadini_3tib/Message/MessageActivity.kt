package com.example.nadini_3tib.Message

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.nadini_3tib.R
import com.example.nadini_3tib.data.database.KonsultasiDatabase
import com.example.nadini_3tib.data.entity.KonsultasiEntity
import com.example.nadini_3tib.data.repository.KonsultasiRepository
import com.example.nadini_3tib.databinding.ActivityMessageBinding
import kotlinx.coroutines.launch

class MessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        window.statusBarColor = Color.parseColor("#FAF8E3")
        WindowCompat.getInsetsController(
            window,
            window.decorView
        ).isAppearanceLightStatusBars = true

        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Konsultasi"
            subtitle = "Layanan kesehatan mental"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val nama = intent.getStringExtra("SENDER")
            ?: "Tenaga Profesional"

        val pesan = intent.getStringExtra("PESAN")
            ?: "Halo, apa kabar hari ini? Ada yang ingin kamu ceritakan?"

        tambahBubble(pesan)

        simpanPesanDatabase(nama, pesan)

        // Tombol Kirim
        binding.btnKirimChat.setOnClickListener {
            val pesanBaru = binding.editPesanChat.text.toString().trim()

            if (pesanBaru.isNotEmpty()) {
                tambahBubblePengguna(pesanBaru)
                binding.editPesanChat.text.clear()
            }
        }
    }

    // Bubble pesan dari tenaga profesional
    private fun tambahBubble(pesan: String) {

        val bubble = TextView(this).apply {
            text = pesan
            textSize = 15f
            setTextColor(Color.BLACK)
            setPadding(16, 12, 16, 12)

            background = GradientDrawable().apply {
                setColor(Color.WHITE)
                cornerRadius = 20f
            }

            maxWidth = (resources.displayMetrics.widthPixels * 0.75).toInt()
        }

        val baris = LinearLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 12
            }

            gravity = Gravity.START
            addView(bubble)
        }

        binding.chatContainer.addView(baris)
    }

    // Bubble pesan pengguna
    private fun tambahBubblePengguna(pesan: String) {

        val bubble = TextView(this).apply {
            text = pesan
            textSize = 15f
            setTextColor(Color.BLACK)
            setPadding(16, 12, 16, 12)

            background = GradientDrawable().apply {
                setColor(Color.parseColor("#FFF2C6"))
                cornerRadius = 20f
            }

            maxWidth = (resources.displayMetrics.widthPixels * 0.75).toInt()
        }

        val baris = LinearLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 12
            }

            gravity = Gravity.END
            addView(bubble)
        }

        binding.chatContainer.addView(baris)
    }

    // Menyimpan pesan awal ke database
    private fun simpanPesanDatabase(nama: String, pesan: String) {

        val database = Room.databaseBuilder(
            applicationContext,
            KonsultasiDatabase::class.java,
            "konsultasi_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        val repository = KonsultasiRepository(database.konsultasiDao())

        lifecycleScope.launch {
            repository.insertKonsultasi(
                KonsultasiEntity(
                    nama = nama,
                    pesan = pesan,
                    avatarUrl = "",
                    kategori = "Konsultasi",
                    distance = "-",
                    travelTime = "-"
                )
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}