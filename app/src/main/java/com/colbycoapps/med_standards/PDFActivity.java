package com.colbycoapps.med_standards;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.util.Objects;

public class PDFActivity extends CommonCode {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset(path)
                .spacing(8)
                .password(null)
                .enableAntialiasing(true)
                .swipeHorizontal(false)
                .defaultPage(0)
                .enableSwipe(true)
                .enableDoubletap(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();

        if (menuId == R.id.navigation_airForce) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(airForceColor)));
        } else if (menuId == R.id.navigation_army) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(armyColor)));
        } else if (menuId == R.id.navigation_navy) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(navyColor)));
        } else if (menuId == R.id.navigation_dod) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(dodColor)));
        }
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(subTitle);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
