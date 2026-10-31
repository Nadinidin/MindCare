package com.example.nadini_3tib.Home.pertemuan5

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.graphics.Color
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nadini_3tib.R
import com.example.nadini_3tib.databinding.ActivityArtikelBinding

class ArtikelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtikelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        window.statusBarColor = Color.parseColor("#FAF8E3")
        WindowCompat.getInsetsController(
            window,
            window.decorView
        ).isAppearanceLightStatusBars = true

        binding = ActivityArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                0,
                systemBars.top,
                0,
                systemBars.bottom
            )
            insets
        }

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Artikel Kesehatan Mental"
            subtitle = "Informasi seputar kesehatan mental"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // Tombol untuk membuka artikel
        binding.btnArtikel1.setOnClickListener {
            bukaArtikel("https://ayosehat.kemkes.go.id/cara-menjaga-kesehatan-mental")
        }

        binding.btnArtikel2.setOnClickListener {
            bukaArtikel("https://www.who.int/news-room/fact-sheets/detail/mental-health-strengthening-our-response")
        }

        binding.btnArtikel3.setOnClickListener {
            bukaArtikel("https://www.who.int/news-room/questions-and-answers/item/stress")
        }

        binding.btnArtikel4.setOnClickListener {
            bukaArtikel("https://www.who.int/news-room/fact-sheets/detail/anxiety-disorders")
        }

        binding.btnArtikel5.setOnClickListener {
            bukaArtikel("https://www.who.int/news-room/fact-sheets/detail/adolescent-mental-health")
        }

        // Chip Group untuk filter artikel
        binding.chipGroupFilter.setOnCheckedStateChangeListener { _, checkedIds ->

            when (checkedIds.firstOrNull()) {

                R.id.chipSemua -> {
                    tampilkanSemuaArtikel()
                }

                R.id.chipPerawatan -> {
                    binding.cardArtikel1.visibility = android.view.View.VISIBLE
                    binding.cardArtikel2.visibility = android.view.View.GONE
                    binding.cardArtikel3.visibility = android.view.View.GONE
                    binding.cardArtikel4.visibility = android.view.View.GONE
                    binding.cardArtikel5.visibility = android.view.View.GONE
                }

                R.id.chipMental -> {
                    binding.cardArtikel1.visibility = android.view.View.GONE
                    binding.cardArtikel2.visibility = android.view.View.VISIBLE
                    binding.cardArtikel3.visibility = android.view.View.GONE
                    binding.cardArtikel4.visibility = android.view.View.GONE
                    binding.cardArtikel5.visibility = android.view.View.GONE
                }

                R.id.chipStres -> {
                    binding.cardArtikel1.visibility = android.view.View.GONE
                    binding.cardArtikel2.visibility = android.view.View.GONE
                    binding.cardArtikel3.visibility = android.view.View.VISIBLE
                    binding.cardArtikel4.visibility = android.view.View.GONE
                    binding.cardArtikel5.visibility = android.view.View.GONE
                }

                R.id.chipKecemasan -> {
                    binding.cardArtikel1.visibility = android.view.View.GONE
                    binding.cardArtikel2.visibility = android.view.View.GONE
                    binding.cardArtikel3.visibility = android.view.View.GONE
                    binding.cardArtikel4.visibility = android.view.View.VISIBLE
                    binding.cardArtikel5.visibility = android.view.View.GONE
                }

                R.id.chipRemaja -> {
                    binding.cardArtikel1.visibility = android.view.View.GONE
                    binding.cardArtikel2.visibility = android.view.View.GONE
                    binding.cardArtikel3.visibility = android.view.View.GONE
                    binding.cardArtikel4.visibility = android.view.View.GONE
                    binding.cardArtikel5.visibility = android.view.View.VISIBLE
                }
            }
        }
    }

    // Menampilkan seluruh artikel
    private fun tampilkanSemuaArtikel() {
        binding.cardArtikel1.visibility = android.view.View.VISIBLE
        binding.cardArtikel2.visibility = android.view.View.VISIBLE
        binding.cardArtikel3.visibility = android.view.View.VISIBLE
        binding.cardArtikel4.visibility = android.view.View.VISIBLE
        binding.cardArtikel5.visibility = android.view.View.VISIBLE
    }

    private fun bukaArtikel(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("urlArtikel", url)
        startActivity(intent)
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
