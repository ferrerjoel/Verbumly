package com.example.verbumly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.verbumly.fragments.LogoFragment
import com.example.verbumly.fragments.NamesFragment
import java.util.*

class Credits : AppCompatActivity() {

    var timer = Timer()

    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)

        supportFragmentManager.commit {
            replace<NamesFragment>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        backButton = findViewById(R.id.imageButton)

        

        backButton.setOnClickListener() {
            timer.cancel()
            finish()
        }

        timer.scheduleAtFixedRate(TimeTask(),3000L,3000L)
    }

    private inner class TimeTask : TimerTask() {
        private var loadNamesFragment: Boolean = false;
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