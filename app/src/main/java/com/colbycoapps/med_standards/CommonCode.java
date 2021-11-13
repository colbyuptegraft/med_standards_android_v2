package com.colbycoapps.med_standards;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CommonCode extends AppCompatActivity {

    public static String airForceMainPath = "pdfs/airForce/main/";
    public static String airForceBomcPath = "pdfs/airForce/bomc/";
    public static String airForceOtherAfisPath = "pdfs/airForce/otherAfis/";
    public static String airForceFsToolKitPath = "pdfs/airForce/fsToolKit/";
    public static String airForceRsvPath = "pdfs/airForce/rsvs/";

    public static String armyPath = "pdfs/army/";
    public static String navyPath = "pdfs/navy/";
    public static String dodPath = "pdfs/dod/";

    public static String airForceColor = "#08549C";
    public static String armyColor = "#174B2A";
    public static String navyColor = "#292F55";
    public static String dodColor = "#6a0dad";
    public static String aboutColor = "#000000";

    public static String path;
    public static String webUrl;
    public static int menuId;

    public static String title;
    public static String subTitle;

    //Extra Menu Options for Air Force Main Menu
    public static String bomcTitle =  "zzBOMC Guidance.txt";
    public static String fsToolkitTitle = "zzFlight Surgeon Toolkit.txt";
    public static String otherTitle = "zzOther AFIs.txt";

    //Extra Menu Options for FS Toolkit Menu
    public static String oxConvTitle = "zzAltitude Oxygen Converter.txt";
    public static String pracGuideTitle = "zzASAMS Practice Guidelines.txt";
    public static String pracGuideLink = "http://www.asams.org/guidelines.html";
    public static String rsvTitle = "zzRSV Sample Briefings.txt";

    //Extra Menu Options for Navy Menu
    public static String navyWikiTitle = "zzNavy Flight Surgeon Wiki.txt";
    public static String navyWikiLink = "https://knowyourchit.mywikis.net/wiki/Main_Page";

    //Array List for Selecting Items
    public ArrayList<String> createArrayList(String path) {

        String[] pdfs;
        final ArrayList<String> arrayList = new ArrayList<>();
        try {
            pdfs = getAssets().list(path.substring(0,path.length()-1));
            assert pdfs != null;
            Collections.addAll(arrayList, pdfs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    //Start New Activity
    public void newActivity(Class Activity) {
        Intent myIntent = new Intent(getApplicationContext(), Activity);
        startActivityForResult(myIntent, 0);
    }

    //Populate ListView
    public void populateListView(String filePath, ListView listView) {

        //Populate Menu with click of Tab Menu
        HashMap<String, String> hashMap = new HashMap<>();

        String[] pdfs;
        try {
            pdfs = getAssets().list(filePath.substring(0,filePath.length() - 1));
            assert pdfs != null;
            for(String i : pdfs) {
                String[] j;
                int k;
                if (i.substring(0,2).matches("zz")) {
                    k = i.length();
                    hashMap.put(i.substring(0, k - 4), "");
                } else {
                    j = i.split("#");
                    k = j[1].length();
                    hashMap.put(j[0], j[1].substring(0, k - 4));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        List< HashMap < String, String >> listItems = new ArrayList<>();
        for (Object o : hashMap.entrySet()) {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry) o;
            resultsMap.put("title", pair.getKey().toString());
            resultsMap.put("subTitle", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        Collections.sort(listItems, mapComparator); //Sort List

        for (HashMap<String, String> i : listItems) {
            String j = i.get("title").substring(0, 2);
            String k;
            int l;
            if(j.matches("zz")) {
                l = i.get("title").length();
                k = i.get("title").substring(2, l);
                i.put("title", k);
            }

        }

        //Set adapter & visibility
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), listItems, R.layout.list_item,
                new String[]{"title", "subTitle"},
                new int[]{R.id.mainText, R.id.subText});

        listView.setAdapter(adapter);
        listView.setVisibility(View.VISIBLE);
    }

    //Function to sort menu alphabetically
    public Comparator<Map<String, String>> mapComparator = new Comparator<Map<String, String>>() {
        public int compare(Map<String, String> m1, Map<String, String> m2) {
            return m1.get(m1.keySet().toArray()[1]).compareTo(m2.get(m2.keySet().toArray()[1]));
        }
    };

    //Configure Action Bar
    public void setActionBar(String title, String subTitle, String color) {
        //Set action bar title
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        getSupportActionBar().setSubtitle(subTitle);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(color)));
    }

}
