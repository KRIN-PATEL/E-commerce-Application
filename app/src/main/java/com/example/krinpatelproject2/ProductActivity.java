package com.example.krinpatelproject2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ProductViewModel productViewModel;
    private ViewPager2 viewPager;
    private ImageSliderAdapter imageSliderAdapter;
    private static final String TAG = "ProductActivity";
    private List<Product> allProducts = new ArrayList<>();
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


        viewPager = findViewById(R.id.viewPager);
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("pro1");
        imageUrls.add("pro2");
        imageUrls.add("pro7");
        imageUrls.add("pro8");
        imageUrls.add("pro9");
        imageUrls.add("pro10");
        imageUrls.add("pro11");
        imageUrls.add("pro12");

        imageSliderAdapter = new ImageSliderAdapter(imageUrls);
        viewPager.setAdapter(imageSliderAdapter);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(productAdapter);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getAllProducts().observe(this, products -> {
            allProducts = products; // Store all products
            productAdapter.setProducts(allProducts); // Initialize with all products
            Log.d(TAG, "Number of products retrieved: " + products.size());
        });

        //  search bar
        searchBar = findViewById(R.id.search_bar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterProducts(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    private void filterProducts(String query) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getProductName().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))) {
                filteredProducts.add(product);
            }
        }
        productAdapter.setProducts(filteredProducts);
    }
}
