package com.sina.roomrx;

import static com.sina.roomrx.R.string.all_domains_deleted;
import static com.sina.roomrx.R.string.deleted;
import static com.sina.roomrx.R.string.successfully_added;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.sina.roomrx.adapter.DomainAdapter;
import com.sina.roomrx.databinding.ActivityMainBinding;
import com.sina.roomrx.db.DomainDao;
import com.sina.roomrx.db.DomainDatabase;
import com.sina.roomrx.dialog.CustomDomainDialog;
import com.sina.roomrx.model.DomainModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements
        DomainAdapter.OnItemDomainLister,
        CustomDomainDialog.ICustomDialog {

    private ActivityMainBinding binding;
    DomainAdapter domainAdapter;
    DomainDatabase domainDatabase;
    DomainDao domainDao;
    private CustomDomainDialog customDomainDialog;
    private FragmentManager fragmentManager;
    private final CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragmentManager = getSupportFragmentManager();
        customDomainDialog = new CustomDomainDialog();

        domainDatabase = Room.databaseBuilder(getApplicationContext(), DomainDatabase.class, "Domain").allowMainThreadQueries().build();
        domainDao = domainDatabase.domainDao();

        binding.floatingActionButton.setOnClickListener(v -> {
            customDomainDialog.show(fragmentManager, getString(R.string.custom_dialog));
        });
        disposable.add(domainDao.getAll().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(MainActivity.this::getDomainData));
    }

    private void getDomainData(List<DomainModel> domainModelList) {
        binding.rvDomainList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        domainAdapter = new DomainAdapter(domainModelList, this);
        binding.rvDomainList.setAdapter(domainAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteAllDomains) {
            disposable.add(domainDao.deleteAll().subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).subscribe(this::allDomainDeleted));
        }
        return super.onOptionsItemSelected(item);
    }

    private void allDomainDeleted() {
        Toast.makeText(this, all_domains_deleted, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(int position) {

    }

    private void domainDeleted(String domainName) {
        Toast.makeText(this, domainName + getString(deleted), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteItemClicked(int position, String domainName) {
        disposable.add(domainDao.deleteDomain(position).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(() -> domainDeleted(domainName)));
    }


    @Override
    public void setText(String domainName) {
        disposable.add(domainDao.insertDomain(new DomainModel(domainName)).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(() -> domainAdded(domainName)));
    }

    private void domainAdded(String domainName) {
        Toast.makeText(this, domainName + getString(successfully_added), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}