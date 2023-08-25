package sg.edu.rp.c346.id22045554.mymovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity3_modify extends AppCompatActivity {
    Button btnUpdate, btnDelete, btnCancel;
    EditText etTitle, etYear, etGenre;
    TextView tvId;
    Spinner spnRates;
    Movie data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_modify);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.buttonCancel);
        tvId = findViewById(R.id.textViewId);
        etTitle = findViewById(R.id.editTextTitle2);
        etGenre = findViewById(R.id.editTextGenre2);
        etYear = findViewById(R.id.editTextYear2);
        spnRates = findViewById(R.id.spinner2);


        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");


        tvId.setText(""+data.getId());
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
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity3_modify.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete " + data.getTitle() + "?");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(MainActivity3_modify.this);
                        dbh.deleteMovie(data.getId());


                        Intent i = new Intent(MainActivity3_modify.this,
                                MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                });

                myBuilder.setNeutralButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity3_modify.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(MainActivity3_modify.this,
                                MainActivity2_show.class);
                        startActivity(i);
                        finish();
                    }
                });

                myBuilder.setNeutralButton("DO NOT DISCARD", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });
    }
}