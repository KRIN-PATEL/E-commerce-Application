package com.example.krinpatelproject2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView productImage;
    private TextView productName, productPrice, productDescription, productQuantity;
    private TextView tvIncreaseQuantity, tvDecreaseQuantity, btnAddToCart, btnGoToCart;
    private int quantity = 1;
    private int productId;
    private double price;
    private String imageUrl, name, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        productDescription = findViewById(R.id.product_description);
        productQuantity = findViewById(R.id.product_quantity);
        tvIncreaseQuantity = findViewById(R.id.tv_increase_quantity);
        tvDecreaseQuantity = findViewById(R.id.tv_decrease_quantity);
        btnAddToCart = findViewById(R.id.btn_add_to_cart);
        btnGoToCart = findViewById(R.id.btn_go_to_cart);

        productId = getIntent().getIntExtra("PRODUCT_ID", -1);
        imageUrl = getIntent().getStringExtra("PRODUCT_IMAGE");
        price = getIntent().getDoubleExtra("PRODUCT_PRICE", 0.0);
        name = getIntent().getStringExtra("PRODUCT_NAME");
        description = getIntent().getStringExtra("PRODUCT_DESC");

        productName.setText(name);
        productPrice.setText(String.format("$%.2f", price));
        productDescription.setText(description);
        int resourceId = getResources().getIdentifier(imageUrl, "drawable", getPackageName());
        Glide.with(this).load(resourceId).into(productImage);

        tvIncreaseQuantity.setOnClickListener(v -> {
            quantity++;
            updateQuantity();
        });

        tvDecreaseQuantity.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                updateQuantity();
            }
        });

        btnAddToCart.setOnClickListener(v -> {
            CartItem cartItem = new CartItem(new Product(productId, name, price, description, imageUrl), quantity);
            CartManager.getInstance().addItem(cartItem);
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
        });

        btnGoToCart.setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }

    private void updateQuantity() {
        productQuantity.setText(String.valueOf(quantity));
    }
}
