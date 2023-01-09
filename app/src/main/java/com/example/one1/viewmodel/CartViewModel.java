package com.example.one1.viewmodel;
import static java.lang.Math.round;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.one1.repository.CartRepo;
import com.example.one1.utils.model.ShoeCart;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private CartRepo cartRepo;

    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepo = new CartRepo(application);
    }

    public LiveData<List<ShoeCart>> getAllCartItems() {
        return cartRepo.getAllCartItemsLiveData();
    }

    public void insertCartItem(ShoeCart shoeCart) {
        cartRepo.insertCartItem(shoeCart);
    }

    public void updateQuantity(int id, double quantity) {
        cartRepo.updateQuantity(id, quantity);
    }

    public void updatePrice(int id, double price) {
        cartRepo.updatePrice(id, round(price));
    } //round

    public void deleteCartItem(ShoeCart shoeCart) {
        cartRepo.deleteCartItem(shoeCart);
    }

    public void deleteAllCartItems() {
        cartRepo.deleteAllCartItems();
    }
}
