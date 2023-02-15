package com.example.epicuntitledmobilegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.Timer
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity() {

    val splashScreenDuration: Long = 3000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        //Fullscreen
        supportActionBar?.hide()

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        changeAct()
    }

    private fun changeAct() {
        Timer().schedule(splashScreenDuration) {
            startLogin()
        }
    }
    fun startLogin(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}