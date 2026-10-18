package com.example.nadini_3tib.pertemuan5

import android.graphics.Color
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nadini_3tib.R
import com.example.nadini_3tib.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        // Mengatur toolbar
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Jelajahi Artikel"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // Warna tulisan dan ikon toolbar
        binding.toolbar.setTitleTextColor(Color.BLACK)
        binding.toolbar.navigationIcon?.setTint(Color.BLACK)

        // Tombol kembali
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // Menampilkan artikel di WebView
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true

        val url = intent.getStringExtra("urlArtikel")
            ?: "https://ayosehat.kemkes.go.id/cara-menjaga-kesehatan-mental"

        binding.webView.loadUrl(url)
    }
}