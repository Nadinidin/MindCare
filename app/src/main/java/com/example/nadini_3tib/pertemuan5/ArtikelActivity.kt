package com.example.nadini_3tib.pertemuan5

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nadini_3tib.R
import com.example.nadini_3tib.databinding.ActivityArtikelBinding
import android.graphics.Color
import androidx.core.view.WindowCompat

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
                systemBars.left,
                systemBars.top,
                systemBars.right,
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