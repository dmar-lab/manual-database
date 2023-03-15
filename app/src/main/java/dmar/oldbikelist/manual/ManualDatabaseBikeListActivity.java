package dmar.oldbikelist.manual;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import dmar.oldbikelist.R;
import dmar.oldbikelist.manual.model.Bike;
import dmar.oldbikelist.manual.model.ManualBikeRepository;

/**
 * Based on javastart.pl author on 2017-10-03.
 */
//TODO duplicates possible !! fix it
    //TODO always check shash list (it should be empty)
public class ManualDatabaseBikeListActivity extends AppCompatActivity {

    private EditText bikeNoEditText;
    private EditText securityCodeEditText;
    private ManualBikeAdapter bikeListAdapter; //internal class

    public ManualDatabaseBikeListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_bike_list); //??

        bikeNoEditText = findViewById(R.id.bike);
        securityCodeEditText = findViewById(R.id.security_code);
        Button addBikeButton = findViewById(R.id.add_new_user_btn);
        ListView bikeListView = findViewById(R.id.list);

        //initialization a new bike
        //two fields now, four fields later
        addActionForBikeAddBtn(addBikeButton);

        bikeListAdapter = new ManualBikeAdapter(); //internal class
        updateBikeList(); //refresh the list
        bikeListView.setAdapter(bikeListAdapter);

        bikeListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ManualDatabaseBikeListActivity.this,
                    ManualDatabaseBikeActivity.class);
            intent.putExtra(ManualDatabaseBikeActivity.PARAM_BIKE_ID, id);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBikeList();
    }

    private void addActionForBikeAddBtn(Button addBikeBtn) {
        addBikeBtn.setOnClickListener(v -> {
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
        List<Bike> bikeList = ManualBikeRepository.findAll(this);
        bikeListAdapter.setBikeList(bikeList);
        bikeListAdapter.notifyDataSetChanged();
    }

    //co jeszcze z niej korzysta
    public class ManualBikeAdapter extends BaseAdapter {

        private List<Bike> adapterBikeList;

        @Override
        public int getCount() {
            return adapterBikeList.size();
        }

        @Override
        public Bike getItem(int position) {
            return adapterBikeList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater
                        .from(ManualDatabaseBikeListActivity.this);
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            TextView text = convertView.findViewById(android.R.id.text1);
            Bike bike = getItem(position);
            text.setText(bike.getBikeNo() + " -> " + bike.getSecurityCode());
            return convertView;
        }

        public void setBikeList(List<Bike> adapterBikeList) {
            this.adapterBikeList = adapterBikeList;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            Toast.makeText(ManualDatabaseBikeListActivity.this, "dmar.oldbikelist DELL",
                    Toast.LENGTH_LONG).show();
            return true;
        } else if (item.getItemId() == R.id.menu_exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
