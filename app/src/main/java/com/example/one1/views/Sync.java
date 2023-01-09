package com.example.one1.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.one1.R;
import com.example.one1.mock.APIWebServer;
import com.example.one1.repository.ProductRepo;
import com.example.one1.utils.model.Product;
import com.example.one1.utils.model.ShoeCart;
import com.example.one1.viewmodel.CartViewModel;
import com.example.one1.viewmodel.ProductViewModel;

import java.util.List;

public class Sync extends AppCompatActivity {

    Button btnRefresh, btnGet,btnGetAPI;
    TextView txtResponse;
    List<Product> productList;
    List<Product> productListAPI;
    APIWebServer apiWebServer;
    Context context;
    //private ProductViewModel viewModel;
     private ProductRepo r ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnGet = findViewById(R.id.btnGet);
        btnGetAPI = findViewById(R.id.btnGetAPI);
        txtResponse = findViewById(R.id.txtResponse);
        context = this;
        r =  new ProductRepo(this.getApplication());
        apiWebServer = new APIWebServer(context);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            //Getting Data From API and sending it to DB for Saving
            @Override
            public void onClick(View view) {
                try {
                productListAPI = apiWebServer.getAllProducts();

                } catch (Exception ex) {
                    Toast.makeText(Sync.this, "Exception !" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

                if(productListAPI!=null) {
                    r.deleteAll();
                    for (int i = 0; i < productListAPI.size(); i++){

                Product p = new Product();
                p.setErpid(productListAPI.get(i).getErpid());
                p.setQuantity(productListAPI.get(i).getQuantity());
                p.setTotalItemPrice(productListAPI.get(i).getTotalItemPrice());
                p.setShoePrice(productListAPI.get(i).getShoePrice()); //price full packing
                p.setShoeName(productListAPI.get(i).getShoeName());
                p.setShoeBrandName(productListAPI.get(i).getShoeBrandName()); //Qty in full packing
                try {
                    r.insert(p);

                } catch (Exception ex) {
                    Toast.makeText(Sync.this, "Exception !" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
                    Toast.makeText(Sync.this, String.valueOf(productListAPI.size())+" No of Items Saved", Toast.LENGTH_SHORT).show();
                }}
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(this, CartActivity.class));

                r.getAllproducts().observe(Sync.this, new Observer<List<Product>>() {
                    @Override
                    public void onChanged(@Nullable List<Product> keysFromDB) {
                        productList = keysFromDB;
                    }
                });

                if(productList!=null) {
                    for (int i = 0; i < productList.size(); i++)
                        Toast.makeText(Sync.this, String.valueOf(i) + " Value=" + productList.get(i).getShoeName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnGetAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(this, CartActivity.class));

               apiWebServer = new APIWebServer(context);
               productListAPI = apiWebServer.getAllProducts();

                if(productListAPI!=null) {
                    for (int i = 0; i < productList.size(); i++)
                        Toast.makeText(Sync.this, String.valueOf(i) + " Value=" + productList.get(i).getShoeName(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    }
