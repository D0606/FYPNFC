package com.example.fypnfcprototype

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_write_result.*


class WriteResultActivity : AppCompatActivity() {

    private var NFCAdapt : NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the theme key*/
        sharedPreferences = getSharedPreferences(
                "colourChoice",
                Context.MODE_PRIVATE
        )
        /*Check the theme and change*/
        when (sharedPreferences.getString(colourKey, "yellowOnBlue")) {
            "yellowOnBlue" -> this.setTheme(R.style.Theme_yellowOnBlue)
            "blueOnYellow" -> this.setTheme(R.style.Theme_blueOnYellow)
            "yellowOnBlack" -> this.setTheme(R.style.Theme_yellowOnBlack)
            "blackOnYellow" -> this.setTheme(R.style.Theme_blackOnYellow)
            "greenOnBlack" -> this.setTheme(R.style.Theme_greenOnBlack)
            "whiteOnBlack" -> this.setTheme(R.style.Theme_whiteOnBlack)
        }

        setContentView(R.layout.activity_write_result)
        supportActionBar?.hide()
        NFCAdapt = NfcAdapter.getDefaultAdapter(this)

        val checkAdapter = NfcAdapter.getDefaultAdapter(this).isEnabled
        if (!checkAdapter) {
            Toast.makeText(this, "NO NFC. PLEASE ENABLE", Toast.LENGTH_SHORT).show()
            textWriteSuccessTitle.text = "PLEASE ENABLE NFC"
        }
        buttonWriteSuccessToHome.setOnClickListener{ goHome() }
        buttonWriteSuccessNew.setOnClickListener{ goWrite() }

        val text = sharedPreferences.getString("tagText", "NULL").toString()
        Log.d("ONCREATE TAG TEXT", text)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            val tagText = sharedPreferences.getString("tagText", "NULL").toString()
            Log.d("ONINTENT TAG TEXT", tagText)
            val messageWrittenSuccessfully = NFCServices.newNFCRecord(tagText, intent)
            if (messageWrittenSuccessfully) {
                Toast.makeText(this, "TAG WRITE SUCCESS", Toast.LENGTH_SHORT).show()
                textWriteSuccessTitle.text = tagText + " - SUCCESS"
            } else {
                Toast.makeText(this, "TAG WRITE ERROR", Toast.LENGTH_SHORT).show()
                textWriteSuccessTitle.text = "FAILED TO WRITE"
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val checkAdapter = NfcAdapter.getDefaultAdapter(this).isEnabled
        if (!checkAdapter) {
            Toast.makeText(this, "NO NFC. PLEASE ENABLE", Toast.LENGTH_SHORT).show()
            textWriteSuccessTitle.text = "PLEASE ENABLE NFC"
        }
        NFCAdapt?.let {NFCServices.foregroundNFCOn(it, this, javaClass)
        }
        Log.i("onResume Called", "TRUE")
    }

    override fun onPause() {
        super.onPause()
        NFCAdapt?.let {NFCServices.foregroundNFCOff(it, this)
        }
        Log.i("onPause Called", "TRUE")
    }

    private fun goHome(){
        val homeIntent = Intent(this, MainActivity::class.java)
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(homeIntent)
    }

    override fun onStop() {
        super.onStop()
        Log.i("onStop Called", "TRUE")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("onDestroy Called", "TRUE")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("onRestart Called", "TRUE")
    }

    private fun goWrite(){
        val writeIntent = Intent(this, WriteActivity::class.java)
        startActivity(writeIntent)
    }
}