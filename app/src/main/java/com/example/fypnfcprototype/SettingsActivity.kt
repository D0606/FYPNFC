package com.example.fypnfcprototype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        buttonSettingsToHome.setOnClickListener{ goHome() }
        buttonSettingsCycle.setOnClickListener{ cycleColours() }
    }

    private fun goHome(){
        finish()
    }

    private fun cycleColours(){}

}