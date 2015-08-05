package com.example.vishal.hotelscom;

import android.app.Activity;
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
public class SubmitFormActivity extends Activity {

    EditText name;
    EditText email;
    EditText phone;
    EditText card;
    EditText cvv;
    DatePicker datepicker;
    Button submitButton;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);
        name = (EditText) findViewById(R.id.editName);
        email= (EditText) findViewById(R.id.emailText);
        phone= (EditText) findViewById(R.id.editPhone);
        card = (EditText) findViewById(R.id.editCard);
        cvv = (EditText) findViewById(R.id.editCvv);
       datepicker = (DatePicker) findViewById(R.id.datePicker);
       submitButton = (Button) findViewById(R.id.submitButton);

    }
    // write text to file
    public void submitButton(View v) {

        // write text to file

            String  newline = "\r\n";

            try {
                File sdCard = Environment.getExternalStorageDirectory();
                File dir = new File(sdCard.getAbsolutePath() + "/Android/myDir");
                dir.mkdirs();
                File file = new File(dir, "manisha.txt");
                openFileOutput("manisha.txt", MODE_APPEND);
                FileOutputStream f = new FileOutputStream(file, true);
                OutputStreamWriter outputWriter = new OutputStreamWriter(f);
                outputWriter.write(name.getText().toString()+",");
                outputWriter.write(email.getText().toString()+" ,");
                outputWriter.write(phone.getText().toString()+", ");
                outputWriter.write(card.getText().toString()+ ", ");
                outputWriter.write(cvv.getText().toString()+newline);
               // outputWriter.write(datepicker.getText().toString()+ ",");

                //outputWriter.write(timezone.getText().toString() + newline);
                outputWriter.flush();
                f.close();
                outputWriter.close();

                //display file saved message
                Toast.makeText(getBaseContext(),"Your booking has been confirmed!",
                        Toast.LENGTH_SHORT).show();

            }
            catch (Exception e) {
                e.printStackTrace();
            }


        }




}
