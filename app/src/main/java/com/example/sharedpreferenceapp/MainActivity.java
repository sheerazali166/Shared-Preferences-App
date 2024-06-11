package com.example.sharedpreferenceapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Toolbar myToolbar;
    private TextView tvActualUnits;
    private TextView tvResult;
    private EditText etMeter;
    private Button bnConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        myToolbar = findViewById(R.id.toolbar);
        tvActualUnits = findViewById(R.id.tvActualUnits);
        tvResult = findViewById(R.id.tvResult);
        etMeter = findViewById(R.id.etMeters);
        bnConverter = findViewById(R.id.btConverter);

        setSupportActionBar(myToolbar);




        // ByDefault
        setDefaultText();

        bnConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Handle the conversion
                Convert(Float.parseFloat(etMeter.getText().toString()));
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {

        tvActualUnits.setText(getString(R.string.actual_units, getActualUnitsLabel()));

        super.onResume();
    }

    private void setDefaultText() {
        tvActualUnits.setText(getString(R.string.actual_units, getActualUnitsLabel()));
        etMeter.setText("");
        tvResult.setText(getString(R.string.result, "0"));
    }

    private String getActualUnitsLabel() {

        // SharedPreferences
        SharedPreferences mSharedPreferences = getSharedPreferences("ActualUnits", MODE_PRIVATE);

        // Retrieve a value
        return mSharedPreferences.getString("Label", "cm");
    }

    private float getActualUnits() {

        // SharedPreferences
        SharedPreferences mSharedPreferences = getSharedPreferences("ActualUnits", MODE_PRIVATE);

        // Retrieve a value
        return mSharedPreferences.getFloat("Unit", 100f);
    }

    private void Convert(float value) {

        // implement later
        tvResult.setText(getString(R.string.result, String.valueOf(value * getActualUnits())));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.settings, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.settings) {

            startActivity(new Intent(this, Settings.class));
        }


        return true;
    }
}