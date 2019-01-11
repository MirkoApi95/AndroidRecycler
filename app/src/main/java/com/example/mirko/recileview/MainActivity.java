package com.example.mirko.recileview;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_IMAGE = 100;
    private Button add;
    private Button selectImg;
    private EditText testoInp;
    private String testo;
    private ArrayList<Animale> animali;
    private RecyclerView recile;
    private AnimaleAdapter adapter;
    private Intent intent;
    private  Uri imageUri;

    private int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_CODE_PERMISSIONS = 101;
    private static final String KEY_PERMISSIONS_REQUEST_COUNT = "KEY_PERMISSIONS_REQUEST_COUNT";
    private static final int MAX_NUMBER_REQUEST_PERMISSIONS = 2;
    private static final List<String> sPermissions = Arrays.asList(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    );
    private int mPermissionRequestCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=findViewById(R.id.ID_Button_Add);
        selectImg=findViewById(R.id.ID_Button_Image);
        testoInp=findViewById(R.id.Id_Edit_Text_In);
        recile=findViewById(R.id.recyclerView);
        recile.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        animali=new ArrayList<Animale>();
        adapter=new AnimaleAdapter(animali);
        testoInp.setText("");
        intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!testoInp.getText().toString().equals("")){
                    recile.setVisibility(View.GONE);
                    testo=testoInp.getText().toString();
                    animali.add(new Animale(testo,imageUri));
                    adapter.setAnimali(animali);
                    recile.setAdapter(adapter);
                    recile.setVisibility(View.VISIBLE);
                    testoInp.setText("");
                }else{
                    Toast.makeText(MainActivity.this,"Non hai inserito testo!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });

    }


    private void requestPermissionsIfNecessary() {
        if (!checkAllPermissions()) {
            if (mPermissionRequestCount < MAX_NUMBER_REQUEST_PERMISSIONS) {
                mPermissionRequestCount += 1;
                ActivityCompat.requestPermissions(
                        this,
                        sPermissions.toArray(new String[0]),
                        REQUEST_CODE_PERMISSIONS);
            } else {
               // findViewById(R.id.select_id).setEnabled(false);
            }
        }
    }

    private boolean checkAllPermissions() {
        boolean hasPermissions = true;
        for (String permission : sPermissions) {
            hasPermissions &=
                    ContextCompat.checkSelfPermission(
                            this, permission) == PackageManager.PERMISSION_GRANTED;
        }
        return hasPermissions;
    }


    /**
     * Permission Checking
     **/

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            requestPermissionsIfNecessary(); // no-op if permissions are granted already.
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_IMAGE:
                    handleImageRequestResult(data);
                    break;
                default:
                    //Log.d(TAG, "Unknown request code.");
            }
        } else {
            //Log.e(TAG, String.format("Unexpected Result code %s", resultCode));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void handleImageRequestResult(Intent data) {
        imageUri = null;
        if (data.getClipData() != null) {
            imageUri = data.getClipData().getItemAt(0).getUri();
        } else if (data.getData() != null) {
            imageUri = data.getData();
        }
        if (imageUri == null) {
            //Log.e(TAG, "Invalid input image Uri.");
            return;
        }
    }
}
