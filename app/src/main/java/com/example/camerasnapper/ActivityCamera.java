package com.example.camerasnapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityCamera extends AppCompatActivity {

    private Button btnTakePhoto;
    private ImageView imageView;

    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        initView();
        initListener();
        EnableRuntimePermission();
    }

    private void initView() {
        btnTakePhoto = findViewById(R.id.btn_take_button);
        imageView = findViewById(R.id.IV_image);
    }

    private void initListener() {
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 7);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 7 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
        if (requestCode == 7 && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "cancelled oooooo", Toast.LENGTH_SHORT).show();
        }
    }

    public void EnableRuntimePermission() {
        ActivityCompat.requestPermissions(ActivityCamera.this, new String[]{
                Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RequestPermissionCode);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        super.onRequestPermissionsResult(requestCode, permissions, result);
        if (result.length > 0
                && requestCode == RequestPermissionCode
                && result[0] == PackageManager.PERMISSION_GRANTED
                && result[1] == PackageManager.PERMISSION_GRANTED
                && result[2] == PackageManager.PERMISSION_GRANTED) {


        Toast.makeText(ActivityCamera.this, "Permission Granted ,Now your application can be access CAMERA.", Toast.LENGTH_SHORT).show();
    }else{
        Toast.makeText(this, "Permission Canceled,Now your application cannot access CAMERA.", Toast.LENGTH_SHORT).show();

        }

    }

}