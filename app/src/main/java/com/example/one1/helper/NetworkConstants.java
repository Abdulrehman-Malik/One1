/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

package com.example.one1.helper;


// TODO: Auto-generated Javadoc

/**
 * The Interface NetworkConstants.
 */
public interface NetworkConstants {
    public boolean DEBUGABLE = true;

    /**
     * The Constant URL_BASE_URI.
     */
    // public static final String URL_BASE_URI =
    // "http://localhost/service.api/";

    public static final String URL_BASE_URI = "https://almuraabit.com/service/service/api/";

    /**
     * The Constant URL_GET_PRODUCTS_BY_CATEGORY.
     */
    public static final String URL_GET_ALL_PRODUCTS = URL_BASE_URI
            + "products/read.php";

    /**
     * The Constant URL_GET_PRODUCTS_BY_CATEGORY.
     */
    public static final String URL_GET_PRODUCTS_MAP = URL_BASE_URI
            + "productMap";

    /**
     * The Constant URL_GET_PRODUCTS_BY_CATEGORY.
     */
    public static final String URL_PLACE_ORDER = URL_BASE_URI + "insertOrder";

    public static final String URL_APPLY_COUPAN = URL_BASE_URI + "validateCoupan";


    // -------------------------
    // Products functionality
    /**
     * The Constant getProductByCategory.
     */
    // -------------------------
    public static final int GET_ALL_PRODUCT_BY_CATEGORY = 0;
    public static final int GET_ALL_PRODUCT = 1;
    public static final int GET_SHOPPING_LIST = 9;
    public static final int GET_CATEGORIES_LIST = 2;


}
