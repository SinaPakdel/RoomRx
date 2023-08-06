package com.sina.roomrx.repository;

import android.app.Application;

import androidx.room.Room;

import com.sina.roomrx.db.DomainDao;
import com.sina.roomrx.db.DomainDatabase;

@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    DomainDatabase provideDomainDatabase() {
        return Room.databaseBuilder(application.getApplicationContext(),
                        DomainDatabase.class, "Domain")
                .build();
    }

    @Provides
    @Singleton
    DomainDao provideDomainDao(DomainDatabase domainDatabase) {
        return domainDatabase.domainDao();
    }

    @Provides
    @Singleton
    DomainRepository provideDomainRepository(DomainDao domainDao) {
        return new DomainRepository(domainDao);
    }

    @Provides
    @Singleton
    ViewModelFactory provideViewModelFactory(DomainRepository domainRepository) {
        return new ViewModelFactory(domainRepository);
    }
}