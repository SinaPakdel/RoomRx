package com.sina.roomrx.repository;

import androidx.lifecycle.ViewModel;

import com.sina.roomrx.model.DomainModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class DomainViewModel extends ViewModel {
    private DomainRepository domainRepository;

    @Inject
    public DomainViewModel(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    public Flowable<List<DomainModel>> getAll() {
        return domainRepository.getAll();
    }

    public Completable insertDomain(String domainName) {
        DomainModel domainModel = new DomainModel(domainName);
        return domainRepository.insertDomain(domainModel);
    }

    public Completable deleteDomain(int id) {
        return domainRepository.deleteDomain(id);
    }

    public Completable updateDomain(DomainModel domainModel) {
        return domainRepository.updateDomain(domainModel);
    }

    public Completable deleteAll() {
        return domainRepository.deleteAll();
    }
}