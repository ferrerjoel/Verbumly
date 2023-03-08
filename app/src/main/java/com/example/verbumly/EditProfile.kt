package com.example.verbumly

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import de.hdodenhof.circleimageview.CircleImageView

class EditProfile : AppCompatActivity() {

    private lateinit var avatar : CircleImageView;
    private lateinit var closeBtn : Button;
    private lateinit var updateBtn : Button;
    private lateinit var changeAvatarBtn : TextView;

    private lateinit var dbRef : DatabaseReference;
    private lateinit var  auth : FirebaseAuth;

    private lateinit var imageUri : Uri;
    private var uri = "";
    private val uploadTask : StorageTask<*>? = null
    private lateinit var stRef : StorageReference;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val cropImage = registerForActivityResult(CropImageContract()) { result ->
            if (result.isSuccessful) {
                // Use the returned uri.
                val uriContent = result.uriContent
                //val uriFilePath = result.getUriFilePath(context) // optional usage
            } else {
                // An error occurred.
                val exception = result.error
            }
        }

        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().reference.child("User");
        stRef = FirebaseStorage.getInstance().reference.child("Avatar");

        avatar = findViewById(R.id.user_avatar);

        closeBtn = findViewById(R.id.btn_close);
        updateBtn = findViewById(R.id.btn_update);
        closeBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view : View?){
                val intent = Intent(this@EditProfile, Menu::class.java)
                startActivity(intent)
                finish()
            }
        })

        updateBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view : View?){
                uploadAvatar();
            }
        })

        changeAvatarBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view : View?){
                // Start picker to get image for cropping and then use the image in cropping activity.
                cropImage.launch(
                    options {
                        setGuidelines(CropImageView.Guidelines.ON)
                    }
                )
            }
        })
    }

    private fun uploadAvatar(){

    }
}