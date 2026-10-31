package com.example.nadini_3tib

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.nadini_3tib.databinding.ActivityTutorialBinding
import android.view.View

class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding

    private val tutorialList = listOf(
        TutorialModel(
            "🌿",
            "Welcome to MindCare",
            "Selamat datang di MindCare! Ruang aman untuk mengenal dan menjaga kesehatan mentalmu."
        ),
        TutorialModel(
            "📖",
            "Understand Your Mind",
            "Temukan informasi kesehatan mental dan kenali pentingnya memahami pikiran serta perasaanmu."
        ),
        TutorialModel(
            "🌸",
            "Take Care of Yourself",
            "Luangkan waktu untuk dirimu, kenali emosimu, dan mulai perjalanan menuju kehidupan yang lebih seimbang."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTutorial()
        setupButton()
    }

    private fun setupTutorial() {
        binding.viewPager.adapter = TutorialAdapter(tutorialList)

        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    updatePage(position)
                }
            }
        )

        updatePage(0)
    }

    private fun setupButton() {
        binding.btnNext.setOnClickListener {
            finishTutorial()
        }

        binding.btnSkip.setOnClickListener {
            finishTutorial()
        }
    }

    private fun updatePage(position: Int) {

        binding.txtIndicator.text =
            tutorialList.indices.joinToString("  ") {
                if (it == position) "●" else "○"
            }

        if (position == tutorialList.size - 1) {
            binding.btnNext.visibility = View.VISIBLE
        } else {
            binding.btnNext.visibility = View.GONE
        }
    }

    private fun finishTutorial() {
        val sharedPref = getSharedPreferences(
            "user_pref",
            MODE_PRIVATE
        )

        sharedPref.edit()
            .putBoolean("tutorial_seen", true)
            .apply()

        startActivity(
            Intent(this, LoginActivity::class.java)
        )

        finish()
    }
}
