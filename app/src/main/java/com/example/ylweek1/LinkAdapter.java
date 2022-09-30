package com.example.ylweek1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.LinkViewHolder> {

    private List<Link> mLinks;
    private RecyclerViewClickListener mListener;
    public LinkAdapter(List<Link> links, RecyclerViewClickListener listener) {
        mLinks=links;
        mListener = listener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, String id);
    }
    @NonNull
    @Override
    public LinkAdapter.LinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_link, parent, false);
        return new LinkViewHolder(v, mListener);
    }
    @Override
    public void onBindViewHolder(@NonNull LinkAdapter.LinkViewHolder holder, int position) {
        Link link = mLinks.get(position);
        holder.name.setText(link.getName());
        holder.url.setText(link.getUrl());
        holder.itemView.setTag(link.getUrl());
    }

    @Override
    public int getItemCount() {
        return mLinks.size();
    }

    public class LinkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name,url;
        private RecyclerViewClickListener listener;

        public LinkViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.linkName);
            url=itemView.findViewById(R.id.linkUrl);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, (String) v.getTag());
        }
    }







}
