package com.colbycoapps.med_standards;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Objects;

public class RSVActivity extends CommonCode {

    ListView rsvListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsv);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setActionBar("RSV Sample Briefings", "", airForceColor);

        rsvListView = findViewById(R.id.rsvListView);
        populateListView(airForceRsvPath, rsvListView);

        rsvListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String text = createArrayList(airForceRsvPath).get(position);
                path = airForceRsvPath + text;
                title = text.split("#")[0];
                subTitle = text.split("#")[1].substring(0, text.split("#")[1].length()-4);
                newActivity(PDFActivity.class);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
