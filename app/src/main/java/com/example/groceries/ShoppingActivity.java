package com.example.groceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;

public class ShoppingActivity extends AppCompatActivity {

    //create method to send mail
    protected void sendEmail(ArrayList arr){
        String message ="";
        for (Object s: arr){
            message += s + "\t";
        }

        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Shopping list.");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try{
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Email sent successfully", "");
        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(ShoppingActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        //call intent from previouse activity
        Intent intent = getIntent();
        ArrayList<String> list_ = intent.getStringArrayListExtra("list");
        ArrayList<String> price_ = intent.getStringArrayListExtra("prices");


        final ArrayList<String> lst = new ArrayList<>();

        for (int i=0; i < list_.size(); i++){
            lst.add(MessageFormat.format("{0}                                                                    {1}", list_.get(i), price_.get(i)));
        }
//                 create an array adapter to populate the list view
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ShoppingActivity.this, R.layout.simple_list_item_1, lst);
        //get list view id
        final ListView listview = (ListView) findViewById(R.id.name_list);
        listview.setAdapter(arrayAdapter);

        Double[] newPrice = new Double[price_.size()];
        newPrice = price_.toArray(newPrice);

        //sum up price
        double sum = 0;
        for(double num: newPrice){
            sum += num;
        }

//      format and display the results to the edit text view
        EditText displayResult_ = (EditText) findViewById(R.id.result);
        DecimalFormat formatval = new DecimalFormat("##.##");
        displayResult_.setText(formatval.format(sum));


        Button sendButton = (Button) findViewById(R.id.send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(lst);
            }
        });
    }


}
