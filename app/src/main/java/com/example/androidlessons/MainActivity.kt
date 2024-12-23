package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.res.Resources.Theme
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.androidlessons.models.User
import com.example.androidlessons.utils.RetrofitInstance
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var progressBarr: ProgressBar
    private lateinit var downloadBTN: Button
    private lateinit var imageView: ShapeableImageView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()

        setSupportActionBar(toolbar)
        downloadBTN.setOnClickListener {
            progressBarr.visibility = View.VISIBLE
            loadRandomImage()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishAffinity()
        return true
    }

    private fun initUI() {
        progressBarr = findViewById(R.id.progressBarr)
        downloadBTN = findViewById(R.id.downloadBTN)
        imageView = findViewById(R.id.imageView)
        toolbar = findViewById(R.id.toolbar)

    }

    private fun loadRandomImage() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.getRandomDog()
                withContext(Dispatchers.Main) {
                    Glide.with(this@MainActivity)
                        .load(response[0].url)
                        .listener(object:  RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                progressBarr.visibility = View.GONE
                                Toast.makeText(applicationContext, "Ошибка загрузки изображения",Toast.LENGTH_SHORT).show()
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                progressBarr.visibility = View.GONE
                                return false
                            }

                        })
                        .into(imageView)
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        applicationContext,
                        "HTTP error: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressBarr.visibility = View.GONE
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        applicationContext,
                        "Network error: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressBarr.visibility = View.GONE
                }
            }
        }
    }
}

//    private fun postRequest() {
//        GlobalScope.launch(Dispatchers.IO) {
//            val responce = try {
////                val user = User("new body", null,"new title", 15)
////                RetrofitInstance.api.createPost(user)
//            } catch (e: HttpException) {
//                Toast.makeText(applicationContext, "http error ${e.message}", Toast.LENGTH_SHORT)
//                    .show()
//                return@launch
//            } catch (e: IOException) {
//                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_SHORT)
//                    .show()
//                return@launch
//            }
//            withContext(Dispatchers.Main) {
//                Toast.makeText(applicationContext, responce., Toast.LENGTH_SHORT).show()
//
//                val data = responce.
//
//            }
//        }
//    }

