package com.sina.roomrx.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DomainModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "domain_name")
    public String domainName;


    public DomainModel(String domainName) {
        this.domainName = domainName;
    }
}
