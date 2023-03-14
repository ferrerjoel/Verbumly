package com.example.verbumly

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*



class EditProfile : AppCompatActivity() {

    private lateinit var avatar: CircleImageView;
    private lateinit var closeBtn: Button;
    private lateinit var updateBtn: Button;
    private lateinit var changeAvatarBtn: Button;

    private lateinit var auth: FirebaseAuth;

    private lateinit var imageUri: Uri;
    private lateinit var stRef: StorageReference;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        auth = FirebaseAuth.getInstance();

        avatar = findViewById<CircleImageView>(R.id.user_avatar);

        closeBtn = findViewById(R.id.btn_close);
        updateBtn = findViewById(R.id.btn_update);
        changeAvatarBtn = findViewById(R.id.change_avatar_btn)


        closeBtn.setOnClickListener {
            val intent = Intent(this@EditProfile, Menu::class.java)
            startActivity(intent)
            finish()
        }
        var resultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
            Picasso.get().load(result).into(avatar)
        }
        changeAvatarBtn.setOnClickListener(View.OnClickListener {
            resultLauncher.launch("image/*")
        });
        updateBtn.setOnClickListener(View.OnClickListener{

            uploadImage();
        })


    }

    private fun uploadImage() {

        // Get the data from an ImageView as bytes
        avatar.isDrawingCacheEnabled = true
        avatar.buildDrawingCache()
        val bitmap = (avatar.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }
}