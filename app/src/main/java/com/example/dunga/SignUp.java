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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    Button signUpNowBtn;
    EditText emailTxt,userNameTxt,passwordTxt;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailTxt=findViewById(R.id.signUpEmailTxt);
        userNameTxt=findViewById(R.id.signUpNameTxt);
        passwordTxt=findViewById(R.id.signUpPassTxt);
        signUpNowBtn=findViewById(R.id.signUpNowBtn);

        mAuth= FirebaseAuth.getInstance();

        signUpNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog signUpDialog= new ProgressDialog(SignUp.this);
                signUpDialog.setMessage("Please Wait...");
                signUpDialog.show();


                String email=emailTxt.getText().toString().trim();
                String password=passwordTxt.getText().toString().trim();



                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        signUpDialog.dismiss();
                        if (task.isSuccessful()){

                            Toast.makeText(SignUp.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

               // userInput();
            }
        });


    }

    public void userInput(){

        String email=emailTxt.getText().toString().trim();
        String username=userNameTxt.getText().toString().trim();
        String password=passwordTxt.getText().toString().trim();


        CheckEmail(email);
        CheckName(username);
        CheckPass(password);
    }

    private boolean CheckEmail(String email){

        if (TextUtils.isEmpty(email)){

            Toast.makeText(this, "Fill email!!", Toast.LENGTH_SHORT).show();

            return false;
        }

        return true;

    }

    private boolean CheckName(String name){

        if (TextUtils.isEmpty(name)){

            Toast.makeText(this, "Fill username!!", Toast.LENGTH_SHORT).show();

            return false;
        }

        return true;

    }

    private boolean CheckPass(String email){

        if (TextUtils.isEmpty(email)){

            Toast.makeText(this, "Fill password!!", Toast.LENGTH_SHORT).show();

            return false;
        }

        return true;

    }

}
