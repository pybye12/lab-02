package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //Declaring the variables so I can reference it later
    ListView citylist;
    ArrayAdapter<String> cityadapter;
    ArrayList<String> dataList;
    EditText cityInput;
    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        citylist = findViewById(R.id.city_list);
        dataList = new ArrayList<>();
        String []cities = {"Edmonton","Vancouver","Moscow","Sydney","Berlin","Vienna","Tokyo","Beijing","Osaka","New Delhi"};
        dataList.addAll(Arrays.asList(cities));
        cityadapter = new ArrayAdapter<>(this,R.layout.content,dataList);
        citylist.setAdapter(cityadapter);
        addButton = findViewById(R.id.Add_id);
        cityInput = findViewById(R.id.edit_text);
        Button deleteButton = findViewById(R.id.Delete_id);

        //When add button is clicked
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String text = cityInput.getText().toString();
                // we took input text then converted to string
                //now we add to original data list
                dataList.add(text);
                //now update adapter which talks to xml
                cityadapter.notifyDataSetChanged();

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String text = cityInput.getText().toString();
                // i think we should check if this text matches a name in datalist
                if (!text.isEmpty()&& dataList.contains(text)){
                    dataList.remove(text);
                    //update adapter
                    cityadapter.notifyDataSetChanged();
                }else{
                    cityInput.setError("Write something which exists in the list");

                }

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}