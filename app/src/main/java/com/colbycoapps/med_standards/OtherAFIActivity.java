package com.colbycoapps.med_standards;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Objects;


public class OtherAFIActivity extends CommonCode {

    ListView otherAfiListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_afi);

        setActionBar("Other AFIs", "", airForceColor);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        otherAfiListView = findViewById(R.id.otherAfiListView);
        populateListView(airForceOtherAfisPath, otherAfiListView);

        otherAfiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String text = createArrayList(airForceOtherAfisPath).get(position);
                path = airForceOtherAfisPath + text;
                title = text.split("#")[0];
                subTitle = text.split("#")[1].substring(0, text.split("#")[1].length()-4);
                newActivity(PDFActivity.class);
            }
        });
    }

    //Allows back button to work
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
