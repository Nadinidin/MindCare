package com.example.nadini_3tib

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nadini_3tib.databinding.ActivityMainBinding
import com.example.nadini_3tib.pertemuan5.ArtikelActivity
import com.example.nadini_3tib.pertemuan5.LimaActivity
import com.example.nadini_3tib.pertemuan6.SixthActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
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

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "MindCare"
        }

        val sharedPref = getSharedPreferences(
            "user_pref",
            MODE_PRIVATE
        )

        val user = sharedPref.getString(
            "username",
            "Pengguna"
        ) ?: "Pengguna"

        binding.txtUsername.text = user
        binding.txtPassword.text = "••••••"

        binding.txtSubtitle.text =
            "Halo, $user! Yuk, jaga kesehatan mentalmu hari ini."

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Psikiater adalah dokter spesialis kesehatan jiwa yang dapat memberikan obat jika diperlukan.",
                Snackbar.LENGTH_LONG
            ).show()
        }

        binding.btnPsikolog.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Psikolog membantu melalui asesmen psikologis dan konseling. Psikolog tidak meresepkan obat.",
                Snackbar.LENGTH_LONG
            ).show()
        }

        binding.btnArtikel.setOnClickListener {
            startActivity(
                Intent(this, ArtikelActivity::class.java)
            )
        }

        binding.btnToLima.setOnClickListener {
            startActivity(
                Intent(this, LimaActivity::class.java)
            )
        }

        binding.btnFragment.setOnClickListener {
            startActivity(
                Intent(this, SixthActivity::class.java)
            )
        }

        binding.btnKembali.setOnClickListener {

            sharedPref.edit()
                .putBoolean("isLogin", false)
                .apply()

            val intent = Intent(
                this,
                LoginActivity::class.java
            )

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }

        binding.btnLogout.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("Keluar dari MindCare")
                .setMessage("Apakah kamu yakin ingin keluar dari akun?")
                .setPositiveButton("Ya") { dialog, _ ->

                    sharedPref.edit()
                        .clear()
                        .apply()

                    dialog.dismiss()

                    val intent = Intent(
                        this,
                        LoginActivity::class.java
                    )

                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}
