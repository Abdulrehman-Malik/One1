package com.example.one1.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.one1.R;
import com.example.one1.utils.model.ShoeCart;
import com.example.one1.utils.model.ShoeItem;
import com.example.one1.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    private ImageView shoeImageView;
    private TextView shoeNameTV, shoeBrandNameTV, shoePriceTV;
    private AppCompatButton addToCartBtn,addToCartBtnFull;
    private ShoeItem shoe;
    private CartViewModel viewModel;
    private List<ShoeCart> shoeCartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        shoe = getIntent().getParcelableExtra("shoeItem");
        initializeVariables();

        viewModel.getAllCartItems().observe(this, new Observer<List<ShoeCart>>() {
            @Override
            public void onChanged(List<ShoeCart> shoeCarts) {
                shoeCartList.addAll(shoeCarts);
            }
        });

        if (shoe != null) {
            setDataToWidgets();
        }

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToRoom(0.5);
            }
        });

        addToCartBtnFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToRoom(1);
            }
        });

    }

    private void insertToRoom(double qty){
        ShoeCart shoeCart = new ShoeCart();
        shoeCart.setErpid(shoe.getErpid());
        shoeCart.setShoeName(shoe.getShoeName());
        shoeCart.setShoeBrandName(shoe.getShoeBrandName());
        shoeCart.setShoePrice(shoe.getShoePrice());
        shoeCart.setShoeImage(shoe.getShoeImage());

        final double[] quantity = {qty};
        final int[] id = new int[1];

        if (!shoeCartList.isEmpty()){
            for(int i=0;i<shoeCartList.size();i++){
                if (shoeCart.getShoeName().equals(shoeCartList.get(i).getShoeName())){
                    quantity[0] = shoeCartList.get(i).getQuantity();
                    //quantity[0]++;
                    quantity[0]= quantity[0] + qty;
                    id[0] = shoeCartList.get(i).getId();
                }
            }
        }

        if (quantity[0]==1 || quantity[0]==0.5){
            shoeCart.setQuantity(quantity[0]);
            shoeCart.setTotalItemPrice(quantity[0]*shoeCart.getShoePrice());
            viewModel.insertCartItem(shoeCart);
        }else{

            viewModel.updateQuantity(id[0] ,quantity[0]);
            viewModel.updatePrice(id[0] , quantity[0]*shoeCart.getShoePrice());
        }

        startActivity(new Intent(DetailedActivity.this , CartActivity.class));
    }

    private void setDataToWidgets() {
        shoeNameTV.setText(shoe.getShoeName());
        shoeBrandNameTV.setText("Quantity in Full Packing: "+shoe.getShoeBrandName());
        shoePriceTV.setText("Price of Full Packing: "+String.valueOf(shoe.getShoePrice()));
        //shoeImageView.setImageResource(shoe.getShoeImage());
        shoeImageView.setImageResource(R.drawable.checkout);
    }

    private void initializeVariables() {

        shoeCartList = new ArrayList<>();
        shoeImageView = findViewById(R.id.detailActivityShoeIV);
        shoeNameTV = findViewById(R.id.detailActivityShoeNameTv);
        shoeBrandNameTV = findViewById(R.id.detailActivityShoeBrandNameTv);
        shoePriceTV = findViewById(R.id.detailActivityShoePriceTv);
        addToCartBtn = findViewById(R.id.detailActivityAddToCartBtn);
        addToCartBtnFull = findViewById(R.id.detailActivityAddToCartBtnFull);

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
    }
}