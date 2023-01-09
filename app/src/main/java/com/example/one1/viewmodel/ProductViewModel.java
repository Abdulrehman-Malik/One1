package com.example.one1.viewmodel;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.one1.repository.CartRepo;
import com.example.one1.repository.ProductRepo;
import com.example.one1.utils.model.Product;
import com.example.one1.utils.model.ShoeCart;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepo cartRepo;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        cartRepo = new ProductRepo(application);
    }

    public LiveData<List<Product>> getAllProducts() {
        return cartRepo.getAllproducts();
    }

    public void insertProduct(Product shoeCart) {
        cartRepo.insert(shoeCart);
    }

    /*
    public void updateQuantity(int id, int quantity) {
        cartRepo.updateQuantity(id, quantity);
    }

    public void updatePrice(int id, double price) {
        cartRepo.updatePrice(id, price);
    }

    public void deleteProduct(Product shoeCart) {
        cartRepo.deleteProduct(shoeCart);
    }

    public void deleteAllProduct() {
        cartRepo.deleteAllProduct();
    }

     */
}
