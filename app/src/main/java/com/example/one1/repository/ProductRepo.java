package com.example.one1.repository;
import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.one1.dao.CartDAO;
import com.example.one1.dao.ProductDAO;
import com.example.one1.mock.APIWebServer;
import com.example.one1.repository.database.CartDatabase;
import com.example.one1.utils.model.Product;
import com.example.one1.utils.model.ShoeCart;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.os.AsyncTask;
import android.util.Log;

public class ProductRepo {

    // below line is the create a variable
    // for dao and list for all courses.
    private ProductDAO dao;
    private LiveData<List<Product>> allproducts;
   private APIWebServer apiWebServer;
    private Executor executor = Executors.newSingleThreadExecutor();


    // creating a constructor for our variables
    // and passing the variables to it.
    public ProductRepo(Application application) {
        CartDatabase database = CartDatabase.getInstance(application);
        dao = database.ProductDAO();
        allproducts = dao.getAllProducts();
        //apiWebServer = new APIWebServer(application.getApplicationContext());
        Log.d("ProductRepo", "ProductRepo: called");
    }
        public   List<Product> getPList(){
        return  dao.getProductList();

        }

    // creating a method to insert the data to our database.
    public void insert(Product model) {

        new InsertProductAsyncTask(dao).execute(model);
    }
    public void deleteAll() {

        new DeleteAllProductsAsyncTask(dao).execute();
    }


    /*
    // creating a method to update data in database.
    public void update(CourseModal model) {
        new UpdateCourseAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(CourseModal model) {
        new DeleteCourseAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the courses.
    public void deleteAllCourses() {
        new DeleteAllCoursesAsyncTask(dao).execute();
    }
*/
    // below method is to read all the courses.
    public LiveData<List<Product>> getAllproducts() {
        return allproducts;
    }

    // we are creating a async task method to insert new course.
    private static class InsertProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDAO dao;

        private InsertProductAsyncTask(ProductDAO dao) {
            this.dao = dao;
            //Log.d("InsertProductAsyncTask", "InsertProductAsyncTask: called");
        }

        @Override
        protected Void doInBackground(Product... model) {

            dao.insertProduct(model[0]);
            return null;
        }
    }


    // we are creating a async task method to insert new course.

/*
    // we are creating a async task method to update our course.
    private static class UpdateCourseAsyncTask extends AsyncTask<CourseModal, Void, Void> {
        private Dao dao;

        private UpdateCourseAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CourseModal... models) {
            // below line is use to update
            // our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCourseAsyncTask extends AsyncTask<CourseModal, Void, Void> {
        private Dao dao;

        private DeleteCourseAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CourseModal... models) {
            // below line is use to delete
            // our course modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }
 */
    // we are creating a async task method to delete all courses.
    private static class DeleteAllProductsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProductDAO dao;
        private DeleteAllProductsAsyncTask(ProductDAO dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllProducts();
            return null;
        }
    }


}
