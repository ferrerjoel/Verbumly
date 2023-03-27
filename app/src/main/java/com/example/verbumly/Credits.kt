package com.example.verbumly

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.verbumly.fragments.LogoFragment
import com.example.verbumly.fragments.NamesFragment
import java.util.*

const val DELAY_TIME_FRAGMENTS = 3000L
class Credits : AppCompatActivity() {

    var timer = Timer()

    private lateinit var backButton: Button
    private lateinit var pizzaButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)

        supportFragmentManager.commit {
            replace<NamesFragment>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        backButton = findViewById(R.id.backButton)
        pizzaButton = findViewById(R.id.pizzaButton)

        backButton.setOnClickListener() {
            timer.cancel()
            finish()
        }

        pizzaButton.setOnClickListener() {
            val uri: Uri =
                Uri.parse("https://www.paypal.com/paypalme/ferrerjoel")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        timer.scheduleAtFixedRate(TimeTask(), DELAY_TIME_FRAGMENTS, DELAY_TIME_FRAGMENTS)
    }

    private inner class TimeTask : TimerTask() {
        private var loadNamesFragment: Boolean = false
        override fun run() {
            if (loadNamesFragment) {
                supportFragmentManager.commit {
                    replace<NamesFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                loadNamesFragment = false

            } else {
                supportFragmentManager.commit {
                    replace<LogoFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                loadNamesFragment = true
            }
        }
    }

}