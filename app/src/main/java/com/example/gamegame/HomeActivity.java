package com.example.gamegame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {


    private Inventory gameInventory;
    private GameTracker gameTracker;
    private ArrayList<String> items;
    private String[] inventoryList;

//    FrameLayout fragmentLayout = new FrameLayout(this);

    private TextView inventoryTextView;
    private TextView back;
    private TextView backFrom;
    private TextView question;
    private TextView empText;
    private TextView conText;

    private Button yesBut;
    private Button noBut;

    private ImageView doorCon;
    private ImageView doorEmp;

    private Fragment newFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        wireWidgets();

        setOnClickListeners();

        initializeItems();

        if(gameTracker.knifeExist() == true){
            back.setVisibility(View.GONE);
        }

        // load the home fragment when this activity is created

//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction()
//                .replace(R.id.container_home, new HomeFragment())
//                .commit();


    }

    private void initializeItems() {

         gameInventory = getIntent().getParcelableExtra(StartActivity.EXTRA_SENT_INVENTORY);


        Bundle extras = getIntent().getExtras();
        gameInventory = extras.getParcelable(StartActivity.EXTRA_SENT_INVENTORY);
        items = gameInventory.getItems();
        inventoryList = items.toArray(new String[0]);

        gameTracker = extras.getParcelable(StartActivity.EXTRA_SENT_TRACKER);
    }

    private void setOnClickListeners() {
        inventoryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInventory(inventoryList);
                updateInventory();
            }
        });

        yesBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "ERROR ERROR EROROREOEOROEOR", Toast.LENGTH_SHORT).show();
            }
        });
        noBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "ERROR ERROR EROROREOEOROEOR", Toast.LENGTH_SHORT).show();
            }
        });

        doorCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alakazam();
                newFragment = new EmployeeFragment();

                if(newFragment != null){
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.container_home, newFragment)
                            .commit();
                    //Toast.makeText(HomeActivity.this, "So this works", Toast.LENGTH_SHORT).show();




                }

            }
        });
//
//        backFrom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bringEmBack();
//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                fm.beginTransaction()
//                        .remove(ControlFragment)
//                        .commit();
//            }
//        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!(gameTracker.getCurrentState().equals("Home"))) {
                    gameTracker.backState();
                    gameTracker.backState();
                    finish();
                //}
            }
        });
        doorEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                alakazam();
                newFragment = new ControlFragment();

                if(newFragment != null){
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.container_home, newFragment)
                            .commit();
                    //Toast.makeText(HomeActivity.this, "So this works", Toast.LENGTH_SHORT).show();




                }
            }
        });
    }

    private void alakazam() {
        inventoryTextView.setVisibility(View.GONE);
        back.setVisibility(View.GONE);

        empText.setVisibility(View.GONE);
        conText.setVisibility(View.GONE);
        question.setVisibility(View.GONE);
        yesBut.setVisibility(View.GONE);
        noBut.setVisibility(View.GONE);
        doorEmp.setVisibility(View.GONE);
        doorCon.setVisibility(View.GONE);
    }

    public Inventory getGameInventory() {
        return gameInventory;
    }

    public String[] getInventoryList() {
        return inventoryList;
    }

    public void bringEmBack(){
        inventoryTextView.setVisibility(View.VISIBLE);
        back.setVisibility(View.VISIBLE);

        empText.setVisibility(View.VISIBLE);
        conText.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        yesBut.setVisibility(View.VISIBLE);
        noBut.setVisibility(View.VISIBLE);
        doorEmp.setVisibility(View.VISIBLE);
        doorCon.setVisibility(View.VISIBLE);

    }

    private void updateDisplay() {
        //if(gameTracker.getCurrentState().equals())
    }


    public void showInventory(String[] inventoryList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.inventory)
                .setItems(inventoryList, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        Dialog inventoryDialog = builder.create();
        inventoryDialog.show();
    }

    public void updateInventory() {

        inventoryList = gameInventory.getItems().toArray(new String[0]);
    }

    private void wireWidgets() {
        inventoryTextView = findViewById(R.id.textView_home_inventory);
        back = findViewById(R.id.textView_control_back);
        question = findViewById(R.id.textView_home_winOrNo);
        conText = findViewById(R.id.textView_home_controlRoom);
        empText = findViewById(R.id.textView_home_lounge);

        yesBut = findViewById(R.id.button_home_yes);
        noBut = findViewById(R.id.button_home_no);

        doorCon = findViewById(R.id.imageView_home_cautionDoor2);
        doorEmp = findViewById(R.id.imageView_home_cautiondoor);
    }
}
