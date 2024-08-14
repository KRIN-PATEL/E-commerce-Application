package com.example.krinpatelproject2;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Pattern;

public class CheckoutActivity extends AppCompatActivity {
    private EditText nameField, emailField, addressField, pincodeField, cardNumberField, cvvField, expiryDateField;
    private Button submitButton;
    private String username;

    // below is for the validation
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z]+$");
    private static final Pattern PINCODE_PATTERN = Pattern.compile("^\\d{6}$");
    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("^\\d{16}$");
    private static final Pattern CVV_PATTERN = Pattern.compile("^\\d{3}$");
    private static final Pattern EXPIRY_DATE_PATTERN = Pattern.compile("^(0[1-9]|1[0-2])([0-9]{2})$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        username = getIntent().getStringExtra("USERNAME");


        nameField = findViewById(R.id.name_field);
        emailField = findViewById(R.id.email_field);
        addressField = findViewById(R.id.address_field);
        pincodeField = findViewById(R.id.pincode_field);
        cardNumberField = findViewById(R.id.card_number_field);
        cvvField = findViewById(R.id.cvv_field);
        expiryDateField = findViewById(R.id.expiry_date_field);
        submitButton = findViewById(R.id.submit_button);


        pincodeField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        cardNumberField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        cvvField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        expiryDateField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});

        //masking the card number and cvv
        cardNumberField.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        cardNumberField.setTransformationMethod(PasswordTransformationMethod.getInstance());
        cvvField.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        cvvField.setTransformationMethod(PasswordTransformationMethod.getInstance());


        submitButton.setOnClickListener(v -> {
            if (validateInputs()) {

                Intent thankYouIntent = new Intent(CheckoutActivity.this, ThankYouActivity.class);
                thankYouIntent.putExtra("USERNAME", username);
                startActivity(thankYouIntent);
                finish();
            }
        });
    }

    // Validate all inputs
    private boolean validateInputs() {
        String name = nameField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String address = addressField.getText().toString().trim();
        String pincode = pincodeField.getText().toString().trim();
        String cardNumber = cardNumberField.getText().toString().trim();
        String cvv = cvvField.getText().toString().trim();
        String expiryDate = expiryDateField.getText().toString().trim();

        if (!NAME_PATTERN.matcher(name).matches()) {
            nameField.setError("Invalid name. Only letters are allowed.");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.setError("Invalid email address.");
            return false;
        }

        if (address.isEmpty()) {
            addressField.setError("Address is required.");
            return false;
        }

        if (!PINCODE_PATTERN.matcher(pincode).matches()) {
            pincodeField.setError("Invalid pincode. Must be 6 digits.");
            return false;
        }

        if (!CARD_NUMBER_PATTERN.matcher(cardNumber).matches()) {
            cardNumberField.setError("Invalid card number. Must be 16 digits.");
            return false;
        }

        if (!CVV_PATTERN.matcher(cvv).matches()) {
            cvvField.setError("Invalid CVV. Must be 3 digits.");
            return false;
        }

        if (!EXPIRY_DATE_PATTERN.matcher(expiryDate).matches()) {
            expiryDateField.setError("Invalid expiry date. Format should be MMYY.");
            return false;
        }

        return true;
    }
}
