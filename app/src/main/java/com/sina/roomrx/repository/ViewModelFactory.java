package com.sina.roomrx.repository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private DomainRepository domainRepository;

    @Inject
    public ViewModelFactory(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DomainViewModel.class)) {
            return (T) new DomainViewModel(domainRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}