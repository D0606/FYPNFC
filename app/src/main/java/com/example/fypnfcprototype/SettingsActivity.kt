package com.example.fypnfcprototype

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity : AppCompatActivity() {
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

        setContentView(R.layout.activity_settings)
        buttonSettingsToHome.setOnClickListener{ goHome() }
        buttonSettingsCycle.setOnClickListener{ cycleColours() }
    }

    private fun goHome(){
        val homeIntent = Intent(this, MainActivity::class.java)
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(homeIntent)
    }

    private fun cycleColours(){
        sharedPreferences = getSharedPreferences(
                "colourChoice",
                Context.MODE_PRIVATE
        )
        when (sharedPreferences.getString(colourKey, "yellowOnBlue")){
            "yellowOnBlue" -> {this.setTheme(R.style.Theme_blueOnYellow)
                sharedPreferences.edit().putString(colourKey, "blueOnYellow").apply()
                recreate()}
            "blueOnYellow" -> {this.setTheme(R.style.Theme_yellowOnBlack)
                sharedPreferences.edit().putString(colourKey, "yellowOnBlack").apply()
                recreate()}
            "yellowOnBlack" -> {this.setTheme(R.style.Theme_blackOnYellow)
                sharedPreferences.edit().putString(colourKey, "blackOnYellow").apply()
                recreate()}
            "blackOnYellow" -> {this.setTheme(R.style.Theme_greenOnBlack)
                sharedPreferences.edit().putString(colourKey, "greenOnBlack").apply()
                recreate()}
            "greenOnBlack" -> {this.setTheme(R.style.Theme_whiteOnBlack)
                sharedPreferences.edit().putString(colourKey, "whiteOnBlack").apply()
                recreate()}
            "whiteOnBlack" -> {this.setTheme(R.style.Theme_yellowOnBlue)
                sharedPreferences.edit().putString(colourKey, "yellowOnBlue").apply()
                recreate()}
        }
    }
}