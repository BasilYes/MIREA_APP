package com.example.mirea_app.ui.main;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mirea_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private List<NewsListItem> mValues;
    private Context context;
    private ViewHolder openedNews = null;
    private RecyclerView recyclerView;


    public NewsRecyclerViewAdapter(List<NewsListItem> items, RecyclerView recyclerView) {
        mValues = items;
        this.recyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mHeadView.setText(mValues.get(position).getHead());
        holder.mBodyView.setText(mValues.get(position).getBody());

        holder.adapter = this;

        Picasso.get().load(mValues.get(position).getUrl()).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mHeadView;
        public final TextView mBodyView;
        public final ImageView mImage;
        public NewsListItem mItem;

        public NewsRecyclerViewAdapter adapter;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mHeadView = (TextView) view.findViewById(R.id.news_head);
            mBodyView = (TextView) view.findViewById(R.id.news_body);
            mImage = (ImageView) view.findViewById(R.id.news_image);
            view.findViewById(R.id.news_item_root).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (openedNews != null){
                        if (openedNews == ViewHolder.this){
                            mBodyView.setMaxLines(0);
                            ViewHolder.this.adapter.recyclerView.scrollToPosition(ViewHolder.this.getAdapterPosition());
                            openedNews = null;
                        }
                        else {
                            mBodyView.setMaxLines(1000);
                            openedNews.mBodyView.setMaxLines(0);
                            openedNews = ViewHolder.this;
                            openedNews.adapter.recyclerView.scrollToPosition(openedNews.getAdapterPosition());
                        }
                        return;
                    }
                    mBodyView.setMaxLines(1000);
                    openedNews = ViewHolder.this;
                    openedNews.adapter.recyclerView.scrollToPosition(openedNews.getAdapterPosition());
                }
            });
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}