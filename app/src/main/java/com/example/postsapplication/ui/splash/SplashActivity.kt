package com.example.postsapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.postsapplication.R
import com.example.postsapplication.ui.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        fadeIn()
    }

    private fun fadeIn() {
        val animation = AnimationUtils.loadAnimation(
            this,
            R.anim.fade_in
        )
        animation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
               IntentToMainActivity()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        logo.startAnimation(animation)
    }

    private fun IntentToMainActivity() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}
