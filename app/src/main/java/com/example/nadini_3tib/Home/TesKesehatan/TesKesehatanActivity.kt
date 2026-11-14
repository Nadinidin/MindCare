package com.example.nadini_3tib.Home.TesKesehatan

import android.content.Intent
import android.os.Bundle
import com.example.nadini_3tib.R
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.nadini_3tib.databinding.ActivityTesKesehatanBinding

class TesKesehatanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTesKesehatanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        binding = ActivityTesKesehatanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.btnMulaiTes.setOnClickListener {
            val intent = Intent(this, PertanyaanTesActivity::class.java)
            startActivity(intent)
        }
    }
}
