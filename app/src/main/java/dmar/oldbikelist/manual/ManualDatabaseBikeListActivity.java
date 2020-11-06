package dmar.oldbikelist.manual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dmar.oldbikelist.R;
import dmar.oldbikelist.manual.model.Bike;

/**
 * Based on javastart.pl author on 2017-10-03.
 */

public class ManualDatabaseBikeListActivity extends AppCompatActivity {

    private EditText bikeNoEditText;
    private EditText securityCodeEditText;
    private Button addBikeBtn;
    private ListView bikeListView;
//    private ManualBikeAdapter bikeListAdapter; //internal class
    private List<Bike> bikeList = new ArrayList <>();

    public ManualDatabaseBikeListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_bike_list);

        bikeNoEditText = findViewById(R.id.bike);
        securityCodeEditText = findViewById(R.id.security_code);
        addBikeBtn = findViewById(R.id.add_new_user_btn);
    }
}