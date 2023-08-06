package com.sina.roomrx.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sina.roomrx.databinding.ItemDomainBinding;
import com.sina.roomrx.model.DomainModel;

import java.util.List;

public class DomainAdapter extends RecyclerView.Adapter<DomainAdapter.ViewHolder> {

    List<DomainModel> domainModelList;
    private final OnItemDomainLister onItemDomainLister;

    public DomainAdapter(List<DomainModel> domainModelList, OnItemDomainLister onItemDomainLister) {
        this.domainModelList = domainModelList;
        this.onItemDomainLister = onItemDomainLister;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemDomainBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvDomain.setText(domainModelList.get(position).domainName);
        holder.binding.btnDelete.setOnClickListener(v -> {
            onItemDomainLister.onDeleteItemClicked(
                    domainModelList.get(holder.getAdapterPosition()).id,
                    domainModelList.get(holder.getAdapterPosition()).domainName);
        });

        holder.binding.getRoot().setOnClickListener(v -> {
            onItemDomainLister.onItemClicked(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return domainModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemDomainBinding binding;

        public ViewHolder(ItemDomainBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemDomainLister {
        void onItemClicked(int position);

        void onDeleteItemClicked(int position, String domainName);
    }
}