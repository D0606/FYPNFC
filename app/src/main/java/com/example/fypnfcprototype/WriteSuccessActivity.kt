package com.example.fypnfcprototype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_write_success.*

class WriteSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_success)
        buttonWriteSuccessToHome.setOnClickListener{ goHome() }
        buttonWriteSuccessNew.setOnClickListener{ goWrite() }
        buttonWriteAgainSuccess.setOnClickListener { writeAgain() }
    }

    private fun goHome(){
        //TODO(Write this with a proper return to the home screen, clearing the stack)
        finish()
    }

    private fun goWrite(){
        //TODO(Write this with a proper return to the home screen, clearing the stack)
        finish()
    }

    private fun writeAgain(){

    }
}