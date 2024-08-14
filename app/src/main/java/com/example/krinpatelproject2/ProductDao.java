package com.example.krinpatelproject2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import androidx.lifecycle.LiveData;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);

    @Query("SELECT * FROM products")
    LiveData<List<Product>> getAllProducts();


    @Query("SELECT * FROM products WHERE id = :productId")
    Product getProductById(int productId);

    //  delete all products
    @Query("DELETE FROM products")
    void deleteAllProducts();
}
