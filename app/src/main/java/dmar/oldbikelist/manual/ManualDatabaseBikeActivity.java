package dmar.oldbikelist.manual;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dmar.oldbikelist.R;
import dmar.oldbikelist.manual.model.Bike;

/**
 * Based on javastart.pl author on 2017-10-03.
 */

//properties of bike
public class ManualDatabaseBikeActivity extends AppCompatActivity {

    public static final String PARAM_BIKE_ID = "param.bike.id"; //data for intent
    private static final int INVALID_ID = -100;
    private Bike bike;
    private EditText bikeNoEditText;
    private EditText securityCodeEditText;
    private EditText other1EditText;
    private EditText other2EditText;
    private Button saveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_bike);

        bikeNoEditText = findViewById(R.id.bike);
        securityCodeEditText = findViewById(R.id.security_code);
        other1EditText = findViewById(R.id.other1);
        other2EditText = findViewById(R.id.other2);
        saveButton = findViewById(R.id.save_btn);
    }
}
