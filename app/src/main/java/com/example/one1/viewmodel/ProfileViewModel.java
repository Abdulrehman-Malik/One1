package com.example.one1.viewmodel;
import static java.lang.Math.round;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.one1.repository.CartRepo;
import com.example.one1.repository.ProfileRepo;
import com.example.one1.utils.model.Profile;
import com.example.one1.utils.model.ShoeCart;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {

    private ProfileRepo Repo;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        Repo = new ProfileRepo(application);
    }

    public List<Profile> getItems() {
        return Repo.getProfile();
    }

    public void insert(Profile i) {
        Repo.insert(i);
    }


    public void deleteAll() {        Repo.delete();    }
}
