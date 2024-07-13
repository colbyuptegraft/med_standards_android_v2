package com.colbycoapps.med_standards;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends CommonCode {

    ListView mainMenuListView;
    TextView aboutTextView;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_airForce:
                    populateListView(airForceMainPath, mainMenuListView);
                    setActionBar("Air Force Menu", "", airForceColor);
                    aboutTextView.setVisibility(View.INVISIBLE);
                    menuId = R.id.navigation_airForce;
                    return true;
                case R.id.navigation_army:
                    populateListView(armyPath, mainMenuListView);
                    setActionBar("Army Menu", "", armyColor);
                    aboutTextView.setVisibility(View.INVISIBLE);
                    menuId = R.id.navigation_army;
                    return true;
                case R.id.navigation_navy:
                    populateListView(navyPath, mainMenuListView);
                    setActionBar("Navy Menu", "", navyColor);
                    aboutTextView.setVisibility(View.INVISIBLE);
                    menuId = R.id.navigation_navy;
                    return true;
                case R.id.navigation_dod:
                    populateListView(dodPath, mainMenuListView);
                    setActionBar("DoD/MHS Menu","", dodColor);
                    aboutTextView.setVisibility(View.INVISIBLE);
                    menuId = R.id.navigation_dod;
                    return true;
                case R.id.navigation_about:
                    mainMenuListView.setVisibility(View.INVISIBLE);
                    aboutTextView.setVisibility(View.VISIBLE);
                    setActionBar("About", "", aboutColor);
                    menuId = R.id.navigation_about;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find Resources
        mainMenuListView = findViewById(R.id.mainMenuListView);
        aboutTextView = findViewById(R.id.aboutTextView);

        populateListView(airForceMainPath, mainMenuListView);
        menuId = R.id.navigation_airForce;
        setActionBar("Air Force Menu", "", airForceColor);

        //Set Navigation View
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Populate TextView and set it to invisible initially
        String message = "This application presents the medical standards for special duty personnel of the United States Air Force, Army, and Navy as well as" +
                " some other useful tools and information for Aerospace Medicine Professionals.\n\nAll AFIs, ARs, and Navy documents were screening and approved for" +
                " inclusion in this application by Air Force Public Affairs.\n\nUpdates with new document versions will occur once every 4-6 weeks. For questions," +
                " concerns, and/or suggestions, please email info@doc-apps.com\n\nNote: Effective 30 Jun 2025, marking 10 years of independently creating & maintaining" +
                " this app, I will no longer be able to continue this service." +
                "  If you have coding experience and are interested in taking ownership of Med Standards, please email the above address.";
        aboutTextView.setText(message);
        aboutTextView.setVisibility(View.INVISIBLE);

        mainMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String text;
                switch (menuId) {
                    case R.id.navigation_airForce:
                        text = createArrayList(airForceMainPath).get(position);
                        if (text.matches(bomcTitle)) {
                            newActivity(BOMCActivity.class);
                            return;
                        } else if (text.matches(fsToolkitTitle)) {
                            newActivity(FSToolKit.class);
                            return;
                        } else if (text.matches(otherTitle)) {
                            newActivity(OtherAFIActivity.class);
                            return;
                        } else {
                            path = airForceMainPath + text;
                            title = text.split("#")[0];
                            subTitle = text.split("#")[1].substring(0, text.split("#")[1].length() - 4);
                        }
                        break;
                    case R.id.navigation_army:
                        text = createArrayList(armyPath).get(position);
                        path = armyPath + text;
                        title = text.split("#")[0];
                        subTitle = text.split("#")[1].substring(0, text.split("#")[1].length() - 4);
                        break;
                    case R.id.navigation_dod:
                        text = createArrayList(dodPath).get(position);
                        path = dodPath + text;
                        title = text.split("#")[0];
                        subTitle = text.split("#")[1].substring(0, text.split("#")[1].length() - 4);
                        break;
                    case R.id.navigation_navy:
                        text = createArrayList(navyPath).get(position);
                        if (text.matches(navyWikiTitle)) {
                            webUrl = navyWikiLink;
                            newActivity(WebViewActivity.class);
                            return;
                        } else {
                            path = navyPath + text;
                            title = text.split("#")[0];
                            subTitle = text.split("#")[1].substring(0, text.split("#")[1].length() - 4);
                        }
                        break;
                }
                newActivity(PDFActivity.class);
            }
        });
    }

}
