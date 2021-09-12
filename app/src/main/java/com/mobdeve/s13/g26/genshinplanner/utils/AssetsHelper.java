package com.mobdeve.s13.g26.genshinplanner.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;


import com.google.gson.JsonParser;
import com.mobdeve.s13.g26.genshinplanner.R;
import com.mobdeve.s13.g26.genshinplanner.models.Character;
import com.mobdeve.s13.g26.genshinplanner.models.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class AssetsHelper {

    private Context context;
    private AssetManager manager;

    public AssetsHelper(Context cx) {
        this.manager = cx.getAssets();
        this.context = cx;
    }

    /**
     * Read one individual JSON from file
     * @param path path to JSON File starting from assets dir
     * @return JSONObject of JSON in path
     */
    private JSONArray readJSON(String path) {
        String json = "";

        try {
            InputStream iStream = this.manager.open(path);
            int size = iStream.available();
            byte[] buffer = new byte[size];
            iStream.read(buffer);
            iStream.close();
            json = new String(buffer, "UTF-8");
            json = json.replaceAll("\"source\"", "\"sources\"");
            JSONObject jsonObj = new JSONObject(json);
            JSONArray jsonArray = new JSONArray();

            Iterator<String> keys = jsonObj.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                jsonArray.put(jsonObj.get(key));

            }
            //Log.d("jsonArray:", jsonArray.toString());
            return jsonArray;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private JSONObject readJSONObject(String path){
        String json = "";

        try {
            InputStream iStream = this.manager.open(path);
            int size = iStream.available();
            byte[] buffer = new byte[size];
            iStream.read(buffer);
            iStream.close();
            json = new String(buffer, "UTF-8");
            JSONObject jsonObj = new JSONObject(json);
            return jsonObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Character> getCharacterAssets() throws JSONException{
        ArrayList<Character> tempList = new ArrayList<>();
        String basePath = "data/characters/";
        String[] paths = {"albedo", "amber", "ayaka", "barbara", "beidou",
                          "bennett", "chongyun", "diluc", "diona", "eula",
                          "fischl", "ganyu", "hu_tao", "jean", "kaeya",
                          "kazuha", "keqing", "klee", "lisa", "mona",
                          "ningguang", "noelle", "qiqi", "razor", "rosaria",
                          "sucrose", "tartaglia", "traveler_anemo", "traveler_geo", "venti",
                          "xiangling", "xiao", "xingqiu", "xinyan", "yanfei", "zhongli"};
        Log.d("TAGG", "INSIDE get character assets");
        for (String path : paths) {
            tempList.add(getDetailsfromDir(basePath, path));
        }
        return tempList;
    }

    private Character getDetailsfromDir(String basePath, String character_path) throws JSONException {
        String name = "";
        String temp = "";
        String weapon = "";
        int rarity = 0;
        int weap_type = 0, element = 0;
            JSONObject jsonObject = readJSONObject(basePath + character_path + "/en.json");
            Log.d("JSONObject", jsonObject.toString());
            name = (String) jsonObject.get("name");
            temp = (String) jsonObject.get("vision");
            weapon = (String) jsonObject.get("weapon");
            rarity = (int) jsonObject.get("rarity");

            Log.d("Contents-JSON","Name "+ name);
            Log.d("Contents-JSON","Temp " + temp);
            Log.d("Contents-JSON","Weapon"  + weapon);
            if (temp.contains("Electro")) {
                element = 1;
            } else if (temp.contains("Pyro")) {
                element = 2;
            } else if (temp.contains("Hydro")) {
                element = 3;
            } else if (temp.contains("Dendro")) {
                element = 4;
            } else if (temp.contains("Geo")) {
                element = 5;
            } else if (temp.contains("Cryo")) {
                element = 6;
            }

            if (weapon.contains("Polearm")) {
                weap_type = 1;
            }
            if (weapon.contains("Sword")) {
                weap_type = 2;
            }
            if (weapon.contains("Claymore")) {
                weap_type = 3;
            }
            if (weapon.contains("Bow")) {
                weap_type = 4;
            }
            if (weapon.contains("Catalyst")) {
                weap_type = 5;
            }
            int image = getImageResources(character_path);
            ArrayList<Integer> ascension_mats = new ArrayList<>();
            ArrayList<Integer> talent_mats = new ArrayList<>();
            return new Character(image, name, weap_type, element, rarity, ascension_mats, talent_mats);
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
        types = new String[]{"character ascension"};
        typesList = new ArrayList<String>(Arrays.asList(types));
        tempList.addAll(getMaterialsFromDir(basePath + paths[1], typesList));


        //get stuff from common-ascension
        types = new String[]{"common ascension"};
        typesList = new ArrayList<String>(Arrays.asList(types));
        tempList.addAll(getMaterialsFromDir(basePath + paths[3], typesList));

        //get stuff from local-specialties
        types = new String[]{"local specialties"};
        typesList = new ArrayList<String>(Arrays.asList(types));
        tempList.addAll(getMaterialsFromDir(basePath + paths[5], typesList));

        //get stuff from talent-books
        types = new String[]{"talent book"};
        typesList = new ArrayList<String>(Arrays.asList(types));
        tempList.addAll(getMaterialsFromDir(basePath + paths[6], typesList));

        //get stuff from talent-boss
        types = new String[]{"talent boss"};
        typesList = new ArrayList<String>(Arrays.asList(types));
        tempList.addAll(getMaterialsFromDir(basePath + paths[7], typesList));


        for(Item item: tempList) {
            String obtains = null;
            if(item.getItem_obtain_ways() != null)
                obtains = Arrays.toString(item.getItem_obtain_ways().toArray());
            //Log.d("TEMPLIST " + item.getItem_name() + ": ", Arrays.toString(item.getItem_types().toArray()) + "\n" + obtains);
        }

        return tempList;
    }

    /**
     * Get assets from boss-material dir
     * @return return arraylist of boss-materials
     */
    private ArrayList<Item> getMaterialsFromDir(String basePath, ArrayList<String> typesList) throws JSONException {
        ArrayList<Item> tempList = new ArrayList<>();
        JSONArray material = readJSON(basePath + "/en.json");

        if(material.get(0) instanceof JSONArray)
            material = (JSONArray) material.get(0);
        for(int i = 0; i < material.length(); i++) { //save to temp array
            JSONObject item = material.getJSONObject(i);
            Iterator<String> keys = item.keys();

            //get item sources
            String name = null;
            String obtainString = null;
            String[] obtains = null;
            ArrayList<String> obtainList = null;

            while (keys.hasNext()) {
                String key = keys.next();
                if(key.equalsIgnoreCase("name"))
                    name = item.getString("name");
                else if(key.equalsIgnoreCase("sources")) {
                    obtainString = item.get("sources").toString();
                    obtains = obtainString.split(",");
                    obtainList = new ArrayList<String>(Arrays.asList(obtains));
                }
                else if(item.get(key) instanceof JSONArray) {
                    JSONArray array = (JSONArray) item.get(key);
                    if(array.get(0) instanceof JSONObject) {
                        JSONObject object = (JSONObject) array.get(0);
                        Iterator<String> innerKeys = object.keys();
                        while (innerKeys.hasNext()) {
                            key = innerKeys.next();
                            if (key.equalsIgnoreCase("name"))
                                name = object.getString("name");
                            if (key.equalsIgnoreCase("sources")) {
                                obtainString = object.get("sources").toString();
                                obtains = obtainString.split(",");
                                obtainList = new ArrayList<String>(Arrays.asList(obtains));
                            }
                        }
                    }
                }
                if(name != null && obtainList != null)
                    continue;
            }

            if(name != null) {
                String filename = "item_" + typesList.get(0).replace(" ", "_") +
                        "_" + name.toLowerCase().replace(" ", "_");
                filename = filename.replace("'", "");

                int image = getDrawableResource(filename);

                if(image > 0)   //images with 0 are tier 1 versions of some items we won't use
                    //save to list
                    tempList.add(new Item(image, name, typesList, obtainList));
            }
        }

        return tempList;
    }

    private int getDrawableResource(String name) {
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(name, "drawable",
                context.getPackageName());
        return resourceId;
    }

    private int getImageResources(String main) {
        Field[] fields = R.drawable.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                String filename = field.getName();

                if (filename.contains("characters_") && filename.contains(main.toLowerCase())) //remove filenames with _
                    return field.getInt(null);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return R.drawable.baal;
    }

}
