package com.example.fypnfcprototype

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_write.*
import java.util.*

class WriteActivity : AppCompatActivity() {
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

        //Clear tag value
        sharedPreferences.edit().putString("tagText", "null").apply()

        setContentView(R.layout.activity_write)
        supportActionBar?.hide()
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
        val tagText = textTagInput.text.toString().toUpperCase(Locale.getDefault())
        if (textTagInput.text.toString().isNotEmpty()) {
            sharedPreferences.edit().putString("tagText", tagText).apply()
            Log.d("TEXT WRITTEN", tagText)
            startActivity(writeTagText)
        }
        else {
            //TODO: Change toasts to dialogs or titles
            textWriteTagScreen.text = "PLEASE ENTER LABEL TEXT"
            Toast.makeText(this, "NO LABEL TEXT ENTERED.", Toast.LENGTH_SHORT).show()
        }

    }

}