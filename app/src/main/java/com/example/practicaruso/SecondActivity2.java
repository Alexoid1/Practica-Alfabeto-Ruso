package com.example.practicaruso;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class SecondActivity2 extends AppCompatActivity {

    TextView textRus;
    Button btnResultado, btn1;
    EditText inputWord;
    String rusW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second2);

        textRus = findViewById(R.id.textRus);
        btnResultado = findViewById(R.id.button2);
        inputWord = findViewById(R.id.input_word);
        btn1 = findViewById(R.id.alfabeto_btn);
        loadJson();

        //String rusText = rusWordObject.getString("ruso");
        textRus.setText(rusW);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(i);
            }
        });
    }

    public int generateRandomNumber() {
        Random random = new Random();
        int upper_limit = 20;
        int randomNumber = random.nextInt(upper_limit);
        return randomNumber;
    }



    private void loadJson() {
        try {
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json;
            int max;

            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);
            max = jsonArray.length();

            int random_num = generateRandomNumber();
            JSONObject chosenWord = jsonArray.getJSONObject(random_num);
            rusW =chosenWord.getString("ruso");
            Log.e("TAG","HOLA: "+rusW);


        } catch (Exception e) {
            Log.e("TAG","loadJson error");

        }
    }

}