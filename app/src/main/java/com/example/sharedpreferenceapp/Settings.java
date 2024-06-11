package com.example.sharedpreferenceapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Settings extends AppCompatActivity {

    private float[] conversionValues = { 100f, 0.001f, 39.3701f };
    private String[] conversionLabels = { "Centimeter", "Kilometer", "Inch" };
    // chootyi tever dikhane nikli thi ya still dikha-yengi
    // maro in currupt muslimani kution ko chahe jaan hi q na chali jaye
    // humne kiya choorhyan pehn rakhi thi ya hain
    // choot bech kar islam phelayengi
    // gadhi hai tumhari begairat chootyi baap tum bachalo gi in kutiyon ko toh bachao na
    // comptition should be equal
    // if you think you are doggi mama stubborn currupt mama
    // big no for these stubborn currupt puppies at no condition
    private String[] shortConversionLabel = { "cm", "km", "in" };

    // view
    private RadioGroup mRadioGroup;

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        createRadioButtons();

        mToolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void createRadioButtons() {

        mRadioGroup = findViewById(R.id.rgConverterOptions);

        for (int i = 0; i < conversionLabels.length; i++) {

            RadioButton rButton = new RadioButton(this);
            rButton.setText(getString(R.string.option, conversionLabels[i]));
            final float value = conversionValues[i];
            final String shortLabel = shortConversionLabel[i];

            // Listener
            rButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setActualUnits(value, shortLabel);

                }
            });
            mRadioGroup.addView(rButton);

            // Selected
            if (value == getActualUnits()) {
                rButton.setChecked(true);
            }
        }

    }

    private float getActualUnits() {

        // SharedPreferences
        SharedPreferences mSharedPreferences = getSharedPreferences("ActualUnits", MODE_PRIVATE);

        // Retrieve a value
        return mSharedPreferences.getFloat("Unit", 100f);
    }

    private void setActualUnits(float value, String shortLabel) {

        // Shared Preferences
        SharedPreferences mSharedPreferences = getSharedPreferences("ActualUnits", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();

        // Store value in pairs (KEY, VALUE)
        mEditor.putFloat("Unit", value);
        mEditor.putString("Label", shortLabel);

        // Apply changes
        mEditor.apply();


    }
}