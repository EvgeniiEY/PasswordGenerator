package com.example.passwordgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PswDbActivity : AppCompatActivity() {

    var btnBackToGenerate: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_psw_db)
        initViews()
        onClickBackToGenerate()
    }

    fun onClickBackToGenerate() {

        btnBackToGenerate?.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun initViews() {
        btnBackToGenerate = findViewById(R.id.buttonBackToGenerate)
    }

}