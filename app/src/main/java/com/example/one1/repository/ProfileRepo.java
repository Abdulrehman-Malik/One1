package com.example.one1.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.one1.dao.ProductDAO;
import com.example.one1.dao.ProfileDAO;
import com.example.one1.mock.APIWebServer;
import com.example.one1.repository.database.CartDatabase;
import com.example.one1.utils.model.Product;
import com.example.one1.utils.model.Profile;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProfileRepo {

    // below line is the create a variable
    // for dao and list for all courses.
    private ProfileDAO dao;
    private List<Profile> profiles;
   private APIWebServer apiWebServer;
    private Executor executor = Executors.newSingleThreadExecutor();


    // creating a constructor for our variables
    // and passing the variables to it.
    public ProfileRepo(Application application) {
        CartDatabase database = CartDatabase.getInstance(application);
        dao = database.ProfileDAO();
        profiles = dao.getProfile();
        //apiWebServer = new APIWebServer(application.getApplicationContext());
        Log.d("ProductRepo", "ProductRepo: called");
    }
        public   List<Profile> getProfile(){
        return  dao.getProfile();

        }

    // creating a method to insert the data to our database.
    public void insert(Profile model) {

        new InsertProfileAsyncTask(dao).execute(model);
    }
    public void delete() {

        new DeleteAllProfileAsyncTask(dao).execute();
    }



    /*
    public void updateProfile(Profile p) {

        new UpdateProfileAsyncTask(dao).execute(p);
    }

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


    // we are creating a async task method to insert new course.
    private static class InsertProfileAsyncTask extends AsyncTask<Profile, Void, Void> {
        private ProfileDAO dao;

        private InsertProfileAsyncTask(ProfileDAO dao) {
            this.dao = dao;
            //Log.d("InsertProductAsyncTask", "InsertProductAsyncTask: called");
        }

        @Override
        protected Void doInBackground(Profile... model) {

            dao.insertProfile(model[0]);
            return null;
        }
    }


    // we are creating a async task method to insert new course.


    // we are creating a async task method to update our course.
    private static class UpdateProfileAsyncTask extends AsyncTask<Profile, Void, Void> {
        private ProfileDAO dao;

        private UpdateProfileAsyncTask(ProfileDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Profile... models) {
            // below line is use to update
            // our modal in dao.
           // dao.updateProfile(models[0].getId(),models[0].getName(),models[0].getCity(),models[0].getAddress(),models[0].getPhone(),models[0].getCode());
            return null;
        }
    }
/*
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
    private static class DeleteAllProfileAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProfileDAO dao;
        private DeleteAllProfileAsyncTask(ProfileDAO dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteProfile();
            return null;
        }
    }


}
