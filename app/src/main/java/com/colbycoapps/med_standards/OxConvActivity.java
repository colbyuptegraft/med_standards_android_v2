package com.colbycoapps.med_standards;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import static java.lang.Math.exp;

public class OxConvActivity extends CommonCode {

    TextView startingAltitudeEditText;
    TextView cabinAltitudeEditText;
    TextView initialFiO2EditText;
    TextView outputTextView;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ox_conv);

        setActionBar("Altitude O2 Converter", "", airForceColor);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        startingAltitudeEditText = findViewById(R.id.startingElevationEditText);
        cabinAltitudeEditText = findViewById(R.id.cabinAltitudeEditText);
        initialFiO2EditText = findViewById(R.id.initialFiO2EditText);
        outputTextView = findViewById(R.id.outputTextView);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(v -> calculate());

    }

    public void calculate() {

        String message;

        String startingAltitudeText = startingAltitudeEditText.getText().toString();
        String cabinAltitudeText = cabinAltitudeEditText.getText().toString();
        String initialFiO2Text = initialFiO2EditText.getText().toString();

        if (startingAltitudeText.matches("") || cabinAltitudeText.matches("")|| initialFiO2Text.matches("")) {

            createToast("Please enter whole numbers.");

        } else {

            Double startingAltitude = Double.parseDouble(startingAltitudeText);
            Double cabinAltitude = Double.parseDouble(cabinAltitudeText);
            double initialFiO2 = Double.parseDouble(initialFiO2Text);

            if (startingAltitude.equals(cabinAltitude)) {

                createToast("Starting elevation = cabin altitude?");

            } else if (initialFiO2 < 21) {

                createToast("Initial FiO2 cannot be < 21%.");

            } else if (cabinAltitude < 0) {

                createToast("Cabin altitude cannot be < 0 feet.");

            } else {

                Double m;
                m = 0.3048;

                double startingAltitudeConversion = startingAltitude * m;
                double cabinAltitudeConversion = cabinAltitude * m;
                double initialFiO2Percent = initialFiO2 / 100;

                double denominator = 8.31447 * 288.15;

                double topNum;
                topNum = 9.80665 * 0.0289644 * startingAltitudeConversion;

                double bottomNum;
                bottomNum = 9.80665 * 0.0289644 * cabinAltitudeConversion;

                double topExp;
                topExp = exp(-1 * (topNum / denominator));

                double bottomExp = exp(-1 * (bottomNum / denominator));

                double finalFiO2 = ((initialFiO2Percent * topExp) / bottomExp);

                int finalResult = (int) Math.round(finalFiO2 * 100);

                if (finalResult > 0) {
                    String textOne = "Equivalent FiO2: " + finalResult + "%";
                    String textTwo;

                    if (finalResult <= 21) {
                        textTwo = "Supplemental O2 Not Required";
                    } else if (finalResult <= 24) {
                        textTwo = "Nasal Cannula at 1 L/Min";
                    } else if (finalResult <= 28) {
                        textTwo = "Nasal Cannula at 1-2 L/Min";
                    } else if (finalResult <= 32) {
                        textTwo = "Nasal Cannula at 2-3 L/Min";
                    } else if (finalResult <= 36) {
                        textTwo = "Nasal Cannula at 3-4 L/Min";
                    } else if (finalResult <= 40) {
                        textTwo = "Nasal Cannula at 4-5 L/Min";
                    } else if (finalResult <= 44) {
                        textTwo = "Nasal Cannula at 5-6 L/Min";
                    } else if (finalResult <= 48) {
                        textTwo = "Simple Face Mask at 6-7 L/Min";
                    } else if (finalResult <= 52) {
                        textTwo = "Simple Face Mask at 7-8 L/Min";
                    } else if (finalResult <= 56) {
                        textTwo = "Simple Face Mask at 8-9 L/Min";
                    } else if (finalResult <= 60) {
                        textTwo = "Simple Face Mask at 9-10 L/Min";
                    } else if (finalResult <= 70) {
                        textTwo = "Non-Rebreather Mask at 6-7 L/Min";
                    } else if (finalResult <= 80) {
                        textTwo = "Non-Rebreather Mask at 7-8 L/Min";
                    } else if (finalResult <= 90) {
                        textTwo = "Non-Rebreather Mask at 8-9 L/Min";
                    } else if (finalResult <= 95) {
                        textTwo = "Non-Rebreather Mask at 9-10 L/Min";
                    } else if (finalResult <= 100) {
                        textTwo = "Non-Rebreather Mask at 10-15 L/Min";
                    } else {
                        textOne = "Required FiO2 > 100%. Lower Cabin Altitude.";
                        textTwo = "Non-Rebreather Mask at 15 L/Min";
                    }

                    message = textOne + "\n\n" + textTwo;
                    outputTextView.setText(message);

                    //Dismiss keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    Objects.requireNonNull(imm).hideSoftInputFromWindow(initialFiO2EditText.getWindowToken(), 0);

                } else {
                    createToast("FiO2 cannot be <= 0%.");
                }
            }
        }

    }

    public void createToast(String message) {
        outputTextView.setText("");
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }


    //Allows back button to work
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //Creates Action Bar Menu (Info Button)
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.ox_conv_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Move to About Page
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.oxConvAbout:
                newActivity(OxConvAboutActivity.class);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

