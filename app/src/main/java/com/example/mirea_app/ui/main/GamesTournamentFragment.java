package com.example.mirea_app.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.mirea_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class GamesTournamentFragment extends Fragment {

    FrameLayout frameLayout;
    public GamesTournamentFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static GamesTournamentFragment newInstance() {
        GamesTournamentFragment fragment = new GamesTournamentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentTransaction transaction;
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.info_list_frame, GameInfoListFragment.newInstance("null"));
        transaction.commit();

        View view = inflater.inflate(R.layout.fragment_tournament_games, container, false);
        frameLayout = view.findViewById(R.id.info_list_frame);
        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.games_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        GameIconRecyclerViewAdapter gameIconRecyclerViewAdapter = new GameIconRecyclerViewAdapter(getNewsList(), this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(gameIconRecyclerViewAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstItemVisible = linearLayoutManager.findFirstVisibleItemPosition();
                int lastItemVisible = linearLayoutManager.findLastVisibleItemPosition();
                if (firstItemVisible >= gameIconRecyclerViewAdapter.getListSize()*2)
                    recyclerView.getLayoutManager().scrollToPosition(gameIconRecyclerViewAdapter.getListSize());
                else if (lastItemVisible <= gameIconRecyclerViewAdapter.getListSize())
                    recyclerView.getLayoutManager().scrollToPosition(gameIconRecyclerViewAdapter.getListSize()*2);
            }
        });

        recyclerView.getLayoutManager().scrollToPosition(gameIconRecyclerViewAdapter.getListSize());
        return view;
    }

    public List<GameIconInfo> getNewsList() {
        String urls[] =
        {
                "https://sun9-37.userapi.com/impf/c858224/v858224266/70312/xVaI0iS0aUM.jpg?size=2076x2160&quality=96&sign=0d09f0fc5b09465e1cde9ae1a58637a5&type=album"
                , "https://images-ext-1.discordapp.net/external/SC06crqBkFq3jO4JZP6C9s4zdC7HwXvJlloG81YoCkA/http/s01.riotpixels.net/data/5e/d9/5ed96fcc-dae6-4ae0-af68-be2ff5993bca.png.240p.jpg"
                , "https://images-ext-2.discordapp.net/external/Ia2U1U05jkDixQjQvd2B10JZhMYlo4cKB6DniScpD0c/https/www.meme-arsenal.com/memes/d86fe462696d9b51e7fdd88ffbfc2174.jpg"
                , "https://images-ext-2.discordapp.net/external/hYmT31fYyU4jko84iBWtC82HRm6NcPZW4EgFy5MI9LI/https/img2.freepng.ru/20180419/wde/kisspng-dota-2-video-game-valve-corporation-logo-source-peru-vector-5ad85a03b45c46.3056671815241282597388.jpg?width=661&height=676"
                , "https://images-ext-2.discordapp.net/external/9zaBSew7jatNp0P06Tmvf3jNlUgP8xKX_Qyi-CRpxHg/https/img2.freepng.ru/20180426/zre/kisspng-league-of-legends-computer-icons-garena-video-game-legends-5ae22337b1c175.7042875415247695917281.jpg?width=676&height=676"
                , "https://images-ext-1.discordapp.net/external/04qHa3s5G0qRXZGgir4rEp4KJlzMtKXc6EldR8kpcXs/https/img.icons8.com/fluent-systems-filled/452/rainbow-six-siege.png"
                , "https://images-ext-1.discordapp.net/external/TRaR-RIIuL11t5VcagRajRYqoLLi3_V_6855nPYUQ1Y/https/yandex-images.clstorage.net/WD4Q9K198/2cd1d0qeQZvK/zblviICdxupKQRuD2Xxy1pJxsmVzM8XT18DLA0vu2bD8u2eAjbBiNLIYrEZtQDdljnDFDiEcYrK82wY_eiDPnv1d-12EjfKfXmUMlllow1I3ARdgR_cwc6PxK1Yqjw1A-R3ihIy0pzFXDhxGPdHFlyxBIn2GaiUVq8yCKg_gmpp8_LorYgnVGBhzXxGbTzRH8qPjVnJ3dwDXJtuSaroqA68aVXK-5uYzND854ctE5DRcE4N-xCt3patu0CgMEunZqN-5uhB9x7qJUB_SSG0Vd1Nx8nUyZrRyJQVZAluJmQO-eBWj_qeHoaRM69J6JFcEnHHS7tXLVtUoDIL4D1DKetrP2lpjnNboaiJvMGtddyQzYPNHgaUH0eZXqvErCokjPTj10D22BRFGb3mCCGWGlVwxcg5lmWSXS40xyk3Cucja7RlYEy62mttSzZO6PmZHUwBi9CGWdHCFJUgxeMtKM91Jt6LMBAcxJTyYA_vkVWZe87JO9hnk5Mn80JtOUenJWu3KKEDc51i6ML5CW1zW1WKgAFRx1nVB9cdacinq2RMvGTaAbRQXsXXfKXJZV9SE7IJBLlQ556bqzCNaX0K4G5jMuFowjkb5K0NOYKlPVTXggbJ0sOeFo3TlmOHJONpyvmn1Y96kNFKkXShgCaZHJl1gY23mqCbE6ZxxiL1Dm9t5f-h5ox82uBlRrBOLbmbHg0MilqLVZXN2tSnBO8rKwa1qZkFM1tWTxD24Q8mU1VQeM9MfF8hnt1lN45hMI4gpm1zpmGB9B3nacJxxew51JmNhEYSj5nRj9xVLQ_kKqlLuOBUAn-SFQ-Z8yFM5tbZ0jtGzXjQolNS5bXDKPFJ661lNG4ri_7fZegB-Ykq_VWSDAZAFAFd0kfWnmcJqizjxnvmncR9Xp9Ekz_mCOdQXN1yx8wxVCjT1CazRCu5ia7uK__oaIb8FiwtBjfJYP-S0Q0BBRbKGtHMEVHhhQ?width=676&height=676"
        };
        String types[] =
                {
                        "mirea"
                        , "HS"
                        , "CSGO"
                        , "DOTA"
                        , "LOL"
                        , "R6"
                        , "VALORANT"
                };
        List<GameIconInfo> ITEMS = new ArrayList<GameIconInfo>();
        for(int i=0;i<urls.length;i++) {
            ITEMS.add(new GameIconInfo(urls[i], types[i]));
        }
        return ITEMS;
    }

    public void onClickItem(GameIconInfo itemInfo){
        if (getFragmentManager() != null) {
            FragmentTransaction transaction;
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.info_list_frame, GameInfoListFragment.newInstance(itemInfo.getType()));
            transaction.commit();
        }
    }
}