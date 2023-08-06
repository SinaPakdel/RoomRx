package com.sina.roomrx.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sina.roomrx.model.DomainModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface DomainDao {

    @Query("select * from DomainModel")
    Flowable<List<DomainModel>> getAll();

    @Insert
    Completable insertDomain(DomainModel domainModel);


    @Query("delete from DomainModel where id = :id")
    Completable deleteDomain(int id);

    @Update
    Completable updateDomain(DomainModel domainModel);

    @Query("delete from domainmodel")
    Completable deleteAll();
}
