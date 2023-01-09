package com.example.one1.views;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.one1.AppController;
import com.example.one1.R;
import com.example.one1.dao.ProductDAO;
import com.example.one1.mock.APIWebServer;
import com.example.one1.repository.CartRepo;
import com.example.one1.repository.ProductRepo;
import com.example.one1.utils.AppConstants;
import com.example.one1.utils.adapter.ShoeItemAdapter;
import com.example.one1.utils.model.Product;
import com.example.one1.utils.model.ShoeCart;
import com.example.one1.utils.model.ShoeItem;
import com.example.one1.viewmodel.CartViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


public class start extends AppCompatActivity {

    private ProductRepo productRepo;
    private ProductDAO productDAO;
    private RecyclerView recyclerView;
    private ArrayList<ShoeItem> shoeItemList;
    private List<Product> productList;
    private ShoeItemAdapter adapter;
    private CartViewModel viewModel;
    private List<ShoeCart> shoeCartList;
    private CoordinatorLayout coordinatorLayout;
    private ImageView cartImageView;
    List<Product> productListAPI;
    private EditText etSearch;
    //Context context;
    APIWebServer apiWebServer;
    private ProductRepo r ;
    private CardView cvProduct,cvRefresh,cvAccount,cvContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        cvProduct=findViewById(R.id.cvShowProduct);
        cvRefresh=findViewById(R.id.cvRefresh);
        //context = this;
        r =  new ProductRepo(this.getApplication());
        apiWebServer =  new APIWebServer(this);

        (new Handler()).postDelayed(this::setupLists, 5000);

        cvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(start.this, MainActivity.class));
            }
        });


        cvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //(new Handler()).postDelayed(this::setupLists, 5000);
                setupLists();

            }
        });

    }

    private    void setupLists(){

        try {
            productListAPI = apiWebServer.getAllProducts();

        } catch (Exception ex) {
            Toast.makeText(start.this, "Exception !" + ex.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(start.this, "Exception !" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            Toast.makeText(start.this, String.valueOf(productListAPI.size())+" No of Products Saved.", Toast.LENGTH_SHORT).show();
        }}



}