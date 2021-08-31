package com.mobdeve.s13.g26.genshinplanner.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Item {
    private int item_img;
    private String item_name;
    private ArrayList<String> item_types;
    private ArrayList<String> item_obtain_ways;


    /**
     * Temporary constructor for asset testing
     *
     */
    public Item (String item_name, ArrayList<String> item_types,ArrayList<String> item_obtain_ways) {
        this.item_name = item_name;
        this.item_types = item_types;
        this.item_obtain_ways = item_obtain_ways;
    }

    public Item (int item_img, String item_name, ArrayList<String> item_types, ArrayList<String> item_obtain_ways){
        this.item_img = item_img;
        this.item_name = item_name;
        this.item_types = item_types;
        this.item_obtain_ways = item_obtain_ways;
    }

    public int getItem_img(){
        return  item_img;
    }

    public String getItem_name(){
        return item_name;
    }

    public ArrayList<String> getItem_types(){
        return item_types;
    }

    public ArrayList<String> getItem_obtain_ways(){
        return item_obtain_ways;
    }

    public void setItem_img(int item_img){
        this.item_img = item_img;
    }

    public void setItem_name(String item_name){
        this.item_name = item_name;
    }

    public void setItem_types(ArrayList<String> item_types) {
        this.item_types = item_types;
    }

    public void setItem_obtain_ways(ArrayList<String> item_obtain_ways) {
        this.item_obtain_ways = item_obtain_ways;
    }
}
