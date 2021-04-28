package com.example.mirea_app.ui.main;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mirea_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private List<NewsListItem> mValues;
    private Context context;


    public NewsRecyclerViewAdapter(List<NewsListItem> items) {
        mValues = items;
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

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mHeadView = (TextView) view.findViewById(R.id.news_head);
            mBodyView = (TextView) view.findViewById(R.id.news_body);
            mImage = (ImageView) view.findViewById(R.id.news_image);
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}