package com.example.gamegame;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gamegame.R;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //TextView tv = rootView.findViewById(R.id.placeholder);
        //tv.setText("hi I inflated the view");

        // in a listener to close the current fragment
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction()
                .remove(this)
                .commit();

        return rootView;
    }
}