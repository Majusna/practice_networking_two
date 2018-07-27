package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {



    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {

        super(context, 0, earthquakes);

    }

    //Get a View that displays the data at the specified position in the data set.

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        String primaryLocation;
        String locationOffset;



        //Get the data item associated with the specified position in the data set.
        Earthquake currentEarthquake = getItem(position);


        TextView magnitudeView = (TextView) listItemView.findViewById (R.id.magnitude);
        magnitudeView.setText(currentEarthquake.getMagnitude());

        String originalLocation = currentEarthquake.getLocation();

        if (originalLocation.contains("of")) {
            String[] parts = originalLocation.split("of");
            locationOffset = parts [0]+ "of ";
            primaryLocation = parts[1];

        } else{
            locationOffset = "Near the ";
            primaryLocation = originalLocation;
        }


        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        TextView locationView = (TextView) listItemView.findViewById(R.id.primary_location);
        locationView.setText(primaryLocation);


        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);



        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }



}
