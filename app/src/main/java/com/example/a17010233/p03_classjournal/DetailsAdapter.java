package com.example.a17010233.p03_classjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsAdapter extends ArrayAdapter<Details> {

    private ArrayList<Details> details;
    private Context context;
    private TextView tvWeek;
    private TextView tvGrade;
    private ImageView ivImage;

    public DetailsAdapter(Context context, int resource, ArrayList<Details> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        details = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    // getView() is the method ListView will call to get the
    //  View object every time ListView needs a row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvWeek = rowView.findViewById(R.id.tvWeek);
        tvGrade = rowView.findViewById(R.id.tvGrade);
        // Get the ImageView object
        ivImage = rowView.findViewById(R.id.ivImage);


        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        Details currentFood = details.get(position);
        // Set the TextView to show the food

        tvWeek.setText("Week " + currentFood.getWeek());
        tvGrade.setText(currentFood.getGrade());



        // Return the nicely done up View to the ListView
        return rowView;
    }




}
