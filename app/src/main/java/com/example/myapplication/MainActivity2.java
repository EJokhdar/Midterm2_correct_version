package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText id, name, surname, national;
    Button add, act1, act3;
    final DataBaseHelper myDB = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        id = findViewById(R.id.editTextNumber);
        name = findViewById(R.id.editTextTextPersonName);
        surname = findViewById(R.id.editTextTextPersonName2);
        national = findViewById(R.id.editTextNumber2);
        add = findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_val = Integer.parseInt(id.getText().toString());
                String name_val = name.getText().toString();
                String surname_val = surname.getText().toString();
                int national_val = Integer.parseInt(national.getText().toString());

                myDB.addEmployee(id_val, name_val, surname_val, national_val);

                Toast.makeText(MainActivity2.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        act1 = findViewById(R.id.button3);
        act1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });
        act3 = findViewById(R.id.button4);
        act3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            }
        });
    }
}