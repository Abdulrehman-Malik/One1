/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

package com.example.one1.helper;

import android.util.Log;

import com.example.one1.repository.ProductRepo;
import com.example.one1.utils.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    private int networktaskType;
    private String jsonResponse;

    /**
     * @param networktaskType
     * @param jsonResponse
     */
    public JSONParser(int networktaskType, String jsonResponse) {
        super();
        this.networktaskType = networktaskType;
        this.jsonResponse = jsonResponse;
    }
    public JSONParser() {
        super();

    }

    public static JSONObject toJSON(Object object) throws JSONException,
            IllegalAccessException {
        Class c = object.getClass();
        JSONObject jsonObject = new JSONObject();
        for (Field field : c.getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            String value = String.valueOf(field.get(object));
            jsonObject.put(name, value);
        }
        System.out.println(jsonObject.toString());

        return jsonObject;
    }



    public static JSONArray toJSONFromList(List list) throws JSONException,
            IllegalAccessException {
        JSONArray jsonArray = new JSONArray();
        for (Object i : list) {
            JSONObject jstr = toJSON(i);
            // JSONObject jsonObject = new JSONObject(jstr);
            jsonArray.put(jstr);
        }
        return jsonArray;
    }
/*
    public void parse() {

        if (jsonResponse != null) {
            try {
                switch (networktaskType) {

                    case NetworkConstants.GET_ALL_PRODUCT:

                        if (NetworkConstants.DEBUGABLE)
                            Log.e("Parse: ", "GET_ALL_PRODUCT " + jsonResponse);
                        JSONArray categoryArray2 = new JSONArray(jsonResponse);
                        productRepo.getCenterRepository().getListOfCategory().clear();
                        for (int i2 = 0; i2 < categoryArray2.length(); i2++) {
                            CenterRepository
                                    .getCenterRepository()
                                    .getListOfCategory()
                                    .add(new ProductCategoryModel(categoryArray2
                                            .getJSONObject(i2).getString(
                                                    "categoryName"), categoryArray2
                                            .getJSONObject(i2).getString(
                                                    "productDescription"),
                                            categoryArray2.getJSONObject(i2)
                                                    .getString("productDiscount"),
                                            categoryArray2.getJSONObject(i2)
                                                    .getString("productUrl")));

                        }
                        break;


                    default:
                        break;
                }

                // TODO parse JSON acc to request
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

    }
    */

}
