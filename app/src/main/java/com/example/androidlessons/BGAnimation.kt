package com.example.androidlessons

import android.graphics.drawable.AnimationDrawable
import android.view.View

object BGAnimation {

     fun backgroundAnimation(view: View) {
        val animation: AnimationDrawable = view.background as AnimationDrawable
        animation.apply {
            setEnterFadeDuration(500)
            setExitFadeDuration(1500)
            start()
        }
    }
}