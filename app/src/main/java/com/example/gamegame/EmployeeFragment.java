package com.example.gamegame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EmployeeFragment extends Fragment {

    //private ImageView sneke;
    private TextView back;

    private Button kill;
    private Button kill1;

    private ImageView employee;

    private ImageView terminate;
    private TextView inventoryTextView;
    private ImageView keyhole;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_employee, container, false);

        //TextView tv = rootView.findViewById(R.id.placeholder);
        //tv.setText("hi I inflated the view");


        wireWidgets(rootView);
        setOnClickListeners();


        return rootView;

    }

    private void setOnClickListeners() {
        kill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killEmployee();
            }
        });
        kill1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killEmployee();
            }
        });
    }

    private void killEmployee() {
        employee.setVisibility(View.GONE);

    }

    private void wireWidgets(View rootView) {
        kill = rootView.findViewById(R.id.button_employee_kill);
        kill1 = rootView.findViewById(R.id.button_employee_kill1);
        employee = rootView.findViewById(R.id.imageView_employee_employee);
    }

}
