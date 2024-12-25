package com.example.androidlessons

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentProviderOperation
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.provider.ContactsContract.CommonDataKinds.StructuredName
import android.provider.ContactsContract.RawContacts
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlessons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var contactModelList: MutableList<ContactModel> = mutableListOf()
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        backgroundAnimation()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionContact.launch(Manifest.permission.READ_CONTACTS)
        } else {
            getContact()
        }
    }

    private fun backgroundAnimation() {
        val animation: AnimationDrawable = binding.mainLayout.background as AnimationDrawable
        animation.apply {
            setEnterFadeDuration(500)
            setExitFadeDuration(1500)
            start()
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        binding.addContactButton.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionWriteContact.launch(Manifest.permission.WRITE_CONTACTS)
            } else {
                addContact()
                getContact()
            }
        }
    }


    private fun addContact() {
        val newContactName = binding.nameInput.text.toString()
        val newContactPhone = binding.phoneInput.text.toString()

        if (newContactName.isEmpty() || newContactPhone.isEmpty()) {
            Toast.makeText(this, "Имя и номер телефона не могут быть пустыми", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val listCPO = ArrayList<ContentProviderOperation>()

        listCPO.add(
            ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
                .withValue(RawContacts.ACCOUNT_TYPE, null)
                .withValue(RawContacts.ACCOUNT_NAME, null)
                .build()
        )
        listCPO.add(
            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
                .withValue(StructuredName.DISPLAY_NAME, newContactName)
                .build()
        )
        listCPO.add(
            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                .withValue(Phone.NUMBER, newContactPhone)
                .withValue(Phone.TYPE, Phone.TYPE_MOBILE)
                .build()
        )

        try {
            contentResolver.applyBatch(ContactsContract.AUTHORITY, listCPO)
            Toast.makeText(this, "$newContactName добавлен в список контактов", Toast.LENGTH_SHORT)
                .show()
        } catch (e: Exception) {
            Log.e("Exception", e.message ?: "Ошибка при добавлении контакта")
        }
    }

    private val permissionWriteContact = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(this, "Получен доступ к записи контактов", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "В разрешении отказано...", Toast.LENGTH_SHORT).show()
        }
    }


    @SuppressLint("Range")
    private fun getContact() {
        contactModelList.clear()
        val phoneCursor = contentResolver.query(
            Phone.CONTENT_URI,
            null,
            null,
            null,
            Phone.DISPLAY_NAME + " ASC"
        )

        while (phoneCursor!!.moveToNext()) {
            val name =
                phoneCursor.getString(phoneCursor.getColumnIndex(Phone.DISPLAY_NAME))
            val phoneNumber =
                phoneCursor.getString(phoneCursor.getColumnIndex(Phone.NUMBER))
            val contactModel = ContactModel(name, phoneNumber)
            contactModelList.add(contactModel)
        }
        phoneCursor.close()

        customAdapter = CustomAdapter(this, contactModelList,
            onCallClick = { number -> callTheNumber(number) },
            onMessageClick = { number -> openMessageActivity(number) }
        )
        binding.recyclerView.adapter = customAdapter
    }

    private fun callTheNumber(number: String) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionOfCall.launch(Manifest.permission.CALL_PHONE)
        } else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
        }
    }

    private fun openMessageActivity(phoneNumber: String) {
        val intent = Intent(this, MessageActivity::class.java)
        intent.putExtra("PHONE_NUMBER", phoneNumber)
        startActivity(intent)
    }

    private val permissionContact = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(this, "Получен доступ к контактам", Toast.LENGTH_SHORT).show()
            getContact()
        } else {
            Toast.makeText(this, "В разрешении отказано...", Toast.LENGTH_SHORT).show()
        }
    }

    private val permissionOfCall = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(this, "Получен доступ к звонкам", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "В разрешении отказано...", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenu -> finishAffinity()
            R.id.searchMenu -> {
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("CONTACT_LIST", ArrayList(contactModelList))
                startActivity(intent)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }


}

