package com.example.fypnfcprototype

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_read_result.*
import kotlinx.android.synthetic.main.activity_write_result.*


class ReadResultActivity : AppCompatActivity() {

    private var NFCAdapt : NfcAdapter? = null
    private var NFCIntent: PendingIntent? = null

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

        val checkAdapter = NfcAdapter.getDefaultAdapter(this).isEnabled
        if (!checkAdapter) {
            Toast.makeText(this, "NO NFC. PLEASE ENABLE", Toast.LENGTH_SHORT).show()
            textReadTagTitle.text = "PLEASE ENABLE NFC"
        }
        buttonReadToHome.setOnClickListener{ goHome() }

        NFCAdapt = NfcAdapter.getDefaultAdapter(this)
        NFCIntent = PendingIntent.getActivity(this, 0,
                Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0)
        Log.d("ACTION?", intent?.action.toString())
        if (intent != null) {
            readTag(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("ACTION?", intent?.action.toString())
        if (intent != null) {
            readTag(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val checkAdapter = NfcAdapter.getDefaultAdapter(this).isEnabled
        if (!checkAdapter) {
            Toast.makeText(this, "NO NFC. PLEASE ENABLE", Toast.LENGTH_SHORT).show()
            textReadTagTitle.text = "PLEASE ENABLE NFC"
        }
        NFCAdapt?.enableForegroundDispatch(this, NFCIntent, null, null)
    }

    override fun onPause() {
        super.onPause()
        NFCAdapt?.disableForegroundDispatch(this)
    }

    private fun goHome(){
        val homeIntent = Intent(this, MainActivity::class.java)
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(homeIntent)
    }

    private fun readTag(processIntent: Intent){
        val message = NFCServices.getNFCRecord(processIntent)
        textReadTagTitle.text = message
    }
}