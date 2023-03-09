package com.example.verbumly

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Continuation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import de.hdodenhof.circleimageview.CircleImageView


class EditProfile : AppCompatActivity() {

    private lateinit var avatar: CircleImageView;
    private lateinit var closeBtn: Button;
    private lateinit var updateBtn: Button;
    private lateinit var changeAvatarBtn: TextView;

    private lateinit var dbRef: DatabaseReference;
    private lateinit var auth: FirebaseAuth;

    private lateinit var imageUri: Uri;
    private var uri = "";
    private var uploadTask: StorageTask<*>? = null
    private lateinit var stRef: StorageReference;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().reference.child("User");
        stRef = FirebaseStorage.getInstance().reference.child("Avatar");

        avatar = findViewById(R.id.user_avatar);

        closeBtn = findViewById(R.id.btn_close);
        updateBtn = findViewById(R.id.btn_update);
        closeBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(this@EditProfile, Menu::class.java)
                startActivity(intent)
                finish()
            }
        })

        updateBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                uploadAvatar();
            }
        })

        changeAvatarBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                CropImage.activity().setAspectRatio(1, 1).start(this@EditProfile)
            }
        })
    }

    @Override
    protected fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val result = CropImage.getActivityResult(data)
            imageUri = result.getUri()
            avatar.setImageURI((imageUri))
        } else {
            Toast.makeText(this, "Error, try again", Toast.LENGTH_SHORT).show();
        }
    }

    private fun uploadAvatar() {
        val progressDialog: ProgressDialog = ProgressDialog(this)
        progressDialog.setTitle("Set your profile")
        progressDialog.setMessage("Please wait while we are setting your data");
        progressDialog.show();

        if (imageUri != null) {
            val fileRef: StorageReference = stRef.child((auth.currentUser?.uid.plus(".jpg")))

            uploadTask = fileRef.putFile(imageUri);

            (uploadTask as UploadTask).continueWith {
                Continuation() {

                }
            }
        }
    }
    changeAvatarBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            getImageFromAlbum();
        }
    });
}