/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */
package com.example.one1.mock;
import android.content.Context;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.one1.helper.NetworkConstants;
import com.example.one1.repository.ProductRepo;
import com.example.one1.utils.AppConstants;
import com.example.one1.utils.model.Product;
import com.example.one1.utils.model.ShoeItem;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
/*
 * This class serve as fake server and provides dummy product and category with real Image Urls taken from flipkart
 */
public class APIWebServer {

    private   ArrayList<Product> listOfProductAPI;
    public  APIWebServer(Context context) {
        if(AppConstants.mQueue==null)
            AppConstants.mQueue = Volley.newRequestQueue(context);
        listOfProductAPI = new ArrayList<Product>();

        GetCategoryAPI();
    }

    public void GetCategoryAPI() {

        Log.d("Rehman getRequestJson",NetworkConstants.URL_GET_ALL_PRODUCTS);
        AppConstants.mQueue.add(HTTPReq.getRequestJson(NetworkConstants.URL_GET_ALL_PRODUCTS, new VolleyCallbackJson() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    JSONArray jsonArray = result.getJSONArray("body");

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                            Product p = new Product();
                            p.setShoeName(jsonObject.getString("code") + "-" + jsonObject.getString("name"));
                            p.setShoeBrandName(jsonObject.getString("qty_in_full_paking"));
                            //p.setQuantity(0);
                            p.setShoePrice(jsonObject.getDouble("rate"));
                            p.setErpid(jsonObject.getInt("erpid"));
                            listOfProductAPI.add(p);

                        Log.d("Rehman Added","Added"+jsonObject.getString("id"));
                    }

                }
                catch (Exception w)
                {
                    Log.d("Rehman Exception",w.getMessage());
                }
                //CenterRepository.getCenterRepository().setListOfCategory(listOfCategory);

            }
            @Override
            public void onError(String result) {
                System.out.println(" rehman "+result);
            }
        }));

    }



    public List<Product> getAllProducts() {
        return listOfProductAPI;
    }
}
