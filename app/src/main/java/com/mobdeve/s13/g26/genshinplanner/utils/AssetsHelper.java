package com.mobdeve.s13.g26.genshinplanner.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;


import com.mobdeve.s13.g26.genshinplanner.models.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class AssetsHelper {

    private AssetManager manager;

    public AssetsHelper(Context cx) {
        this.manager = cx.getAssets();
        try {
            getItemAssets();
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read one individual JSON from file
     * @param path path to JSON File starting from assets dir
     * @return JSONObject of JSON in path
     */
    private JSONArray readJSON(String path) {
        String json = "";
        try{
            InputStream iStream = this.manager.open(path);
            int size = iStream.available();
            byte[] buffer = new byte[size];
            iStream.read(buffer);
            iStream.close();
            json = new String(buffer, "UTF-8");
            json = json.replaceAll("\"source\"", "\"sources\"");
            JSONObject jsonObj = new JSONObject(json);
            Log.d("OBJ:", jsonObj.toString());
            JSONArray jsonArray;

            if(jsonObj.has("items"))
                jsonArray = jsonObj.getJSONArray("items");
            else
                do {
                    JSONArray names = jsonObj.names();
                    jsonArray = new JSONArray();

                    for (int i = 0; i < names.length(); i++) {
                        String name = names.get(i).toString();
                        jsonArray.put(jsonObj.get(name));
                    }
                    jsonObj = jsonArray.getJSONObject(0);
                } while (jsonArray.getJSONObject(0).isNull("sources"));


            return jsonArray;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Item> getItemAssets() throws JSONException {
        ArrayList<Item> tempList = new ArrayList<>();

        String basePath = "data/materials/";
        String[] paths = {"boss-material",
                        "character-ascension",
                        "character-experience",
                        "common-ascension",
                        "cooking-ingredients",
                        "local-specialties",
                        "talent-book",
                        "talent-boss",
                        "weapon-ascension",
                        "weapon-experience"};

        //get stuff from boss-material
        String[] types = {"boss material",  "character ascension material"};
        ArrayList<String> typesList = new ArrayList<String>(Arrays.asList(types));
        tempList.addAll(getMaterialsFromDir(basePath + paths[0], typesList));

        //get stuff from character-ascension
        types = new String[]{"character ascension material"};
        typesList = new ArrayList<String>(Arrays.asList(types));
        tempList.addAll(getMaterialsFromDir(basePath + paths[1], typesList));

        //get stuff from character-experience
        types = new String[]{"character experience material"};
        typesList = new ArrayList<String>(Arrays.asList(types));
        tempList.addAll(getMaterialsFromDir(basePath + paths[2], typesList));

        //get stuff from common-ascension
        types = new String[]{"character ascension material"};
        typesList = new ArrayList<String>(Arrays.asList(types));
        //tempList.addAll(getMaterialsFromDir(basePath + paths[3], typesList));

        //get stuff from cooking-ingredients
        types = new String[]{"cooking ingredients"};
        typesList = new ArrayList<String>(Arrays.asList(types));
        tempList.addAll(getMaterialsFromDir(basePath + paths[4], typesList));

        //get stuff from local-specialties
        types = new String[]{"local specialties"};
        typesList = new ArrayList<String>(Arrays.asList(types));
        //tempList.addAll(getMaterialsFromDir(basePath + paths[5], typesList));

        //get stuff from talent-books
        types = new String[]{"talent book"};
        typesList = new ArrayList<String>(Arrays.asList(types));
        //tempList.addAll(getMaterialsFromDir(basePath + paths[6], typesList));

        //get stuff from talent-boss
        types = new String[]{"talent material"};
        typesList = new ArrayList<String>(Arrays.asList(types));
        //tempList.addAll(getMaterialsFromDir(basePath + paths[7], typesList));


        for(Item item: tempList) {
            String obtains = null;
            if(item.getItem_obtain_ways() != null)
                obtains = Arrays.toString(item.getItem_obtain_ways().toArray());
            Log.d("TEMPLIST " + item.getItem_name() + ": ", Arrays.toString(item.getItem_types().toArray()) + "\n" + obtains);
        }

        return tempList;
    }

    /**
     * Get assets from boss-material dir
     * @return return arraylist of boss-materials
     */
    private ArrayList<Item> getMaterialsFromDir(String basePath, ArrayList<String> typesList) throws JSONException {
        ArrayList<Item> tempList = new ArrayList<>();
        JSONArray bossMaterial = readJSON(basePath + "/en.json");

        for(int i = 0; i < bossMaterial.length(); i++) { //save to temp array
            JSONObject item = bossMaterial.getJSONObject(i);

            //get item sources

            String obtainString = null;
            String[] obtains = null;
            ArrayList<String> obtainList = null;

            if(item.has("sources")) {
                obtainString = item.get("sources").toString();
                obtains = obtainString.split(",");
                obtainList = new ArrayList<String>(Arrays.asList(obtains));
            }

            //save to list
            tempList.add(new Item(item.get("name").toString(), typesList, obtainList));
        }

        return tempList;
    }
}
