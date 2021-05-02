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

/**
 * A fragment representing a list of Items.
 */
public class NewsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsFragment() {
    }

    public static NewsFragment newInstance(/*int columnCount*/) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new NewsRecyclerViewAdapter(getNewsList(), recyclerView));
        }
        return view;
    }

    public List<NewsListItem> getNewsList() {
        List<NewsListItem> ITEMS = new ArrayList<NewsListItem>();
        for(int i=0;i<15;i++) {

            ITEMS.add(new NewsListItem(
                    "Распродажа"
                    , "В Epic Games Store продолжается еженедельная раздача игр. На этот раз пользователи могут добавить в свою библиотеку сразу 2 игры! Одиночный экшен про одного из самых ужасных пришельцев Alien: Isolation, а так же экшен Hand of Fate 2.\n" +
                    "\n" +
                    "• Alien: Isolation — vk.cc/c1bZr7\n" +
                    "• Hand of Fate 2 — vk.cc/c1bZws\n" +
                    "\n" +
                    "В Steam проходит распродажа 3 франшиз от EA. Продукты серий игр Battlefield, Crysis, а так же Titanfall можно приобрести по скидкам достигающих 75%!\n" +
                    "\n" +
                    "• Распродажа EA \"FPS SALE\" — vk.cc/c0Xd2l\n" +
                    "\n" +
                    "Также в Steam проходит распродажа издателя из под чьего пера вышел изумительный градостроитель с элементами выживания Frostpunk! Наборы с играми и дополнениями можно приобрести по скидкам достигающим 85%!\n" +
                    "\n" +
                    "• Распродажа 11bit studions — vk.cc/c1htsK\n" +
                    "\n" +
                    "Все в том же самом Steam проходит распродажа издателя игр для игры в компании Jackbox Games.\n" +
                    "\n" +
                    "• Распродажа Jackbox Games — vk.cc/c1dnml\n" +
                    "\n" +
                    "Также, ко всему прочему, в Steam проходят бесплатные выходные у игры Warhammer: Vermintide 2, которую можно приобрести по скидке в 75%!\n" +
                    "\n" +
                    "• Warhammer: Vermintide 2 Free Weekend — vk.cc/8qNowv\n" +
                    "\n" +
                    "Прочие скидки Steam:\n" +
                    "\n" +
                    "• SCUM — 309 руб. (-40%) - vk.cc/83cYja\n" +
                    "• Tekken 7 — 1199 руб. (-0%) - vk.cc/bZDM0Y\n" +
                    "• Green Hell — 325 руб. (-30%) - vk.cc/8fAGcJ\n" +
                    "• Creeper World 4 — 311 руб. (-33%) - vk.cc/c1fOsx\n" +
                    "• Marvel's Avengers — 799 руб. (-60%) - vk.cc/amno0h\n" +
                    "• DEATH STRANDING — 1399 руб. (-60%) - vk.cc/c0dGHd\n" +
                    "• Rising Storm 2: Vietnam — 199 руб. (-75%) - vk.cc/845G6V"
                    , "https://sun9-7.userapi.com/impg/XREjhmFxGbydrcjFBEamQtz03QLGVAMJaJ2E8g/jWvP89NnifA.jpg?size=1280x603&quality=96&sign=16cb6ba1e5b2918ba12aee10c03d245a&type=album"));
        }
        return ITEMS;
    }
}