package com.example.androidlessons

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.androidlessons.databinding.ActivityMessageBinding

class MessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessageBinding
    private lateinit var phoneNumber: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        phoneNumber = intent.getStringExtra("PHONE_NUMBER") ?: ""
        binding.phoneNumberTV.text = "Номер телефона: $phoneNumber"
        backgroundAnimation()

        binding.sendButton.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.SEND_SMS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionSendSms.launch(Manifest.permission.SEND_SMS)
            } else {
                sendMessage()
            }
        }
    }

    private fun backgroundAnimation() {
        val animation: AnimationDrawable = binding.messageLayout.background as AnimationDrawable
        animation.apply {
            setEnterFadeDuration(500)
            setExitFadeDuration(1500)
            start()
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun sendMessage() {
        val message = binding.messageET.text.toString()
        if (message.isNotEmpty()) {
            try {
                val smsManager: SmsManager = if (Build.VERSION.SDK_INT >= 23) {
                    this.getSystemService(SmsManager::class.java)
                } else {
                    SmsManager.getDefault()
                }
                smsManager.sendTextMessage(phoneNumber, null, message, null, null)
                Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(
                    applicationContext,
                    "Please enter all the data.." + e.message.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(applicationContext, "Введите сообщение", Toast.LENGTH_LONG).show()
        }
    }

    private val permissionSendSms = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            sendMessage()
        } else {
            Toast.makeText(this, "Разрешение на отправку SMS не предоставлено", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.exitMenu -> finishAffinity()

            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}

