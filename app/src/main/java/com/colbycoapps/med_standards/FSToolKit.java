package com.colbycoapps.med_standards;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Objects;

public class FSToolKit extends CommonCode {

    ListView fsToolKitListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fstool_kit);

        setActionBar("FS Toolkit", "", airForceColor);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        fsToolKitListView = findViewById(R.id.fsToolKitListView);
        populateListView(airForceFsToolKitPath, fsToolKitListView);

        //Move to PDFViewer & set action bar accordingly
        fsToolKitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String text = createArrayList(airForceFsToolKitPath).get(position);
                if (text.matches(pracGuideTitle)) {
                    webUrl = pracGuideLink;
                    newActivity(WebViewActivity.class);
                } else if (text.matches(oxConvTitle)) {
                    newActivity(OxConvActivity.class);
                } else if (text.matches(rsvTitle)) {
                    newActivity(RSVActivity.class);
                } else {
                    path = airForceFsToolKitPath + text;
                    title = text.split("#")[0];
                    subTitle = text.split("#")[1].substring(0, text.split("#")[1].length()-4);
                    newActivity(PDFActivity.class);
                }
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
