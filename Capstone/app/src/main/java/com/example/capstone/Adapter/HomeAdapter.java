package com.example.capstone.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.capstone.API.DataItem;
import com.example.capstone.databinding.ItemDesignBinding;

import java.util.List;
import java.util.zip.Inflater;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ListViewHolder> {

    private final List<DataItem> list;
    private OnItemClickCallback onItemClickCallback;

    public HomeAdapter(List<DataItem> list) {
        this.list = list;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public HomeAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListViewHolder(ItemDesignBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ListViewHolder holder, int position) {
        holder.binding.txtItemHome.setText(list.get(position).getName());
        Glide.with(holder.binding.getRoot()).
                load(list.get(position).getImage()).
                into(holder.binding.imgItemHome);
        holder.itemView.setOnClickListener(v -> {
            onItemClickCallback.onItemClicked(
                    list.get(holder.getAdapterPosition())
            );
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ItemDesignBinding binding;
        public ListViewHolder(@NonNull ItemDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(DataItem data);
    }
}
