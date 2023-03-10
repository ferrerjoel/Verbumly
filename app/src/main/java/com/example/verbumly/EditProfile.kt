package com.example.verbumly

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
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

        changeAvatarBtn.setOnClickListener(View.OnClickListener {
            @Override
            fun onClick(v : View){

                selectImage();
            }
        });
        updateBtn.setOnClickListener(View.OnClickListener{

            uploadImage();
        })
    }

    private fun uploadImage() {

        var formatter : SimpleDateFormat = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.GERMANY);
        var now : Date = Date();
        var name : String = formatter.format(now)
        stRef = FirebaseStorage.getInstance().getReference("images/"+name);


        stRef.putFile(imageUri).addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot>(){
            fun onSucces(taskSnapshot : UploadTask.TaskSnapshot){
                Toast.makeText(this@EditProfile, "Successfully uploaded avatar", Toast.LENGTH_SHORT).show()
            }
        }).addOnFailureListener(OnFailureListener {
            fun onFailure(){
                Toast.makeText(this@EditProfile, "Failed to upload avatar", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun selectImage() {

        val i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(i, 100);

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && data != null && data.data != null){

            imageUri = data.data!!;
            Picasso.get().load(imageUri).into(avatar)

        }
    }

}