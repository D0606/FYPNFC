package com.example.fypnfcprototype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_read.*

class ReadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
        buttonReadToHome.setOnClickListener{ goHome() }
        buttonReadAgain.setOnClickListener{ readTag() }
    }

    private fun goHome(){
        finish()
    }

    private fun readTag(){

    }

}