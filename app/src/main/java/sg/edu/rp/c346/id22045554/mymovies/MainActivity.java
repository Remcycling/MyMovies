package sg.edu.rp.c346.id22045554.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnShow;
    EditText etTitle, etGenre, etYear;
    Spinner spnRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShow =findViewById(R.id.buttonShow);
        etTitle = findViewById(R.id.editTextName);
        etGenre = findViewById(R.id.editTextGenre);
        etYear = findViewById(R.id.editTextYear);
        spnRating = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.spinnerItems, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnRating.setAdapter(adapter);

        spnRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String rate = "";
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        parent.getItemAtPosition(position);
                        rate = "G";
                        break;
                    case 2:
                        parent.getItemAtPosition(position);
                        rate ="PG";
                        break;
                    case 3:
                        parent.getItemAtPosition(position);
                        rate ="PG13";
                        break;
                    case 4:
                        parent.getItemAtPosition(position);
                        rate ="NC16";
                        break;
                    case 5:
                        parent.getItemAtPosition(position);
                        rate ="M18";
                        break;
                    case 6:
                        parent.getItemAtPosition(position);
                        rate ="R21";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                String t = etTitle.getText().toString();
                String g = etGenre.getText().toString();
                String y = etYear.getText().toString();
                int date = Integer.parseInt(y);
                String rate = spnRating.getSelectedItem().toString();




                db.insertMovie(t,g,date,rate);

                etGenre.setText("");
                etTitle.setText("");
                etYear.setText("");
                Toast.makeText(MainActivity.this, "Movie added!", Toast.LENGTH_LONG).show();

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context

                Intent intent = new Intent(MainActivity.this, MainActivity2_show.class);
                startActivity(intent);



            }
        });

    }
}