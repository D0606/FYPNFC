package com.example.fypnfcprototype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_write.*

class WriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        buttonReadToHome.setOnClickListener{ goHome() }
        buttonReadAgain.setOnClickListener{ writeTag() }
    }

    private fun goHome(){
        finish()
    }

    private fun writeTag(){

    }

}