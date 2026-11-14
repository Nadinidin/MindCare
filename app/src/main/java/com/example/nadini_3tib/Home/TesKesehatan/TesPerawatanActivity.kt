package com.example.nadini_3tib.Home.TesKesehatan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.nadini_3tib.R
import com.example.nadini_3tib.databinding.ActivityTesPerawatanBinding

class TesPerawatanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTesPerawatanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        binding = ActivityTesPerawatanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.btnMulaiTesPerawatan.setOnClickListener {
            val intent = Intent(this, TesLainnyaActivity::class.java)
            intent.putExtra("jenis_tes", "perawatan")
            startActivity(intent)
        }
    }
}
