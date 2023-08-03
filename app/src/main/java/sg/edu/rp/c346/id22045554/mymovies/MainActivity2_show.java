package sg.edu.rp.c346.id22045554.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2_show extends AppCompatActivity {
    ListView lvMovies;
    ArrayList<Movie> alMovie;
    ArrayList<Movie> al13;
    CustomAdapter adapter;
    Button btn13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_show);
        lvMovies = findViewById(R.id.listViewMovies);
        btn13 = findViewById(R.id.buttonShow13);



        DBHelper db = new DBHelper(MainActivity2_show.this);

        alMovie = db.getMovies();

        adapter = new CustomAdapter(this, R.layout.row, alMovie);
        lvMovies.setAdapter(adapter);

        db.close();

        btn13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity2_show.this);


                al13 = db.get13movies();
                adapter = new CustomAdapter(MainActivity2_show.this, R.layout.row, al13);
                lvMovies.setAdapter(adapter);



                db.close();

            }
        });


        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie data = alMovie.get(position);

                Intent intent = new Intent(MainActivity2_show.this, MainActivity3_modify.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });


    }
}