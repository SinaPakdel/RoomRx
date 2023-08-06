package com.sina.roomrx.repository;

import com.sina.roomrx.db.DomainDao;
import com.sina.roomrx.model.DomainModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class DomainRepository {
    private DomainDao domainDao;

    @Inject
    public DomainRepository(DomainDao domainDao) {
        this.domainDao = domainDao;
    }

    public Flowable<List<DomainModel>> getAll() {
        return domainDao.getAll();
    }

    public Completable insertDomain(DomainModel domainModel) {
        return domainDao.insertDomain(domainModel);
    }

    public Completable deleteDomain(int id) {
        return domainDao.deleteDomain(id);
    }

    public Completable updateDomain(DomainModel domainModel) {
        return domainDao.updateDomain(domainModel);
    }

    public Completable deleteAll() {
        return domainDao.deleteAll();
    }
}