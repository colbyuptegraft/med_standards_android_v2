package com.colbycoapps.med_standards;

import android.os.Bundle;
import android.widget.ListView;

import java.util.Objects;

public class BOMCActivity extends CommonCode {

    ListView bomcListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bomc);

        setActionBar("BOMC Guidance", "", airForceColor);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        bomcListView = findViewById(R.id.bomcListView);
        populateListView(airForceBomcPath, bomcListView);

        bomcListView.setOnItemClickListener((adapterView, view, position, id) -> {
            String text = createArrayList(airForceBomcPath).get(position);
            path = airForceBomcPath + text;
            title = text.split("#")[0];
            subTitle = text.split("#")[1].substring(0, text.split("#")[1].length()-4);
            newActivity(PDFActivity.class);
        });
    }

    //Allows back button to work
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
