package com.example.fypnfcprototype

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import java.io.IOException


object NFCServices {

    fun getNFCRecord(intent: Intent?): String {
        intent?.let {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
                val nDefData = getNDefData(intent)
                nDefData[0].records?.let {
                    it.forEach {
                        it?.payload.let {
                            it?.let {
                                return String(it)
                            }
                        }
                    }
                }
            } else {
                return "Touch tag for label 1."
            }
        }
        return "Touch tag for label 2."
    }

    fun newNFCRecord(payload: String, intent: Intent?): Boolean {
        val prefix = "danieltruman.com:fypnfc"
        val record = NdefRecord(NdefRecord.TNF_EXTERNAL_TYPE, prefix.toByteArray(), ByteArray(0), payload.toByteArray())
        val message = NdefMessage(arrayOf(record))
        intent?.let {
            val tag = it.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
            return writeTag(message, tag)
        }
        return false
    }

    private fun getNDefData(intent: Intent): Array<NdefMessage> {
        val data = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
        data?.let {
            return data.map {
                it as NdefMessage
            }.toTypedArray()
        }
        //Handle unknown type of tag.
        val empty = byteArrayOf()
        val record = NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty)
        val message = NdefMessage(arrayOf(record))
        return arrayOf(message)
    }

    private fun writeTag(nfcMessage: NdefMessage, tag: Tag?): Boolean {

        try {
            val nDefTag = Ndef.get(tag)

            nDefTag?.let {
                it.connect()
                if (it.maxSize < nfcMessage.toByteArray().size) {
                    //If too big to fit on tag
                    return false
                }
                if (it.isWritable) {
                    it.writeNdefMessage(nfcMessage)
                    it.close()
                    //If can write then write
                    return true
                } else {
                    //If tag is read only
                    return false
                }
            }

            val nDefFormatableTag = NdefFormatable.get(tag)

            nDefFormatableTag?.let {
                try {
                    it.connect()
                    it.format(nfcMessage)
                    it.close()
                    //If can write then write
                    return true
                } catch (e: IOException) {
                    //Tag formatting failed
                    return false
                }
            }
            //NDEF not supported
            return false

        } catch (e: Exception) {
            //Write failed
        }
        return false
    }

    fun foregroundNFCOff(nfcAdapter: NfcAdapter, activity: Activity) {
        nfcAdapter.disableForegroundDispatch(activity)
    }

    fun <T> foregroundNFCOn(nfcAdapter: NfcAdapter, activity: Activity, classType: Class<T>) {
        val pendingIntent = PendingIntent.getActivity(activity, 0,
                Intent(activity, classType).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0)
        val nfcIntentFilter = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
        val filterArr = arrayOf(nfcIntentFilter)
        val techArr = arrayOf(arrayOf(Ndef::class.java.name), arrayOf(NdefFormatable::class.java.name))

        nfcAdapter.enableForegroundDispatch(activity, pendingIntent, filterArr, techArr)
    }
}

