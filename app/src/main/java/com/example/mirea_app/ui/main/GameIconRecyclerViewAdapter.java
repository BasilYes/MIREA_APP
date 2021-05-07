package com.example.mirea_app.ui.main;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mirea_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GameIconRecyclerViewAdapter extends RecyclerView.Adapter<GameIconRecyclerViewAdapter.ViewHolder> {

    private final List<GameIconInfo> mValues;
    GamesTournamentFragment ownFragment;

    public GameIconRecyclerViewAdapter(List<GameIconInfo> items, GamesTournamentFragment ownFragment) {
        mValues = items;
        this.ownFragment = ownFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_game_icon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position % mValues.size());
        Picasso.get().load(mValues.get(position % mValues.size()).getUrl()).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mValues.size() * 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImage;
        public GameIconInfo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImage = (ImageView) view.findViewById(R.id.imageView);
            mImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ownFragment.onClickItem(mItem);
                }
            });
        }
    }
     public int getListSize(){
        return mValues.size();
     }
}