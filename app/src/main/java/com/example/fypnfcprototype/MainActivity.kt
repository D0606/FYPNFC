package com.example.fypnfcprototype

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonHomeToSettings.setOnClickListener{ startSettingsScreen() }
        buttonHomeReadTag.setOnClickListener{ startReadScreen() }
        buttonHomeWriteTag.setOnClickListener { startWriteScreen() }
    }

    private fun startSettingsScreen(){
        val settingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(settingsIntent)
    }

    private fun startReadScreen(){
        val readIntent = Intent(this, ReadActivity::class.java)
        startActivity(readIntent)
    }

    private fun startWriteScreen(){
        val writeIntent = Intent(this, WriteActivity::class.java)
        startActivity(writeIntent)
    }
}