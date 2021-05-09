package com.example.mirea_app.ui.main;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mirea_app.R;

import java.util.List;

public class ComputerGridRecyclerViewAdapter extends RecyclerView.Adapter<ComputerGridRecyclerViewAdapter.ViewHolder> {

    private List<Boolean> mValues;
    ComputerGridFragment ownFragment;

    public ComputerGridRecyclerViewAdapter(ComputerGridFragment fragment) {
        ownFragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_computer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(Integer.toString(position+6));
        holder.pos = position+6;
    }

    @Override
    public int getItemCount() {
        return 25;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        int pos;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ownFragment.setSelectedComp(pos);
                }
            });
        }
    }
}