package com.mobdeve.s13.g26.genshinplanner.models;

import java.util.ArrayList;

public class Plan {
    private String plan_id;
    private User plan_owner;
    private String plan_title;
    private String plan_description;
    private ArrayList<Item> plan_items;
    private ArrayList<String> plan_route;
    private int plan_resin_spent;
    private int plan_rating;

    public Plan(){
    }

    public Plan(String plan_id, User plan_owner, String plan_title, String plan_description, ArrayList<Item> plan_items, ArrayList<String> plan_route, int plan_resin_spent, int plan_rating){
        this.plan_id = plan_id;
        this.plan_owner = plan_owner;
        this.plan_title = plan_title;
        this.plan_description = plan_description;
        this.plan_items = plan_items;
        this.plan_route = plan_route;
        this.plan_resin_spent = plan_resin_spent;
        this.plan_rating = plan_rating;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public User getPlan_owner() {
        return plan_owner;
    }

    public String getPlan_title() {
        return plan_title;
    }

    public String getPlan_description() {
        return plan_description;
    }

    public ArrayList<Item> getPlan_items() {
        return plan_items;
    }

    public ArrayList<String> getPlan_route() {
        return plan_route;
    }

    public int getPlan_resin_spent() {
        return plan_resin_spent;
    }

    public int getPlan_rating() {
        return plan_rating;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public void setPlan_owner(User plan_owner) {
        this.plan_owner = plan_owner;
    }

    public void setPlan_title(String plan_title) {
        this.plan_title = plan_title;
    }

    public void setPlan_description(String plan_description) {
        this.plan_description = plan_description;
    }

    public void setPlan_items(ArrayList<Item> plan_items) {
        this.plan_items = plan_items;
    }

    public void setPlan_route(ArrayList<String> plan_route) {
        this.plan_route = plan_route;
    }

    public void setPlan_rating(int plan_rating) {
        this.plan_rating = plan_rating;
    }

    public void setPlan_resin_spent(int plan_resin_spent) {
        this.plan_resin_spent = plan_resin_spent;
    }

}
