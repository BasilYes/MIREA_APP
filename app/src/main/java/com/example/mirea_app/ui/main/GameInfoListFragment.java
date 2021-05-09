package com.example.mirea_app.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mirea_app.R;

import java.util.ArrayList;
import java.util.List;

public class GameInfoListFragment extends Fragment {

    private String gameType = "null";


    public GameInfoListFragment() {
    }

    public static GameInfoListFragment newInstance(String gameType) {
        GameInfoListFragment fragment = new GameInfoListFragment();
        Bundle args = new Bundle();
        args.putString("GAME_TYPE", gameType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            gameType = getArguments().getString("GAME_TYPE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_info_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new GameInfoRecyclerViewAdapter(getInfo()));
        }
        return view;
    }

    List<GameInfoListItem> getInfo(){

        List<GameInfoListItem> out = new ArrayList<>();
        if (!gameType.equals("null")){
            out.add(new GameInfoListItem(gameType + ":"));
            out.add(new GameInfoListItem("Тут какой-то контент (стримы, повторы и т.п.) в виде кликабельных строчек"));
            return out;
        }
        out.add(new GameInfoListItem("Выбери игру которая интересует"));
        return out;
    }
}