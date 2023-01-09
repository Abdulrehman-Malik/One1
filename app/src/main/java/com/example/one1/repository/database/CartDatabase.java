package com.example.one1.repository.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.one1.dao.CartDAO;
import com.example.one1.dao.ProductDAO;
import com.example.one1.dao.ProfileDAO;
import com.example.one1.utils.model.Product;
import com.example.one1.utils.model.Profile;
import com.example.one1.utils.model.ShoeCart;

@Database(entities = {ShoeCart.class,Product.class, Profile.class}, version =12)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();
    public abstract ProductDAO ProductDAO();
    public abstract ProfileDAO ProfileDAO();
    private static CartDatabase instance;

    public static synchronized  CartDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext()
                            , CartDatabase.class , "ShoeDatabase")
                            .fallbackToDestructiveMigration().allowMainThreadQueries()
                            .build();
        }
        return instance;
    }
}
