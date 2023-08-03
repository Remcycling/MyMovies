package sg.edu.rp.c346.id22045554.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);
        this.parent_context = context;
        this.layout_id = resource;
        this.movieList = objects;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id,parent,false);

        TextView tvName = rowView.findViewById(R.id.textViewName);
        TextView tvGenre = rowView.findViewById(R.id.textViewGenre);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        ImageView ivRating = rowView.findViewById(R.id.imageViewRating);

        Movie currentItem = movieList.get(position);

        if (currentItem.getRating().equalsIgnoreCase("PG13")) {
            ivRating.setImageResource(R.drawable.rating_pg13);
        } else if (currentItem.getRating().equalsIgnoreCase("NC16")) {
            ivRating.setImageResource(R.drawable.rating_nc16);
        } else if (currentItem.getRating().equalsIgnoreCase("G")){
            ivRating.setImageResource(R.drawable.rating_g);
        } else if (currentItem.getRating().equalsIgnoreCase("PG")){
            ivRating.setImageResource(R.drawable.rating_pg);
        } else if (currentItem.getRating().equalsIgnoreCase("M18")){
            ivRating.setImageResource(R.drawable.rating_m18);
        } else {
            ivRating.setImageResource(R.drawable.rating_r21);
        }


        tvName.setText(currentItem.getTitle());
        tvGenre.setText(currentItem.getGenre());
        tvYear.setText(currentItem.getYear()+"");

        return rowView;
    }
}
