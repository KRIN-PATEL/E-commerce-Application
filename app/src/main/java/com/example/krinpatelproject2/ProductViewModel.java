package com.example.krinpatelproject2;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private final LiveData<List<Product>> allProducts;
    private final ProductDao productDao;

    public ProductViewModel(@NonNull Application application) {
        super(application);


        AppDatabase db = AppDatabase.getInstance(application);


        productDao = db.productDao();


        allProducts = productDao.getAllProducts();
    }


    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }
}
