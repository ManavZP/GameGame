package com.example.gamegame;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Inventory implements Parcelable {

    private ArrayList<String> items = new ArrayList<String>();
    private int weight;
    private int value;

    public Inventory(){
        items.add("1 Quarter");
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void addItem(String itemToAdd){
        items.add(itemToAdd);
    }

    public void removeItem(String itemToRemove){
        for(int i = 0 ; i < items.size() ; i++){
            if(items.get(i).equals(itemToRemove)){
                items.remove(i);
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.items);
        dest.writeInt(this.weight);
        dest.writeInt(this.value);

    }

    protected Inventory(Parcel in) {
        this.items = in.createStringArrayList();
        this.weight = in.readInt();
        this.value = in.readInt();
    }

    public static final Creator<Inventory> CREATOR = new Creator<Inventory>() {
        @Override
        public Inventory createFromParcel(Parcel source) {
            return new Inventory(source);
        }

        @Override
        public Inventory[] newArray(int size) {
            return new Inventory[size];
        }
    };
}
