package com.example.androidlessons

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.androidlessons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null
    private val songList = listOf(R.raw.cyberworld, R.raw.alchemy, R.raw.vitality)
    private var currentSong = 0
    private lateinit var animator: ValueAnimator
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBackground()
        setupMediaPlayer()
        setupAnimation()
    }

    private fun setupMediaPlayer() {
        binding.playFAB.setOnClickListener { playSong() }
        binding.pauseFAB.setOnClickListener {
            mediaPlayer?.pause()
            stopAnimationDisc()
        }
        binding.stopFAB.setOnClickListener { stopSong() }
        binding.nextFAB.setOnClickListener { nextSong() }
        binding.previousFAB.setOnClickListener { previousSong() }
        binding.seekbarSB.setOnSeekBarChangeListener(createSeekBarChangeListener())
        binding.seekbarVolumeSB.setOnSeekBarChangeListener(createSeekBarVolumeChangeListener())
    }

    private fun setupAnimation() {
        animator = ValueAnimator.ofFloat(0f, 360f)
        animator.duration = 2500
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.addUpdateListener { animation ->
            val rotationValue = animation.animatedValue as Float
            binding.imageViewIV.rotation = rotationValue
        }
    }

    private fun createSeekBarVolumeChangeListener() = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (fromUser) {
                val volume = progress / 100f
                mediaPlayer?.setVolume(volume, volume)
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }

    private fun playSong() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, songList[currentSong])
            initializeSeekBar()
        }
        mediaPlayer?.start()
        playAnimationDisc()
    }

    private fun stopSong() {
        mediaPlayer?.apply {
            stop()
            reset()
            release()
        }
        mediaPlayer = null
        stopAnimationDisc()
    }

    private fun nextSong() {
        currentSong = (currentSong + 1) % songList.size
        resetMediaPlayer()
    }

    private fun previousSong() {
        currentSong = (currentSong - 1 + songList.size) % songList.size
        resetMediaPlayer()
    }

    private fun resetMediaPlayer() {
        mediaPlayer?.apply {
            stop()
            reset()
            release()
        }
        mediaPlayer = MediaPlayer.create(this, songList[currentSong])
        mediaPlayer?.start()
        playAnimationDisc()
    }

    private fun createSeekBarChangeListener() = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (fromUser) mediaPlayer?.seekTo(progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }

    private fun initializeSeekBar() {
        binding.seekbarVolumeSB.max = 100

        binding.seekbarSB.max = mediaPlayer!!.duration
        handler.postDelayed(object : Runnable {
            override fun run() {
                mediaPlayer?.let {
                    binding.seekbarSB.progress = it.currentPosition
                    handler.postDelayed(this, 1000)
                }
            }
        }, 0)
    }

    private fun setBackground() {
        val animation = binding.mainLayout.background as AnimationDrawable
        animation.apply {
            setEnterFadeDuration(500)
            setExitFadeDuration(1500)
            start()
        }
    }

    private fun playAnimationDisc() {
        if (!animator.isRunning){
            animator.start()
        }else if (animator.isPaused){
            animator.start()
        }
    }

    private fun stopAnimationDisc() {
        animator.pause()
    }
}

