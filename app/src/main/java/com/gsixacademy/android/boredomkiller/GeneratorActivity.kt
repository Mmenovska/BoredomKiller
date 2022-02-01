package com.gsixacademy.android.boredomkiller

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_generator.*

class GeneratorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generator)

        btnBored.setOnClickListener {
            startActivity(Intent(applicationContext, IdeasActivity::class.java))
            finish()
        }


    }
}