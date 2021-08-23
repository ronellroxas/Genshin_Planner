package com.mobdeve.s13.g26.genshinplanner.models;

import java.util.ArrayList;

public class Character {
    private int char_image;
    private String char_name;
    private int weap_type; //1 = Polearm, 2 = Sword, 3 = Great Sword, 4 = Bow, 5 = Catalyst
    private int element;  // 1 = Electro, 2 = Pyro, 3 = Hydro, 4 = Dendro, 5 = Geo, 6, Cryo
    private int rarity;
    private ArrayList<Integer> ascension_mats;
    private ArrayList<Integer> talent_mats;

    public Character(int char_image, String char_name, int weap_type, int element, int rarity, ArrayList<Integer> ascension_mats, ArrayList<Integer> talent_mats){
        this.char_image = char_image;
        this.char_name = char_name;
        this.weap_type = weap_type;
        this.element = element;
        this.rarity = rarity;
        this.ascension_mats = ascension_mats;
        this.talent_mats = talent_mats;
    }

    public int getChar_image() {
        return char_image;
    }

    public String getChar_name() {
        return char_name;
    }

    public int getWeap_type() {
        return weap_type;
    }

    public int getElement() {
        return element;
    }

    public int getRarity() {
        return rarity;
    }

    public ArrayList<Integer> getAscension_mats() {
        return ascension_mats;
    }

    public ArrayList<Integer> getTalent_mats(){
        return  talent_mats;
    }

    public void setChar_image(int char_image){
        this.char_image = char_image;
    }

    public void setChar_name(String char_name){
        this.char_name = char_name;
    }

    public void setWeap_type(int weap_type) {
        this.weap_type = weap_type;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void setAscension_mats(ArrayList<Integer> ascension_mats){
        this.ascension_mats = ascension_mats;
    }

    public void setTalent_mats(ArrayList<Integer> talent_mats){
        this.talent_mats = talent_mats;
    }
}
