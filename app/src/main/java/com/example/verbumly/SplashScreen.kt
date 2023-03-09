package com.example.verbumly

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import java.util.Timer
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity() {

    private val splashScreenDuration: Long = 3000;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen)
        //Fullscreen
        supportActionBar?.hide()

        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        val mp = MediaPlayer.create(applicationContext, R.raw.splash_audio);
        mp.start();
        changeAct();
    }

    private fun changeAct() {
        Timer().schedule(splashScreenDuration) {
            startLogin()
        }
    }
    private fun startLogin(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        //mp.stop();
    }
}