package com.udacity.sandwichclub.utils;

import android.util.Log;
import android.widget.Toast;


import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.makeText;




public class JsonUtils {




    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject2 = jsonObject.getJSONObject("name");
            JSONArray jsonArray = jsonObject2.getJSONArray("alsoKnownAs");
            List<String> stringList = new ArrayList<>();
            for(int i =0 ; i < jsonArray.length(); i++){
                stringList.add(jsonArray.get(i).toString());

            }

            JSONArray jsonArray1 = jsonObject.getJSONArray("ingredients");
            List<String> stringList1 = new ArrayList<>();

            for(int i = 0; i < jsonArray1.length(); i++){
                stringList1.add(jsonArray1.get(i).toString());
        }


        sandwich = new Sandwich(jsonObject2.getString("mainName"), stringList, jsonObject.getString("placeOfOrigin"),jsonObject.getString("description"), jsonObject.getString("image"), stringList1);
        Log.d("Creation", sandwich.getDescription());


        } catch (JSONException e) {
            e.printStackTrace();
        }







        return sandwich;
    }

}
