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
import androidx.activity.OnBackPressedCallback
import java.util.*

const val DELAY_TIME_FRAGMENTS = 3000L
class Credits : AppCompatActivity() {

    var timer = Timer()

    private lateinit var backButton: Button
    private lateinit var pizzaButton: Button

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        timer.cancel()
    }

    /**
     * This callback is called when the user tries to return using it's phone back button, this way we can ensure the timer is closed and that there aren't any "fragment bucles" when trying to return
     */
    private val backCallback = object : OnBackPressedCallback(true /* enabled by default */) {
        override fun handleOnBackPressed() {
            timer.cancel()
            finish()
        }
    }

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
        onBackPressedDispatcher.addCallback(this, backCallback)
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