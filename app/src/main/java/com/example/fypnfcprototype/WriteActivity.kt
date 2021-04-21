package com.example.fypnfcprototype

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_write.*

class WriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        buttonWriteToHome.setOnClickListener{ goHome() }
        buttonWriteNow.setOnClickListener{ writeTag() }
    }

    private fun goHome(){
        val homeIntent = Intent(this, MainActivity::class.java)
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(homeIntent)
    }

    private fun writeTag(){
        val writeTagText = Intent(this, WriteResultActivity::class.java)
        val tagText = textTagInput.text.toString()
        if (textTagInput.text.toString().isNotEmpty()) {
            Log.d("LINE READ", tagText)
            writeTagText.putExtra("EXTRA_tagText", tagText)
            startActivity(writeTagText)
        }
        else {
            //TODO: Change toasts to dialogs
            Toast.makeText(this, "Error: No text.", Toast.LENGTH_SHORT).show()
        }

    }

}