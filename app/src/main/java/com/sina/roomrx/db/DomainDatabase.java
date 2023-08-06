package com.sina.roomrx.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.sina.roomrx.model.DomainModel;

@Database(entities = {DomainModel.class}, version = 1,exportSchema = false)
public abstract class DomainDatabase extends RoomDatabase {
    public abstract DomainDao domainDao();
}
