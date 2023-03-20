package com.example.verbumly

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream


class EditProfile : AppCompatActivity() {

    private lateinit var uname: TextView
    private lateinit var mail: TextView
    private lateinit var cStreak: TextView
    private lateinit var mStreak: TextView
    private lateinit var plays: TextView
    private lateinit var avatar: CircleImageView
    private lateinit var closeBtn: Button
    private lateinit var updateBtn: Button
    private lateinit var changeAvatarBtn: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private lateinit var imageUri: String
    private lateinit var stRef: StorageReference
    private lateinit var stRefUrl: StorageReference

    private var username: String = ""
    private var email: String = ""
    private var currStreak: Long = 0
    private var maxStreak: Long = 0
    private var playerPlays: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        uname = findViewById(R.id.uname)
        mail = findViewById(R.id.mail)
        cStreak = findViewById(R.id.curr_streak)
        mStreak = findViewById(R.id.max_streak)
        plays = findViewById(R.id.plays)

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                username = snapshot.child(auth.uid!!)
                    .child("Name").value.toString() // Get the name from Firebase
                email = snapshot.child(auth.uid!!)
                    .child("Email").value.toString() // Get the email from Firebase
                val stats = snapshot.child(auth.uid!!).child("Stats")

                currStreak = stats.child("CurrentStreak").value as Long //Get the current streak
                maxStreak = stats.child("MaxStreak").value as Long //Get the max streak
                playerPlays = stats.child("Plays").value as Long //Get the plays

                uname.text = username
                mail.text = email
                cStreak.text = getString(R.string.statsCurrStreak, currStreak.toString())
                mStreak.text = getString(R.string.statsMaxStreak, maxStreak.toString())
                plays.text = getString(R.string.statsPlays, playerPlays.toString())

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        stRefUrl = FirebaseStorage.getInstance().reference

        avatar = findViewById<CircleImageView>(R.id.user_avatar)

        // Load the image using Picasso
        stRefUrl.child("avatars/" + auth.uid!!).downloadUrl.addOnSuccessListener {
            OnSuccessListener<Uri?> { uri ->
                Picasso.get().load(uri).into(avatar)
                Log.d("DEBUG", "Has image")
            }
        }.addOnFailureListener {
            Log.d("DEBUG", "The user doesn't have an image")
        }

        closeBtn = findViewById(R.id.btn_close)
        updateBtn = findViewById(R.id.btn_update)
        changeAvatarBtn = findViewById(R.id.change_avatar_btn)

        closeBtn.setOnClickListener {
            val intent = Intent(this@EditProfile, Menu::class.java)
            startActivity(intent)
            finish()
        }

        var resultLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
                Picasso.get().load(result).into(avatar)
            }

        changeAvatarBtn.setOnClickListener(View.OnClickListener {
            resultLauncher.launch("image/*")
        })

        updateBtn.setOnClickListener(View.OnClickListener {
            uploadImage()
        })

    }

    private fun uploadImage() {
        var folderReference: StorageReference = stRefUrl.child("avatars")

        // Get the data from an ImageView as bytes
        avatar.isDrawingCacheEnabled = true
        avatar.buildDrawingCache()
        val bitmap = (avatar.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = folderReference.child(auth.uid!!).putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }
}