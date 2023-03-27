package com.example.verbumly

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
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
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.io.ByteArrayOutputStream


class EditProfile : AppCompatActivity(), EasyPermissions.PermissionCallbacks{

    private lateinit var uname: TextView
    private lateinit var mail: TextView
    private lateinit var cStreak: TextView
    private lateinit var mStreak: TextView
    private lateinit var plays: TextView
    private lateinit var avatar: CircleImageView
    private lateinit var closeBtn: ImageButton
    private lateinit var updateBtn: Button
    private lateinit var changeAvatarBtn: Button
    private lateinit var cameraBtn: ImageButton

    private lateinit var topButtons : LinearLayout
    private lateinit var botButtons : LinearLayout

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private lateinit var stRefUrl: StorageReference

    private var username: String = ""
    private var email: String = ""
    private var currStreak: Long = 0
    private var maxStreak: Long = 0
    private var playerPlays: Long = 0

    /**
     * Initializes all the edit profile activity elements
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        uname = findViewById(R.id.uname)
        mail = findViewById(R.id.mail)
        cStreak = findViewById(R.id.curr_streak)
        mStreak = findViewById(R.id.max_streak)
        plays = findViewById(R.id.plays)

        topButtons = findViewById(R.id.topBtn)
        botButtons = findViewById(R.id.botBtn)

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        
        var uid = intent.getStringExtra("UID")
        if(uid == null){
            uid = auth.uid!!
        }else{
            topButtons.visibility = View.GONE
            botButtons.visibility = View.GONE
        }
        //Get all of user info
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                username = snapshot.child(uid)
                    .child("Name").value.toString() // Get the name from Firebase
                email = snapshot.child(uid)
                    .child("Email").value.toString() // Get the email from Firebase
                val stats = snapshot.child(uid).child("Stats")

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
        stRefUrl.child("avatars/" + uid).downloadUrl.addOnSuccessListener {
            Picasso.get().load(it).into(avatar)
        }.addOnFailureListener {
            Log.d("DEBUG", "The user doesn't have an image")
        }

        closeBtn = findViewById(R.id.btn_close)
        updateBtn = findViewById(R.id.btn_update)
        changeAvatarBtn = findViewById(R.id.change_avatar_btn)
        cameraBtn = findViewById(R.id.camera_btn)

        requestCameraPermission()

        closeBtn.setOnClickListener {
            val intent = Intent(this@EditProfile, Menu::class.java)
            startActivity(intent)
            finish()
        }
        //Loads image to imageView
        var resultLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
                Picasso.get().load(result).into(avatar)
            }
        //Open gallery
        changeAvatarBtn.setOnClickListener(View.OnClickListener {
            resultLauncher.launch("image/*")
        })
        //Open camera
        cameraBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 100)
        })
        // Uploads the currently selected img to the database
        updateBtn.setOnClickListener(View.OnClickListener {
            uploadImage(uid)
            Toast.makeText(this,"Updated photo", Toast.LENGTH_SHORT).show()
        })

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode == RESULT_OK){
            val image = data?.extras?.get("data") as Bitmap
            avatar.setImageBitmap(image)
            //Picasso.get().load(image).into(avatar)
        }
    }


    /**
     * Uploads the selected img to the Storage of the the Firebase database
     */
    private fun uploadImage(uid : String) {
        var folderReference: StorageReference = stRefUrl.child("avatars")

        // Get the data from an ImageView as bytes
        avatar.isDrawingCacheEnabled = true
        avatar.buildDrawingCache()
        val bitmap = (avatar.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = folderReference.child(uid).putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }
    private fun requestCameraPermission(){
        if(hasCameraPermissions()){
            return
        }
        EasyPermissions.requestPermissions(this, "You need camera permission", 100, android.Manifest.permission.CAMERA)
    }
    private fun hasCameraPermissions()= EasyPermissions.hasPermissions(this, android.Manifest.permission.CAMERA)
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this, perms)){
            AppSettingsDialog.Builder(this).build().show()
        }else{
            requestCameraPermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult((requestCode),permissions,grantResults, this)
    }
}