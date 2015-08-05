package com.example.vishal.hotelscom;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Vishal on 20/11/2014.
 */
public class HotelDetailsActivity extends Activity {
    private TextView hotelName;
    private TextView hotelAddress;
    private TextView hotelPrice;
    private TextView hotelDescription;
    private ImageView hotelImage, icon;


    private int HotelID;
    private Button proceedButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        proceedButton = (Button) findViewById(R.id.Button);
        this.hotelName = (TextView) this.findViewById(R.id.hotelName);
        this.hotelDescription=(TextView) this.findViewById(R.id.hotelDescription);
        this.hotelAddress = (TextView) this.findViewById(R.id.hotelAddress);
        this.hotelPrice = (TextView) this.findViewById(R.id.hotelPrice);
        this.hotelImage = (ImageView) this.findViewById(R.id.code);
        this.icon = (ImageView) this.findViewById(R.id.icon);




        //get hotel id
        this.HotelID = getIntent().getIntExtra("HotelID", -1);
        if (this.HotelID > 0) {
            new GetHotelsDetails().execute(new ExternalServiceHandler());
        }
    }

    public void proceedButton(View v) {
        Bundle dataBundle = new Bundle();
        // set the sender and the receiver of the intent
        Intent intent = new Intent();
        intent.setClass(this, BookingDetails.class);
        intent.putExtras(dataBundle); // store the data you want sent

        startActivity(intent); // transmit your intent
    }
/**
 * Async task class to get json by making HTTP call
 */
private class GetHotelsDetails extends AsyncTask<ExternalServiceHandler, Long, JSONArray> {
    @Override
    protected JSONArray doInBackground(ExternalServiceHandler... params) {
        return  params[0].GetHotelsDetail(HotelID);
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        try {
            JSONObject hotel = jsonArray.getJSONObject(0);
            hotelName.setText(hotel.getString("name"));
            hotelDescription.setText(hotel.getString("description"));
            hotelAddress.setText(hotel.getString("address"));
            hotelPrice.setText(hotel.getString("price"));

            String image=hotel.getString("image");
            if( image.equals("villa")){
                icon.setImageResource(R.drawable.villa);
            }
            else if( hotelImage.equals("citadines")){
               icon.setImageResource(R.drawable.citadenies);
            }
            else if( hotelImage.equals("rend")){
               icon.setImageResource(R.drawable.rende);
            }
            else if( hotelImage.equals("bayside")){
               icon.setImageResource(R.drawable.bayview);
            }
            else if( hotelImage.equals("treasury")){
                icon.setImageResource(R.drawable.treary);
            }
            else if( hotelImage.equals("stamford")){
             icon.setImageResource(R.drawable.stamford);
            }
            else if( hotelImage.equals("tune")){
              icon.setImageResource(R.drawable.tune);
            }
            else if( hotelImage.equals("ibis")){
                icon.setImageResource(R.drawable.ibis);
            }
            else if( hotelImage.equals("mantra")){
               icon.setImageResource(R.drawable.mantra);
            }
            else
            {
                icon.setImageResource(R.drawable.hilton);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }}}


