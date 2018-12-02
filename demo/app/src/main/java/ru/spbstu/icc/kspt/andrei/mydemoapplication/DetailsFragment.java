package ru.spbstu.icc.kspt.andrei.mydemoapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    public static final String KEY_DETAILS_TEXT = "ru.spbstu.icc.kspt.andrei.mydemoapplication.detailsText";

    public DetailsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        setText(args.getString(KEY_DETAILS_TEXT));
    }

    public void setText(String text) {
        TextView detailsText = getActivity().findViewById(R.id.details_text);
        if (detailsText != null) {
            detailsText.setText(text);
        }
    }

    public static DetailsFragment newInstance(String text) {
        DetailsFragment res = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(KEY_DETAILS_TEXT, text);
        res.setArguments(args);
        return res;
    }
}
