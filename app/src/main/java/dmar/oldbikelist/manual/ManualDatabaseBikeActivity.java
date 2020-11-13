package dmar.oldbikelist.manual;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dmar.oldbikelist.R;
import dmar.oldbikelist.manual.model.Bike;
import dmar.oldbikelist.manual.model.ManualBikeRepository;

/**
 * Based on javastart.pl author on 2017-10-03.
 */
///media/bcart/KINGSTON/0dell/bazaManual/main/java/pl/javastart/ap/database/manual/model
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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bike.setBikeNo(bikeNoEditText.getText().toString());
                bike.setSecurityCode(securityCodeEditText.getText().toString());
                bike.setOther1(other1EditText.getText().toString());
                bike.setOther2(other2EditText.getText().toString());
                ManualBikeRepository.updateBike(ManualDatabaseBikeActivity.this, bike);
                finish();
            }
        });

        long id = getIntent().getLongExtra(PARAM_BIKE_ID, INVALID_ID);
        if (id == INVALID_ID) {
            finish();
        }

        // pobranie rekordu z Bike i umieszczenie w polach EditText
        bike = ManualBikeRepository.findById(this, id);
        bikeNoEditText.setText(bike.getBikeNo());
        securityCodeEditText.setText(bike.getSecurityCode());
        other1EditText.setText(bike.getOther1());
        other2EditText.setText(bike.getOther2());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_delete_bike) {
            ManualBikeRepository.deleteBike(ManualDatabaseBikeActivity.this, bike);
            Toast.makeText(this, "Usunięto z bazy",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
