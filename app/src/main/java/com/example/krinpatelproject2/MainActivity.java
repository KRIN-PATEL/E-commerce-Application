package com.example.krinpatelproject2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegister;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executorService = Executors.newSingleThreadExecutor();


        executorService.execute(this::initializeProducts);


        btnLogin = findViewById(R.id.btnlogin);
        btnRegister = findViewById(R.id.btnregister);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    private void initializeProducts() {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        ProductDao productDao = db.productDao();


        productDao.deleteAllProducts();

        // Insert new products
        List<Product> products = Arrays.asList(
                new Product(0, "I Phone 15 Pro Max ", 2500.00, "The iPhone 15 Pro Max features a 6.7-inch OLED display with 120Hz refresh rate, powered by Apple's improved A17 Pro processor. It has a triple camera setup with a 48MP main camera.The 6.7 Super Retina XDR display2 with ProMotion ramps up refresh rates to 120 Hz  ", "pro1"),
                new Product(0, "Samsung S24 Ultra ", 2700.00, "The Samsung Galaxy S24 Ultra is a smartphone with Design: Big, bold, and titanium Display: Ultra-sharp and fast, with a peak brightness of 2,600 nits.smart Galaxy AI features, great cameras and powerful performance there's little not to like and it's hardly surprising that they've been a hit with consumers", "pro2"),
                new Product(0, "Beats Airbud Pro ", 145.00, "The in-ear design creates a tight seal for engrossing audio. Not to mention there’s spatial audio support that turns on automatically when indulging in Dolby Atmos. Powerbeats Pro supports spatial audio for immersive music ", "pro6"),
                new Product(0, "Sony Sound System", 79.99, "360 Spatial Sound Mapping immerses you in spatial sound by placing speakers all around. Sound Field effect is off, place the three speakers in the corner of your room. Sound Field effect is off", "pro7"),
                new Product(0, "Mac Book Pro", 3000.00, "The MacBook Pro is a line of Mac laptop computers developed and manufactured by Apple. Supercharged by M3 Pro chip, Apple MacBook Pro takes its power and efficiency further than ever.", "pro8"),
                new Product(0, "Mac Moniter ", 2899.99, "Apple’s best Mac monitor, the Pro Display XDR, comes at a price. A very steep price. The display can deliver up to 1,600 nits of brightness as it supports HDR with a billion colors", "pro9"),
                new Product(0, "HP Gaming Mouse", 99.99, "The HP Gaming Mouse is an ambidextrous and customizable mouse with 6 programmable buttons and 8 sensitivity levels.360° RGB LightingPixart 3389 Sensor16,000 DPI 450 IPS  50G Customizable with HyperX", "pro10"),
                new Product(0, "Play Station 5", 750.00, "The PS5 is powered by an eight-core AMD Zen 2 CPU and custom AMD Radeon GPU, and it is offered in two models: one with a 4K Blu-ray drive and one without. It supports a 120Hz video refresh and is considerably", "pro11"),
                new Product(0, "X Box X Series", 500.00, "The Xbox Series X offers 12 teraflops of raw graphic processing power, DirectX ray tracing, a custom 1TB SSD, and 4K gaming123.Enjoy 4K gaming at up to 120fps, advanced 3D spatial sound, and more for an incredible experience", "pro12"),
                new Product(0, "Samsung Galaxy S23 Fe", 1750.00, "The Samsung Galaxy S23 FE was announced in October 2023 and features a 6.4-inch display, Exynos 2200 chipset, 4500 mAh battery, 256 GB storage, 8 GB RAM, and Corning Gorilla Glass 5", "pro13"),
                new Product(0, "Apple Adapter and Cables", 129.99, "An Apple adapter, also known as an Apple charger cable, is the power source for Apple devices. It is available in various wattages, including 5, 12, 20, 30, 45, and 60 watts", "pro14")
        );

        for (Product product : products) {
            productDao.insert(product);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
