package com.humosys.dhyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    EditText editTextAge,editTextName;
    String gender;
    int genderState =0;
    FirebaseAuth mAuth;
    Button saveProfile;
    TextView textViewEmail;
    FirebaseUser user;
    boolean b=false;
//    DatabaseReference databaseUser;

    public void setGender(View v){
        boolean checked = ((RadioButton) v).isChecked();
        genderState =1;
        // Check which radio button was clicked
        switch (v.getId()) {
            case R.id.radioButtonMale:
                if (checked) {
                    gender = "Male";
                    break;
                }
            case R.id.radioButtonFemale:
                if (checked) {
                    gender = "Female";
                    break;
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
//        databaseUser = FirebaseDatabase.getInstance().getReference("users");

        saveProfile=(Button) findViewById(R.id.buttonSaveProfile);
        editTextAge = (EditText)findViewById(R.id.editTextAge);
        editTextName = (EditText)findViewById(R.id.editTextName);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);


        if(user!=null)
        {
            String email =user.getEmail();
            textViewEmail.setText("Email Id: "+email);
        }
        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                if(b==true) {
                    mAuth.signOut();
                    startActivity(intent);
                    finish();
                }
            }
        });

    }


    private void saveUserInformation() {


        String displayName = editTextName.getText().toString();
        String age = editTextAge.getText().toString();

        if (displayName.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return ;
        }
        if(age.isEmpty()) {
            editTextAge.setError("Age Required");
            editTextAge.requestFocus();
            return ;
        }
        if(genderState!=1){
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            return ;
        }

        if (user != null ) {
//            String id = databaseUser.push().getKey();
//            DatabaseActivity user1 = new DatabaseActivity(id,displayName,age,gender);
//            databaseUser.child(id).setValue(user1)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Log.i("database", "onSuccess: true");
//                            Toast.makeText(ProfileActivity.this, "DataBase Updated", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.i("database", "onFailure: true");
//                            Toast.makeText(ProfileActivity.this, "DataBase Update Failed", Toast.LENGTH_SHORT).show();
//                        }
//                    });


            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName).build();

            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        b=true;


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Can' Go Back. Save Data First. ", Toast.LENGTH_SHORT).show();
        //Do Nothing
    }
}



