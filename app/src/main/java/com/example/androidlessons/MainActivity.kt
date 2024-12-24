package com.example.androidlessons

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.androidlessons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentVideo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val videoList = mutableListOf(
            Uri.parse("android.resource://$packageName/${R.raw.video1}"),
            Uri.parse("android.resource://$packageName/${R.raw.video2}"),
            Uri.parse("android.resource://$packageName/${R.raw.video3}"),
        )

        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)

        playVideo(videoList[currentVideo])

        binding.previosFAB.setOnClickListener {
            currentVideo = (currentVideo - 1 + videoList.size) % videoList.size
            playVideo(videoList[currentVideo])
        }

        binding.pauseFAB.setOnClickListener {
            if (binding.videoView.isPlaying) {
                binding.videoView.pause()
            } else {
                binding.videoView.start()
            }
        }

        binding.nextFAB.setOnClickListener {
            currentVideo = (currentVideo + 1) % videoList.size
            playVideo(videoList[currentVideo])
        }
    }

    private fun playVideo(uri: Uri) {
        binding.videoView.setVideoURI(uri)
        binding.videoView.requestFocus()
        binding.videoView.start()
    }
}
