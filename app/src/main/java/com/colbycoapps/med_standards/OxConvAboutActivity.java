package com.colbycoapps.med_standards;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class OxConvAboutActivity extends CommonCode {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ox_conv_about);

        setActionBar("About","", airForceColor);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        TextView oxConvAboutFirstTextView = findViewById(R.id.oxConvAboutFirstTextView);
        TextView oxConvAboutSecondTextView = findViewById(R.id.oxConvAboutSecondTextView);

        String textOne = "This application uses the equation:";
        String textTwo = "to calculate the inspiratory oxygen needs of patients flown at various cabin altitudes for the purpose of " +
                "aeromedical evacuations and provides a recommendation for the method of oxygen delivery.  The margin of error is +/- 1%" +
                "/n/nThe calculated results are recommendations only.  The actual amount of oxygen and delivery method should be based on the clinical status " +
                "of each individual patient.";
        oxConvAboutFirstTextView.setText(textOne);
        oxConvAboutSecondTextView.setText(textTwo);
    }

    //Allows back button to work
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
