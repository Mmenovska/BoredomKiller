package com.gsixacademy.android.boredomkiller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        GlobalScope.launch {
            Thread.sleep(1500)
            startActivity(Intent(applicationContext, GeneratorActivity::class.java))
            finish()
        }
    }
}