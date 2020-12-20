package com.example.upvote;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewPost extends AppCompatActivity {
    EditText location,date, time;
    ImageView imageView;
    Button choose,submit;
    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_post);
        location=findViewById(R.id.location);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        imageView=findViewById(R.id.imageview);
        choose=findViewById(R.id.choose);
        submit= findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location1 = location.getText().toString();
                String date1 = date.getText().toString();
                String time1 = time.getText().toString();
                if(location1.equals("")||date1.equals("")||time1.equals(""))
                    Toast.makeText(NewPost.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(NewPost.this, "Submitted Sucessfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Post.class);
                    intent.putExtra("location", location1);
                    intent.putExtra("date",date1);
                    intent.putExtra("time",time1);
                    intent.putExtra("my_image",R.drawable.pothole);
                    startActivity(intent);
                }
            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else {
                        pickImageFromGallery();
                    }
                }
                else{
                    pickImageFromGallery();
                }
            }
        });
    }
    private void pickImageFromGallery(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK && requestCode==IMAGE_PICK_CODE){
            imageView.setImageURI(data.getData());
        }
    }
}
