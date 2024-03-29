package com.example.verbumly

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*


private const val TAG: String = "STATE"
class Register : AppCompatActivity() {

    lateinit var auth: FirebaseAuth //FIREBASE AUTH

    private lateinit var mailEt: EditText
    private lateinit var passEt: EditText
    private lateinit var nameEt: EditText
    private lateinit var dateTv: TextView
    private lateinit var registerBtn: Button

    // Load data to TV
    // We use calendar (but there are more options)
    val date = Calendar.getInstance().time
    private val formatter = SimpleDateFormat.getDateInstance() //or use
    private val formatedDate = formatter.format(date)
    // We show the TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        mailEt = findViewById<EditText>(R.id.mailEt)
        passEt = findViewById<EditText>(R.id.passEt)
        nameEt = findViewById<EditText>(R.id.nameEt)
        dateTv = findViewById<TextView>(R.id.dateTv)
        registerBtn = findViewById<Button>(R.id.registerBtn)

        dateTv.text = formatedDate

        registerBtn.setOnClickListener() {
            // Validate input
            val email: String = mailEt.text.toString()
            val pass: String = passEt.text.toString()
            // Mail validation
            // If it's not mail type
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mailEt.error = "Invalid Mail"
            } else if (pass.length < 6) {
                passEt.error = "Password less than 6 chars"
            } else {
                registerPlayer(email, pass)
            }
        }
    }

    /**
     * Using the given data it registers the user into the Firebase database
     */
    private fun registerPlayer(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        // Question mark = can be null
        if (user != null) {
            val uidString: String = user.uid
            val mailString: String = mailEt.text.toString()
            val nameString: String = nameEt.text.toString()
            val dateString: String = dateTv.text.toString()

//            val hashPass = Hashing.sha256()
//                .hashString(passString, StandardCharsets.UTF_8)
//                .toString()

            val playerData: HashMap<String, Any> = HashMap<String, Any>()
            val playerStats: HashMap<String, Any> = HashMap<String, Any>()

            playerData["Uid"] = uidString
            playerData["Email"] = mailString
            playerData["Name"] = nameString
            playerData["Date"] = dateString
            playerData["Stats"] = playerStats

            playerStats["MaxStreak"] = 0
            playerStats["CurrentStreak"] = 0
            playerStats["Plays"] = 0

            // We create a cursor and we give it a name
            val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://verbumly-default-rtdb.firebaseio.com/")
            val reference: DatabaseReference = database.getReference("")

            if(reference != null) {
                // Create a child with the values of playerData
                reference.child(uidString).setValue(playerData)
                Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Data base error", Toast.LENGTH_SHORT).show()
            }
            finish()

        } else {
            Toast.makeText(this, "Error creating user ", Toast.LENGTH_SHORT).show()
        }
    }
}

