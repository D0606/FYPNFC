package com.example.fypnfcprototype

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

lateinit var sharedPreferences : SharedPreferences
const val colourKey =  "currentTheme"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the theme key*/
        sharedPreferences = getSharedPreferences(
                "colourChoice",
                Context.MODE_PRIVATE
        )
        /*Check the theme now*/
        when (sharedPreferences.getString(colourKey, "yellowOnBlue")) {
            "yellowOnBlue" -> this.setTheme(R.style.Theme_yellowOnBlue)
            "blueOnYellow" -> this.setTheme(R.style.Theme_blueOnYellow)
            "yellowOnBlack" -> this.setTheme(R.style.Theme_yellowOnBlack)
            "blackOnYellow" -> this.setTheme(R.style.Theme_blackOnYellow)
            "greenOnBlack" -> this.setTheme(R.style.Theme_greenOnBlack)
            "whiteOnBlack" -> this.setTheme(R.style.Theme_whiteOnBlack)
        }

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
        val readIntent = Intent(this, ReadResultActivity::class.java)
        startActivity(readIntent)
    }

    private fun startWriteScreen(){
        val writeIntent = Intent(this, WriteActivity::class.java)
        startActivity(writeIntent)
    }
}