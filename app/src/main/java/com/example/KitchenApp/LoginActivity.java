package com.example.KitchenApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button buttonToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonToMain = findViewById(R.id.buttonToMain);
        buttonToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editUsername = findViewById(R.id.editUsername);
                EditText editPassword = findViewById(R.id.editPassword);
                String testUsername = editUsername.getText().toString();
                String testPassword = editPassword.getText().toString();
                String trueUsername = getString(R.string.username);
                String truePassword = getString(R.string.password);
                boolean loginSuccess = testUsername.equals(trueUsername) && testPassword.equals(truePassword);
                Toast.makeText(LoginActivity.this, "Login " + loginSuccess, Toast.LENGTH_SHORT).show();
                if (loginSuccess) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}
