package com.example.androidlessons

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.androidlessons.databinding.ActivityMainBinding
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val permsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(this, "Разрешение получено!", Toast.LENGTH_SHORT).show()
            targetActivity?.let {
                startActivity(Intent(this, it))
            }
        } else {
            Toast.makeText(this, "В разрешении отказано..", Toast.LENGTH_SHORT).show()
        }
    }

    private var targetActivity: Class<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.cameraPermsBTN.setOnClickListener {
            requestPermission(Manifest.permission.CAMERA, CameraActivity::class.java)
        }

        binding.contactsPermsBTN.setOnClickListener {
            requestPermission(Manifest.permission.READ_CONTACTS, ContactsActivity::class.java)
        }
    }

    private fun requestPermission(permission: String, targetActivity: Class<*>) {
        this.targetActivity = targetActivity
        permsLauncher.launch(permission)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishAffinity()
       return false
    }
}
