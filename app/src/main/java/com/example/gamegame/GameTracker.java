package com.example.gamegame;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.ArrayList;

public class GameTracker implements Parcelable {

    private String currentState;
    private int i = 0;
    private String prevState;

    private boolean hasKnife = false;
    private ArrayList<String> states = new ArrayList<String>();

    public GameTracker(){
        currentState = "Startup";
        states.add("Startup");
        states.add("QuitCont");
        states.add("Glitch");
        states.add("Home");
        states.add("EmployeeRoom");
        states.add("ControlRoom");
        states.add("BetaList");

    }

    public void giveKnife(){
        hasKnife = true;
    }
    public boolean knifeExist(){
        return hasKnife;
    }
    public String getCurrentState() {
        return states.get(i);
    }

    public void setCurrentState(String state) {
        prevState = currentState;
        currentState = state;
    }

    public void advanceState(){
        i++;
        currentState = states.get(i);
    }
    public void backState(){
        i--;
        currentState = states.get(i);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.currentState);
        dest.writeInt(this.i);
        dest.writeString(this.prevState);
        dest.writeByte(this.hasKnife ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.states);
    }

    protected GameTracker(Parcel in) {
        this.currentState = in.readString();
        this.i = in.readInt();
        this.prevState = in.readString();
        this.hasKnife = in.readByte() != 0;
        this.states = in.createStringArrayList();
    }

    public static final Creator<GameTracker> CREATOR = new Creator<GameTracker>() {
        @Override
        public GameTracker createFromParcel(Parcel source) {
            return new GameTracker(source);
        }

        @Override
        public GameTracker[] newArray(int size) {
            return new GameTracker[size];
        }
    };
}
