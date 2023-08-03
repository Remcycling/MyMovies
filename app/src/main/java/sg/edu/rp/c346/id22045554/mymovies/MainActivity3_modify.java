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

public class MainActivity3_modify extends AppCompatActivity {
    Button btnUpdate, btnDelete, btnCancel;
    EditText etId, etTitle, etYear, etGenre;
    Spinner spnRates;
    Movie data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_modify);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.buttonCancel);
        etId = findViewById(R.id.editTextId);
        etTitle = findViewById(R.id.editTextTitle2);
        etGenre = findViewById(R.id.editTextGenre2);
        etYear = findViewById(R.id.editTextYear2);
        spnRates = findViewById(R.id.spinner2);


        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");


        etId.setHint(""+data.getId());
        etTitle.setText(data.getTitle());
        etGenre.setText(data.getGenre());
        etYear.setText(""+data.getYear());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity3_modify.this,
                R.array.spinnerItems, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnRates.setAdapter(adapter);

        if (data.getRating().equalsIgnoreCase("G")){
            spnRates.setSelection(0);
        } else if (data.getRating().equalsIgnoreCase("PG")){
            spnRates.setSelection(1);
        } else if (data.getRating().equalsIgnoreCase("PG13")){
            spnRates.setSelection(2);
        } else if (data.getRating().equalsIgnoreCase("NC16")){
            spnRates.setSelection(3);
        } else if (data.getRating().equalsIgnoreCase("M18")){
            spnRates.setSelection(4);
        } else {
            spnRates.setSelection(5);
        }



        spnRates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                        rate ="M18";
                        break;
                    case 5:
                        parent.getItemAtPosition(position);
                        rate ="R21";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity3_modify.this);

                String y = etYear.getText().toString();
                int date = Integer.parseInt(y);
                String rate = spnRates.getSelectedItem().toString();

                /*int num = 0;
                int starCheck = star.getCheckedRadioButtonId();
                if (starCheck == R.id.rb1) {
                    num = 1;
                } else if (starCheck == R.id.rb2) {
                    num = 2;
                } else if (starCheck == R.id.rb3) {
                    num = 3;
                } else if (starCheck == R.id.rb4) {
                    num = 4;
                } else {
                    num = 5;
                }*/



                data.setMovie(etTitle.getText().toString(), etGenre.getText().toString(),date, rate);
                dbh.updateMovie(data);
                dbh.close();

                Intent i = new Intent(MainActivity3_modify.this,
                        MainActivity.class);
                startActivity(i);
                finish();


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity3_modify.this);
                dbh.deleteMovie(data.getId());


                Intent i = new Intent(MainActivity3_modify.this,
                        MainActivity.class);
                startActivity(i);
                finish();


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity3_modify.this,
                        MainActivity2_show.class);
                startActivity(i);
                finish();

            }
        });
    }
}