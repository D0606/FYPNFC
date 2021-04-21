package com.example.fypnfcprototype

import android.content.Intent
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_read_result.*

private var NFCAdapt: NfcAdapter? = null

class ReadResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_result)
        NFCAdapt = NfcAdapter.getDefaultAdapter(this)
        buttonReadToHome.setOnClickListener{ goHome() }
        buttonReadAgain.setOnClickListener{ readTag() }
        //TODO: Change this from toast to actual text screen output
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

    private fun readTag(){
        recreate()
    }

}