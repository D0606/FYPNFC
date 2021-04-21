package com.example.fypnfcprototype

import android.content.Intent
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_write_result.*

private var NFCAdapt: NfcAdapter? = null

class WriteResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_result)
        NFCAdapt = NfcAdapter.getDefaultAdapter(this)
        buttonWriteSuccessToHome.setOnClickListener{ goHome() }
        buttonWriteSuccessNew.setOnClickListener{ goWrite() }
        buttonWriteAgainSuccess.setOnClickListener { writeAgain() }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val tagText = getTagText()
        val messageWrittenSuccessfully = NFCServices.newNFCRecord(tagText, intent)
        if (messageWrittenSuccessfully){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        NFCAdapt?.let {NFCServices.foregroundNFCOn(it, this, javaClass)
        }
    }

    override fun onPause() {
        super.onPause()
        NFCAdapt?.let {NFCServices.foregroundNFCOff(it, this)
        }
    }

    private fun goHome(){
        val homeIntent = Intent(this, MainActivity::class.java)
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(homeIntent)
    }

    private fun goWrite(){
        val writeIntent = Intent(this, WriteActivity::class.java)
        startActivity(writeIntent)
    }

    private fun writeAgain(){
        recreate()
    }

    private fun getTagText() :String {
        return intent.getStringExtra("EXTRA_tagText").toString()
    }

}