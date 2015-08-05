package com.example.vishal.hotelscom;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyActivity extends Activity
{
    private ListView GetHotelListView;
    private JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        this.GetHotelListView = (ListView) this.findViewById(R.id.list);

        new GetHotelsTask().execute(new ExternalServiceHandler());

        this.GetHotelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               try {
                   //get the hotel details which was selected
                   JSONObject hotelClicked = jsonArray.getJSONObject(position);
                   //send hotel id
                   Intent showDetails = new Intent(getApplicationContext(), HotelDetailsActivity.class);
                   showDetails.putExtra("HotelID", hotelClicked.getInt("id"));
                   startActivity(showDetails);
               }
               catch (JSONException e) {
                   e.printStackTrace();
               }
           }
         });



            }


    public void setListAdapter(JSONArray jsonArray) {
        this.jsonArray=jsonArray;
        this.GetHotelListView.setAdapter(new GetHotelListViewAdapter(jsonArray,this));
    }


    /**
     * Async task class to get json by making HTTP call
     */
    private class GetHotelsTask extends AsyncTask<ExternalServiceHandler, Long, JSONArray> {
        @Override
        protected JSONArray doInBackground(ExternalServiceHandler... params) {
           return  params[0].getAllHotels();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            // Dismiss the progress dialog
            // if (pDialog.isShowing())
            //     pDialog.dismiss();
            setListAdapter(jsonArray);
        }

    }

}



            /**
             * Updating parsed JSON data into ListView
             * */
           // ListAdapter adapter = new SimpleAdapter(
            //        MyActivity.this, contactList,
            //        R.layout.list_item, new String[] { TAG_NAME, TAG_EMAIL,
            //        //TAG_PHONE_MOBILE
           // }, new int[] { R.id.name,
             //       R.id.email, R.id.mobile });

           // setListAdapter(adapter);
       // }

