package com.example.krinpatelproject2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField, passwordField;
    private Button btnLogin;
    private TextView txtRegisterPrompt;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        usernameField = findViewById(R.id.usernameField);
        passwordField = findViewById(R.id.passwordField);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegisterPrompt = findViewById(R.id.txtRegisterPrompt);


        db = AppDatabase.getInstance(getApplicationContext());


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = usernameField.getText().toString();
                final String password = passwordField.getText().toString();


                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }


                new CheckUserTask().execute(username, password);
            }
        });


        txtRegisterPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    private class CheckUserTask extends AsyncTask<String, Void, User> {
        @Override
        protected User doInBackground(String... params) {
            String username = params[0];
            String password = params[1];

            return db.userDao().getUser(username, password);
        }

        @Override
        protected void onPostExecute(User user) {
            if (user != null) {

                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();


                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("loggedInUsername", user.getUsername());
                editor.apply();


                Intent intent = new Intent(LoginActivity.this, ProductActivity.class);
                startActivity(intent);

                finish();
            } else {

                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
