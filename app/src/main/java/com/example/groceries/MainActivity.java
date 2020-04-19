package com.example.groceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //create button vars
        final Button buttonAdd = (Button) findViewById(R.id.button_add);

        //create an empty array to store input
        final ArrayList<Double> priceArr = new ArrayList<>();
        final ArrayList<String> itemNameArr = new ArrayList<>();
//        double sum = 0;

        //create a listener to listen for button add
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the input from text box and store in variable
                EditText itemName_ = (EditText) findViewById(R.id.itemName);

                //convert each input
                String itemName = itemName_.getText().toString();
                //add input to list
                itemNameArr.add(itemName);
                itemName_.setText("");

                // do for price
                EditText price_ = (EditText) findViewById(R.id.price);
                double price = Double.valueOf(price_.getText().toString());
                priceArr.add(price);
                Toast toast = Toast.makeText(getApplicationContext(),
                        itemName + "has been added to list",
                        Toast.LENGTH_SHORT);
                toast.show();
                price_.setText("");

            }
        });

//      compile button to compile grocerie list
        Button buttonCompile = (Button) findViewById(R.id.button_compile);
        //create a listener for button compile
        buttonCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShoppingActivity.class);
                //send data to next activity
                intent.putExtra("list", itemNameArr);
                intent.putExtra("prices", priceArr);
                startActivity(intent);
            }
        });


    }



}
