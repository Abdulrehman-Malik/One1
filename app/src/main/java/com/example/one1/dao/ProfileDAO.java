package com.example.one1.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.one1.utils.model.Product;
import com.example.one1.utils.model.Profile;

import java.util.List;

@Dao
public interface ProfileDAO {

    @Insert
    void insertProfile(Profile p);

    @Query("SELECT * FROM Profile_table")
    public List<Profile> getProfile();


    @Query("DELETE FROM Profile_table")
    void deleteProfile();


}
