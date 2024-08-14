package com.example.krinpatelproject2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ThankYouActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        TextView thankYouMessage = findViewById(R.id.thank_you_message);
        Button logoutButton = findViewById(R.id.logout_button);


        String username = getIntent().getStringExtra("USERNAME");


        thankYouMessage.setText("Thank you for your purchase, " + username + "!!");


        CartManager.getInstance().clearCart();


        logoutButton.setOnClickListener(v -> {

            getSharedPreferences("UserPrefs", MODE_PRIVATE).edit().clear().apply();


            Intent intent = new Intent(ThankYouActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
