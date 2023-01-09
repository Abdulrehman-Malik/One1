package com.example.one1.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.one1.utils.model.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    @Insert
    void insertProduct(Product shoeCart);

    @Query("SELECT * FROM product_table")
    public LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM product_table")
    public List<Product> getProductList();

    @Delete
    void deleteProduct(Product shoeCart);

    @Query("DELETE FROM product_table")
    void deleteAllProducts();
/*
    @Query("UPDATE product_table SET quantity=:quantity WHERE id=:id")
    void updateQuantity(int id , int quantity);

    @Query("UPDATE product_table SET totalItemPrice=:totalItemPrice WHERE id=:id")
    void updatePrice(int id , double totalItemPrice);


*/
}
