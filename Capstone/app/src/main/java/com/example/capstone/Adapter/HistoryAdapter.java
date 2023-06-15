package com.example.capstone.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.capstone.API.DataItem;
import com.example.capstone.API.History;
import com.example.capstone.databinding.ItemDesignBinding;
import com.example.capstone.databinding.ItemHistoryBinding;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ListViewHolder> {
    private final List<History> list;
    private HomeAdapter.OnItemClickCallback onItemClickCallback;

    public HistoryAdapter(List<History> list) {
        this.list = list;
    }

    public void setOnItemClickCallback(HomeAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    @NonNull
    @Override
    public HistoryAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryAdapter.ListViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ListViewHolder holder, int position) {
        holder.itemHistoryBinding.txtItemHistory.setText(list.get(position).getName());
        holder.itemHistoryBinding.txtHistoryPredict.setText(list.get(position).getPredict());
        Glide.with(holder.itemHistoryBinding.getRoot())
                .load(list.get(position).getImage())
                .into(holder.itemHistoryBinding.imgItemHistory);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ItemHistoryBinding itemHistoryBinding;
        public ListViewHolder(@NonNull ItemHistoryBinding itemHistoryBinding) {
            super(itemHistoryBinding.getRoot());
            this.itemHistoryBinding = itemHistoryBinding;
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(History data);
    }
}
