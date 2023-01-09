package com.example.one1.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.one1.R;
import com.example.one1.utils.model.Profile;
import com.example.one1.viewmodel.ProfileViewModel;

public class ProfileActivity extends AppCompatActivity {
    private EditText txtName,txtPhone,txtAddress,txtCity,txtCode;
    private ProfileViewModel viewModel;
    private Button btnPlaceOrder;
    private Profile profile;
    private int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeVariables();
        //profile = getIntent().getParcelableExtra("profile");
        setDataToWidgets();

    }

    private void initializeVariables() {

        profile = new Profile();
        txtName = findViewById(R.id.txtName);
        txtAddress = findViewById(R.id.txtAddress);
        txtCode = findViewById(R.id.txtCode);
        txtCity = findViewById(R.id.txtCity);
        txtPhone = findViewById(R.id.txtPhone);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    if(Validate())
                    {
                        getDataFromWidgets();
                        viewModel.deleteAll();
                        viewModel.insert(profile);
                        Context context = getApplicationContext();
                        CharSequence text = "Order Placed!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    }
                });
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    }
    private void setDataToWidgets() {

        if (viewModel.getItems().size()>0) {
            profile = viewModel.getItems().get(0);

        txtName.setText(profile.getName());
        txtAddress.setText(profile.getAddress());
        txtPhone.setText(profile.getPhone());
        txtCity.setText(profile.getCity());
        txtCode.setText(profile.getClientCode());
        Id = profile.getId();
    }
    }
    private void getDataFromWidgets() {
        profile.setName(txtName.getText().toString()); ;
        profile.setAddress(txtAddress.getText().toString());
        profile.setPhone(txtPhone.getText().toString());
        profile.setCity(txtCity.getText().toString());
        profile.setId(Id);
        profile.setClientCode(Integer.parseInt(txtCode.getText().toString()));
    }
    private boolean Validate() {


        if (TextUtils.isEmpty(txtName.getText())) {
            txtName.setError("Name is required!");
            return false;
        }

        if (TextUtils.isEmpty(txtAddress.getText())) {
            txtAddress.setError("Address is required!");
            return false;
        }
        if (TextUtils.isEmpty(txtPhone.getText())) {
            txtPhone.setError("Phone is required!");
            return false;
        }
        if (TextUtils.isEmpty(txtCity.getText())) {
            txtCity.setError("City is required!");
            return false;
        }
        /*
        if (TextUtils.isEmpty(txtCode.getText())) {
            txtCode.setError("Code is required, get it from Afzal Plastic Industry!");
            return false;
        }*/
        return true;
    }
}