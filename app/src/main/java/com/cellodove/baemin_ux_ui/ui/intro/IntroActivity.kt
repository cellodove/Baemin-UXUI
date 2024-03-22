package com.cellodove.baemin_ux_ui.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.cellodove.baemin_ux_ui.MainActivity
import com.cellodove.baemin_ux_ui.databinding.ActivityIntroBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notificationsViewModel =
            ViewModelProvider(this)[IntroViewModel::class.java]

        notificationsViewModel.image.observe(this){
            Glide.with(this)
                .load(it)
                .into(binding.titleImage)
        }

        lifecycleScope.launch {
            delay(3000)
            finish()
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
        }
    }

}