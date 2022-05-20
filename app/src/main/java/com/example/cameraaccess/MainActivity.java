package com.example.cameraaccess;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
    //initialize value
    ImageView imageView;
    Button buttonOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.imageView);
        buttonOpen=findViewById(R.id.button_camera);

        //Request for camera permission
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED);
        {
            ActivityCompat.requestPermissions(MainActivity.this,
            new String[]
                    {
                    Manifest.permission.CAMERA
                    },
                100);
        }
       buttonOpen.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //open camera
               Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(i,100);
           }
       });
    }




    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100)
        //get capture image
        {
            Bitmap captureimage = (Bitmap) data.getExtras().get("data");
            //setcapture image to imageview
            imageView.setImageBitmap(captureimage);
        }
    }

}