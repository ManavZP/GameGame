package com.example.gamegame;

import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    private ImageView coin;
    private ImageView snek;

    public static final String EXTRA_SENT_INVENTORY = "theinventory";
    public static final String EXTRA_SENT_TRACKER = "thetracker";



    //gggggg

    private Inventory inventoryOne = new Inventory();
    private ArrayList<String> items = inventoryOne.getItems();
    private String[] inventoryList = items.toArray(new String[0]);

    //gggggg



    private TextView insert;
    private TextView welcome;
    private TextView snekTalk;
    private TextView back;

    private Button continueBut;
    private Button quitBut;

    private Animation slideUp;
    private Animation slideDown;
    private Animation blink;

    private ConstraintLayout ll;

    private GameTracker gameTracker = new GameTracker();

    private TextView quitCont;
    private TextView inventoryTextView;

    private Inventory gameInventory = new Inventory();
    //private String[] inventoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        wireWidgets();
        setOnClickListeners();
        makeAnimations();
        updateInventory();

//       Parceleable code
//        Intent i = new Intent();
//        i.putExtra("Inventory", gameInventory);
//          when receiving in the other activity
//        i.getParcelableExtra()

    }

    private void makeAnimations() {
        slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidein);
        slideUp = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide);
        blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movein);
    }

    private void setOnClickListeners() {
        coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this, "You have lost: 1 Quarter", Toast.LENGTH_LONG).show();
                gameInventory.removeItem("1 Quarter");
                updateInventory();
                gameTracker.advanceState();
                welcome.startAnimation(slideUp);
                updateDisplay();
            }
        });
        continueBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueBut.setEnabled(false);
                gameTracker.advanceState();
                updateDisplay();
                gameTracker.giveKnife();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(gameTracker.getCurrentState().equals("QuitCont"))) {
                    gameTracker.backState();
                    updateDisplay();
                }
            }
        });

        inventoryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInventory(inventoryList);
                updateInventory();
            }
        });

        quitBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gameTracker.advanceState();
                gameTracker.advanceState();


                Intent intentNext = new Intent(StartActivity.this, HomeActivity.class);
                Bundle extras = new Bundle();

                extras.putParcelable(EXTRA_SENT_INVENTORY, gameInventory);
                extras.putParcelable(EXTRA_SENT_TRACKER, gameTracker);
                intentNext.putExtras(extras);
                startActivity(intentNext);

            }
        });

    }


    private void showInventory(String[] inventoryList) {
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


    private void alakazam(){

        coin.setVisibility(View.GONE);
        insert.setVisibility(View.GONE);
        welcome.setVisibility(View.GONE);
        snek.setVisibility(View.GONE);
        snekTalk.setVisibility(View.GONE);
        inventoryTextView.setVisibility(View.GONE);
        continueBut.setVisibility(View.GONE);
        quitBut.setVisibility(View.GONE);
        quitCont.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
        snek.clearAnimation();


    }

    private void updateDisplay(){

        if(gameTracker.getCurrentState().equals("QuitCont")) {
            alakazam();
            quitCont.setText("Welcome to the Game Game. Press \"Quit\" to Continue");
            quitCont.setVisibility(View.VISIBLE);
            inventoryTextView.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            quitCont.startAnimation(slideDown);
            quitBut.setVisibility(View.VISIBLE);
            continueBut.setVisibility(View.VISIBLE);
        }

        else if(gameTracker.getCurrentState().equals("Glitch")){
            alakazam();
            inventoryTextView.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            snek.setVisibility(View.VISIBLE);
            snek.startAnimation(blink);
            snekTalk.setVisibility(View.VISIBLE);
            quitCont.setText("");
            Toast.makeText(this, "You have acquired: 1 Danger Knife", Toast.LENGTH_LONG).show();
            gameInventory.addItem("1 Danger Knife");
            updateInventory();
        }
    }

    private void wireWidgets() {
        coin = findViewById(R.id.imageView_start_coin);
        insert = findViewById(R.id.textView_start_insert);
        welcome = findViewById(R.id.textView_start_welcome);
        quitCont = findViewById(R.id.textView_start_quitcont);
        continueBut = findViewById(R.id.button_start_continue);
        quitBut = findViewById(R.id.button_start_quit);
        snek = findViewById(R.id.imageView_start_noodle);
        snekTalk = findViewById(R.id.textView_start_noodle);
        inventoryTextView = findViewById(R.id.textView_start_inventory);
        back = findViewById(R.id.textView_start_back);
        ll = findViewById(R.id.ll);

    }
}
