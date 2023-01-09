package com.example.one1.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.one1.dao.CartDAO;
import com.example.one1.repository.database.CartDatabase;
import com.example.one1.utils.model.ShoeCart;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CartRepo {

    private CartDAO cartDAO;
    private LiveData<List<ShoeCart>> allCartItemsLiveData;
    private Executor executor = Executors.newSingleThreadExecutor();

    public LiveData<List<ShoeCart>> getAllCartItemsLiveData() {
        return allCartItemsLiveData;
    }

    public CartRepo(Application application){
        cartDAO = CartDatabase.getInstance(application).cartDAO();
        allCartItemsLiveData = cartDAO.getAllCartItems();
    }

    public void insertCartItem(ShoeCart shoeCart){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.insertCartItem(shoeCart);
            }
        });
    }

    public void deleteCartItem(ShoeCart shoeCart){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.deleteCartItem(shoeCart);
            }
        });
    }

    public void updateQuantity(int id , double quantity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.updateQuantity(id, quantity);
            }
        });
    }

    public void updatePrice(int id , double price){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.updatePrice(id , price);
            }
        });
    }

    public void deleteAllCartItems(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.deleteAllItems();
            }
        });
    }
}
