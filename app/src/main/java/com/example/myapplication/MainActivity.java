package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView date, temp, humidity;
    Button datePicker, activity_2;
    String url = "http://api.openweathermap.org/data/2.5/weather?q=London&appid=99544f0c6d4f86c6a25d7b3021204c1f&units=metric";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = findViewById(R.id.textView2);
        datePicker = findViewById(R.id.dateBtn);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, d, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        temp = findViewById(R.id.textView3);
        humidity = findViewById(R.id.textView4);
        weather(url);

        activity_2 = findViewById(R.id.button2);
        activity_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
    }
    Calendar c = Calendar.getInstance();
    DateFormat fmtDate = DateFormat.getDateInstance();
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            date.setText("The date you chose is: "+fmtDate.format(c.getTime()));
        }
    };

    public void weather(String url){
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Eisa", "Response Received");
                try {
                    JSONObject jsonMain = response.getJSONObject("main");

                    double temperature = jsonMain.getDouble("temp");
                    Log.d("Eisa","temp=" + temperature);
                    temp.setText(String.valueOf(temperature)+"°C");

                    double humid = jsonMain.getDouble("humidity");
                    Log.d("Eisa","humidity=" + humid);
                    humidity.setText("Humidity: "+String.valueOf(humid));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("Eisa", "JSON ERROR");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Eisa", "Error retrieving URL");
            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }

}