package com.example.aditya;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class Ana extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    EditText ftv;
    ImageView img;
    StorageReference mStorageRef,ref;
    FirebaseFirestore db;
    Uri imguri;
    Button upld, chs,test;
    static  String ext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana);
        mStorageRef = FirebaseStorage.getInstance().getReference("Analog Electronics");
        db=FirebaseFirestore.getInstance();
        img = (ImageView) findViewById(R.id.imageView);
        upld = (Button) findViewById(R.id.button2);
        chs = (Button) findViewById(R.id.button3);
        test=(Button)findViewById(R.id.button4);
        ftv=(EditText)findViewById(R.id.ftv);





        chs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filechooser();

            }
        });

        upld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploader();

            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Ana.this,Test.class));

            }
        });






    }

    private String getextension(Uri uri)

    {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }



    private void uploader() {



        if(ftv.getText().toString().trim().isEmpty())
        {
            ftv.setError("Name Required!");
            return;
        }


        final StorageReference ref = mStorageRef.child(ftv.getText().toString().trim());
        ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        ext= getextension(imguri);
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloaduri= uri;
                                Map<String, Object> user = new HashMap<>();
                                user.put("link",downloaduri.toString());
                                user.put("name",ftv.getText().toString().trim());
                                db.collection("ana").add(user);
                            }
                        });

                        Toast.makeText(getApplicationContext(), "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }


    private void Filechooser() {


        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, 1);



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK&& data!=null&&data.getData()!=null)

        {
            imguri=data.getData();
            img.setImageURI(imguri);
        }
    }





}
