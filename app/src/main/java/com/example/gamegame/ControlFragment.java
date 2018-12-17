package com.example.gamegame;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ControlFragment extends Fragment {

    //private ImageView sneke;
    private TextView back;
    private Button lightOn;
    private Button lightOff;
    private ImageView light;
    private ImageView terminate;
    private TextView inventoryTextView;
    private ImageView keyhole;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_control, container, false);

        //TextView tv = rootView.findViewById(R.id.placeholder);
        //tv.setText("hi I inflated the view");




        wireWidgets(rootView);
        setOnClickListeners();



        return rootView;

    }

    private void wireWidgets(View rootView) {
        back = rootView.findViewById(R.id.textView_control_back);
        lightOff = rootView.findViewById(R.id.button_control_lightOff);
        lightOn = rootView.findViewById(R.id.button_control_lightOn);
        light = rootView.findViewById(R.id.imageView_control_light);

        terminate = rootView.findViewById(R.id.imageView_control_redBut);
        inventoryTextView = rootView.findViewById(R.id.textView_control_inventory);
        keyhole = rootView.findViewById(R.id.imageView_control_keyhole);
    }

    private void setOnClickListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        .remove(ControlFragment.this)
                        .commit();
                FragmentActivity parentActivity = getActivity();
                if(parentActivity instanceof HomeActivity) {
                    ((HomeActivity) parentActivity).bringEmBack();
                }
            }
        });
       lightOff.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               light.setImageResource(R.drawable.dark);
           }
       });
       lightOn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               light.setImageResource(R.drawable.light);
           }
       });

       terminate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(getActivity(), "You do not have Administrative power to do that", Toast.LENGTH_SHORT).show();
           }
       });

       keyhole.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });





       inventoryTextView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FragmentActivity parentActivity = getActivity();
               if(parentActivity instanceof HomeActivity) {
                   ((HomeActivity) parentActivity).showInventory(((HomeActivity) parentActivity).getInventoryList());
               }
           }
       });




    }


//
//    private ImageView findViewById(int imageView) {
//        return findViewById(imageView);
//    }
}