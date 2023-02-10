package dmar.oldbikelist.manual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dmar.oldbikelist.R;
import dmar.oldbikelist.manual.model.Bike;
import dmar.oldbikelist.manual.model.ManualBikeRepository;

/**
 * Based on javastart.pl author on 2017-10-03.
 */
//TODO
    //increase literal on the list
public class ManualDatabaseBikeListActivity extends AppCompatActivity {

    private EditText bikeNoEditText;
    private EditText securityCodeEditText;
    private Button addBikeBtn;
    private ListView bikeListView;
    private ManualBikeAdapter bikeListAdapter; //internal class
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
        bikeListView = findViewById(R.id.list);

        //initialization a new bike
        //na poczatek tylko dwa pierwsze pola
        addActionForBikeAddBtn(addBikeBtn);

        bikeListAdapter = new ManualBikeAdapter(); //internal class
        updateBikeList(); //refresh the list
        bikeListView.setAdapter(bikeListAdapter);

        bikeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManualDatabaseBikeListActivity.this,
                        ManualDatabaseBikeActivity.class);
                intent.putExtra(ManualDatabaseBikeActivity.PARAM_BIKE_ID, id);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateBikeList();
    }

    private void addActionForBikeAddBtn(Button addBikeBtn) {
        addBikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bikeNoEditText.getText().toString().isEmpty()
                        || securityCodeEditText.getText().toString().isEmpty()) {
                    Toast.makeText(ManualDatabaseBikeListActivity.this,
                            "Uzupełnij obydwa pola",
                            Toast.LENGTH_SHORT).show();
                } else {
                    addBike();
                    bikeNoEditText.setText("");
                    securityCodeEditText.setText("");
                }
            }
        });
    }

    private void addBike() {
        Bike bike = new Bike();
        bike.setBikeNo(bikeNoEditText.getText().toString());
        bike.setSecurityCode(securityCodeEditText.getText().toString());
        ManualBikeRepository.addBike(ManualDatabaseBikeListActivity.this, bike);
        updateBikeList();
    }

    private void updateBikeList() {
        //pobranie rekordow z bazy
        bikeList = ManualBikeRepository.findAll(this);
        bikeListAdapter.setBikeList(bikeList);
        bikeListAdapter.notifyDataSetChanged();
    }

    //co jeszcze z niej korzysta
    public class ManualBikeAdapter extends BaseAdapter {

        private List<Bike> bikeList;

        @Override
        public int getCount() {
            return bikeList.size();
        }

        @Override
        public Bike getItem(int position) {
            return bikeList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater
                        .from(ManualDatabaseBikeListActivity.this);
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            TextView text = convertView.findViewById(android.R.id.text1);
            Bike bike = getItem(position);
            // dont concatenate with setText
            text.setText(bike.getBikeNo() + " -> " + bike.getSecurityCode());
            return convertView;
        }

        public void setBikeList(List<Bike> bikeList) {
            this.bikeList = bikeList;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            Toast.makeText(ManualDatabaseBikeListActivity.this, "dmar.oldbikelist.manual DELL",
                    Toast.LENGTH_LONG).show();
            return true;
        } else if (item.getItemId() == R.id.menu_exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}