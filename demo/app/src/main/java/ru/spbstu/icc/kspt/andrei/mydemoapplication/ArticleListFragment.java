package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ArticleListFragment extends ListFragment {
    interface ArticleListener {
        void onArticle(String text);
    }

    private String[] items = {"Item1", "Item2", "Item3"};

    private ArticleListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ArticleListener) context;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Activity must implement ArticleListener", e);
        }
    }

    ArticleListener getListener() {
        return listener;
    }

    void setListener(ArticleListener listener) {
        this.listener = listener;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null) {
            String text = items[position];
            listener.onArticle(text);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListAdapter adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                items
        );

        setListAdapter(adapter);
    }
}
