package com.example.KitchenApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    static boolean loginSuccess = false;
    static boolean loginLocked = false;
//    private static final String SHARED_PREF_NAME = "shared_pref";
//    static final String KEY_FAIL_COUNT = "count_failed_login";
    static final int failCountLimit = 3;
    static FailCounter failCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        final CardView cardView = findViewById(R.id.activityLoginCardView);
        //    static SharedPreferences sharedPreferences;
        TextView failCountTextView = findViewById(R.id.activityLoginFailCountTextView);
        failCountTextView.setVisibility(View.INVISIBLE);
        failCounter = new FailCounter(failCountLimit);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;
//                int localFailCount = sharedPreferences.getInt(KEY_FAIL_COUNT, 0);
//                loginLocked = (localFailCount >= failCountLimit);
                if (failCounter.checkTooManyFails()) {
                    message = "Too many failed attempts! Login locked";
                } else {
                    EditText editUsername = findViewById(R.id.editUsername);
                    EditText editPassword = findViewById(R.id.editPassword);
                    loginSuccess = checkLogin(
                            editUsername.getText().toString(),
                            editPassword.getText().toString(),
                            getString(R.string.username),
                            getString(R.string.password)
                    );
                    if (loginSuccess) {
//                        setFailCount(0);
                        failCounter.reset();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        failCounter.increment();
//                        localFailCount++;
//                        setFailCount(localFailCount);
                    }
                }
            }
        });
        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // reset count and text view to default state
                failCounter.reset();
//                setFailCount(0);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }
    static boolean checkLogin(
            String enteredUsername,
            String enteredPassword,
            String trueUsername,
            String truePassword
    ) {
        return enteredUsername.equals(trueUsername) && enteredPassword.equals(truePassword);
    }
//    private void setFailCount(int localFailCount) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt(KEY_FAIL_COUNT, localFailCount);  // reset fail count
//        editor.apply();
//        String text = "Failed login count: " + localFailCount;
//        failCountTextView.setText(text);
//        if (localFailCount == 0) {
//            failCountTextView.setVisibility(View.INVISIBLE);
//        } else {
//            failCountTextView.setVisibility(View.VISIBLE);
//        }
//    }
    static class FailCounter {
        int countLimit;
        int count;
        FailCounter(int countLimit) {
            this.countLimit = countLimit;
            reset();
        }
        void increment() {
            count++;
        }
        void reset() {
            count = 0;
        }
        boolean checkTooManyFails() {
            return count >= countLimit;
        }
    }
}
