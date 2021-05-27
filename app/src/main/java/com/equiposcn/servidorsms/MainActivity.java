package com.equiposcn.servidorsms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkSMSStatePermission();
        setContentView(R.layout.activity_main);

        buttonSalir = findViewById(R.id.buttonSalir);

        buttonSalir.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.exit(0);
                    }
                }
        );
    }

    private void checkSMSStatePermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            Log.i("Mensaje","No tienes permisos para recibir SMS");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 225);
        }else{
            Log.i("mensaje", "Si tienes permiso para recibir SMS");
        }

        permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            Log.i("Mensaje","No tienes permisos para leer SMS");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 225);
        }else{
            Log.i("mensaje", "Si tienes permiso para leer SMS");
        }

        permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.SEND_SMS);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            Log.i("mensaje", "No tienes permisos de enviar SMS");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 255);
        }else{
            Log.i("mensaje", "Tienes permisos de enviar SMS");
        }
    }
}