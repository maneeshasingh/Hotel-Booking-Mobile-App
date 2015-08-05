package com.example.vishal.hotelscom;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vishal on 20/11/2014.
 */
public class GetHotelListViewAdapter extends BaseAdapter {
    private JSONArray dataArray;
    private Activity activity;
    private static LayoutInflater inflater=null;

    public GetHotelListViewAdapter(JSONArray jsonArray,Activity a){
        this.dataArray=jsonArray;
        this.activity=a;
        inflater=(LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount(){
        return this.dataArray.length();
    }
    public Object getItem(int position){
        return position;
    }
    public long getItemId(int position){
        return position;
    }
    public View getView(int position,View convertView,ViewGroup parent){
        //setup convert view if it is null
        ListCell cell;
        if(convertView==null){

            convertView=inflater.inflate(R.layout.list_item,null);
            cell=new ListCell();
            cell.name=(TextView) convertView.findViewById(R.id.hotel_name);
            cell.address=(TextView) convertView.findViewById(R.id.hotel_address);
            cell.price=(TextView) convertView.findViewById(R.id.hotel_price);
            cell.imageIcon=(ImageView) convertView.findViewById(R.id.icon);


            convertView.setTag(cell);

        }
        else
        {
   cell=(ListCell)convertView.getTag();
        }
        //change the data of the cell
        try{
            JSONObject jsonObject=this.dataArray.getJSONObject(position);
            cell.name.setText(jsonObject.getString("name"));
             cell.address.setText(jsonObject.getString("address"));
            cell.price.setText("$"+jsonObject.getInt("price"));
            String hotelImage=jsonObject.getString("image");
            if( hotelImage.equals("villa")){
               cell.imageIcon.setImageResource(R.drawable.villa);
           }
            else if( hotelImage.equals("citadines")){
                    cell.imageIcon.setImageResource(R.drawable.citadenies);
                }
                else if( hotelImage.equals("rend")){
                    cell.imageIcon.setImageResource(R.drawable.rende);
            }
            else if( hotelImage.equals("bayside")){
                cell.imageIcon.setImageResource(R.drawable.bayview);
            }
            else if( hotelImage.equals("treasury")){
                cell.imageIcon.setImageResource(R.drawable.treary);
            }
            else if( hotelImage.equals("stamford")){
                cell.imageIcon.setImageResource(R.drawable.stamford);
            }
            else if( hotelImage.equals("tune")){
                cell.imageIcon.setImageResource(R.drawable.tune);
            }
            else if( hotelImage.equals("ibis")){
                cell.imageIcon.setImageResource(R.drawable.ibis);
            }
            else if( hotelImage.equals("mantra")){
                cell.imageIcon.setImageResource(R.drawable.mantra);
            }
            else
           {
                cell.imageIcon.setImageResource(R.drawable.hilton);
            }
        }
        catch(JSONException e){
     e.printStackTrace();
        }
        return convertView;
}
 private class ListCell{
     private TextView name;
     private TextView address;
     private TextView price;
     private ImageView imageIcon;
 }
}