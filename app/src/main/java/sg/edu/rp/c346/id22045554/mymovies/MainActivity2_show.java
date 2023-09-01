package sg.edu.rp.c346.id22045554.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity2_show extends AppCompatActivity {
    ListView lvMovies;
    ArrayList<Movie> alMovie;
    ArrayList<Movie> alGroup;
    CustomAdapter adapter;
    ArrayAdapter<String> dataAdapter;
    Spinner spnRate;
    Button btnAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_show);
        lvMovies = findViewById(R.id.listViewMovies);
        btnAll = findViewById(R.id.buttonShow13);
        spnRate = findViewById(R.id.spinner3);



        DBHelper db = new DBHelper(MainActivity2_show.this);

        int defaultPosition = 0;
        spnRate.setSelection(defaultPosition);


        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, db.getAllRate());
        spnRate.setAdapter(dataAdapter);




        spnRate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    alMovie = db.getMovies();

                    adapter = new CustomAdapter(MainActivity2_show.this, R.layout.row, alMovie);
                    lvMovies.setAdapter(adapter);
                } else {
                    String selectedValue = parent.getItemAtPosition(position).toString();
                    alGroup = db.getRatesmovies(selectedValue);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where nothing is selected

            }
        });
        db.close();

        btnAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity2_show.this);

                adapter = new CustomAdapter(MainActivity2_show.this, R.layout.row, alGroup);
                lvMovies.setAdapter(adapter);
                db.close();

            }
        });


        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie data = alGroup.get(position);

                Intent intent = new Intent(MainActivity2_show.this, MainActivity3_modify.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });


    }
}