package com.example.vishal.hotelscom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Vishal on 21/11/2014.
 */
public class BookingDetails extends Activity {

    DatePicker toDatepicker;
    DatePicker fromDatepicker;
    EditText guestText;
    Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        toDatepicker =  (DatePicker) findViewById(R.id.datePicker01);
        fromDatepicker=  (DatePicker) findViewById(R.id.datePicker02);
        guestText=  (EditText)findViewById(R.id.guestEdittext);
        nextButton = (Button) findViewById(R.id.nextButton);

    }
    // write text to file
    public void nextButton(View v) {
        // write text to file
        String strDateTo = toDatepicker.getYear() + "-" + (toDatepicker.getMonth() + 1) + "-" + toDatepicker.getDayOfMonth();
        String strDateFrom = fromDatepicker.getYear() + "-" + (fromDatepicker.getMonth() + 1) + "-" + fromDatepicker.getDayOfMonth();

        String newline = "\r\n";

        try {
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File(sdCard.getAbsolutePath() + "/Android/myDir");
            dir.mkdirs();
            File file = new File(dir, "manisha.txt");
            openFileOutput("manisha.txt", MODE_APPEND);
            FileOutputStream f = new FileOutputStream(file, true);
            OutputStreamWriter outputWriter = new OutputStreamWriter(f);
            outputWriter.write(strDateTo + ",");
            outputWriter.write(strDateFrom + ",");
            outputWriter.write(guestText.getText().toString() + newline);
            outputWriter.flush();
            f.close();
            outputWriter.close();
            Toast.makeText(getBaseContext(), "proceeding to next page...",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Bundle dataBundle = new Bundle();
        // set the sender and the receiver of the intent
        Intent intent = new Intent();
        intent.setClass(this, SubmitFormActivity.class);
        intent.putExtras(dataBundle); // store the data you want sent

        startActivity(intent); // transmit your intent

    }




}

