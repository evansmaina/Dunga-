package com.example.dunga;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dunga.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    Button signUpNowBtn;
    EditText emailTxt,passEdtxt;
    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        emailTxt=findViewById(R.id.signInEmailTxt);
        passEdtxt=findViewById(R.id.signInPassTxt);
        signUpNowBtn=findViewById(R.id.signInNowBtn);
        mAuth=FirebaseAuth.getInstance();

        signUpNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog signInDialog= new ProgressDialog(SignIn.this);
               signInDialog.setMessage("Please Wait...");
               signInDialog.show();
               String email= emailTxt.getText().toString().trim();
              String  pass=passEdtxt.getText().toString().trim();



                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                         signInDialog.dismiss();

                        if (task.isSuccessful()){

                            Toast.makeText(SignIn.this, "Login Successful", Toast.LENGTH_LONG).show();

                        }
                        else{

                            Toast.makeText(SignIn.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });
          // userInput();

            }
        });
    }

    public void userInput(){

        String email= emailTxt.getText().toString().trim();
        String  pass=passEdtxt.getText().toString().trim();
            checkEmail(email);
            checkPass(pass);



    }

    private boolean checkEmail(String myemail){
        if (TextUtils.isEmpty(myemail)){
            Toast.makeText(this, "Fill email!!", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }

    private boolean checkPass(String mypass){
        if (TextUtils.isEmpty(mypass)){
            Toast.makeText(this, "Fill password!!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }
}
