package com.example.krinpatelproject2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView cartRecyclerView;
    private TextView cartTotal, cartTax, cartGrandTotal;
    private Button btnCheckout;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cart_recycler_view);
        cartTotal = findViewById(R.id.cart_total);
        cartTax = findViewById(R.id.cart_tax);
        cartGrandTotal = findViewById(R.id.cart_grand_total);
        btnCheckout = findViewById(R.id.btn_checkout);

        cartItems = CartManager.getInstance().getCartItems();
        cartAdapter = new CartAdapter(cartItems, this, this::updateTotals);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        updateTotals();

        // this is condtion to check cart is empty or not
        if (cartItems.isEmpty()) {
            btnCheckout.setEnabled(false);
            btnCheckout.setText("Cart is empty");
        } else {
            btnCheckout.setEnabled(true);
            btnCheckout.setText("Checkout");
        }

        btnCheckout.setOnClickListener(v -> {
            if (!cartItems.isEmpty()) {
                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                String username = prefs.getString("loggedInUsername", "User");
                Intent checkoutIntent = new Intent(CartActivity.this, CheckoutActivity.class);
                checkoutIntent.putExtra("USERNAME", username);
                startActivity(checkoutIntent);
            } else {

                Toast.makeText(this, "Your cart is empty!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTotals() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        double tax = total * 0.13;
        double grandTotal = total + tax;

        cartTotal.setText(String.format("Total: $%.2f", total));
        cartTax.setText(String.format("Tax: $%.2f", tax));
        cartGrandTotal.setText(String.format("Grand Total: $%.2f", grandTotal));
    }
}
