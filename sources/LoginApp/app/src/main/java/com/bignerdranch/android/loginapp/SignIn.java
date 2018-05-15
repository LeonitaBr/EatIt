package com.bignerdranch.android.loginapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {
    EditText editPhone, editPassword;
    Button btnsignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnsignIn=(Button)findViewById(R.id.btnSignIn);
        editPhone=(EditText)findViewById(R.id.editPhone);
        editPassword=(EditText)findViewById(R.id.editPassword);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");
        btnsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog= new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       if (dataSnapshot.child(editPhone.getText().toString()).exists()){
                           mDialog.dismiss();
                           User user= (User) dataSnapshot.child(editPhone.getText().toString()).getValue(User.class);
                            user.setPhone(editPhone.getText().toString());
                           /*if (user.getPassword().equals(editPassword.getText().toString())) {
                               Intent homeIntent=new Intent(SignIn.this,Home.class);
                                Common.currentUser=user;
                                startActivity(homeIntent);
                                finish();
                           }
                           else{
                               Toast.makeText(SignIn.this,"Wrong Password !",Toast.LENGTH_SHORT).show();
                           }*/

                           if (user != null) {
                               Intent homeIntent=new Intent(SignIn.this,Home.class);
                               Common.currentUser=user;
                               startActivity(homeIntent);
                               finish();
                           }
                           else{
                               Toast.makeText(SignIn.this,"Wrong Password !",Toast.LENGTH_SHORT).show();
                           }
                       }
                       else{
                           mDialog.dismiss();
                           Toast.makeText(SignIn.this,"User doesnt exist",Toast.LENGTH_SHORT).show();
                       }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
