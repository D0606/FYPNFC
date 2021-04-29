package com.example.fypnfcprototype

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_read_result.*

private var NFCAdapt: NfcAdapter? = null

class ReadResultActivity : AppCompatActivity() {
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

        setContentView(R.layout.activity_read_result)
        supportActionBar?.hide()
        buttonReadToHome.setOnClickListener{ goHome() }
        Log.d("ACTION?", intent?.action.toString())
        NFCAdapt = NfcAdapter.getDefaultAdapter(this)
        //TODO: Change this from toast to actual text screen output
        val message = NFCServices.getNFCRecord(this.intent)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        textReadTagTitle.text = message
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("ACTION?", intent?.action.toString())
        NFCAdapt = NfcAdapter.getDefaultAdapter(this)
        val message = NFCServices.getNFCRecord(this.intent)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        textReadTagTitle.text = message
    }

    override fun onResume() {
        super.onResume()
        NFCAdapt?.let {
            NFCServices.foregroundNFCOn(it, this, javaClass)
        }
    }

    override fun onPause() {
        super.onPause()
        NFCAdapt?.let {
            NFCServices.foregroundNFCOff(it, this)
        }
    }

    private fun goHome(){
        val homeIntent = Intent(this, MainActivity::class.java)
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(homeIntent)
    }

}