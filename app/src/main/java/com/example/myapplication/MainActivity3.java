package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    EditText id;
    Button view, delete, act2;
    final DataBaseHelper myDB = new DataBaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        id = findViewById(R.id.editTextTextPersonName3);

        view = findViewById(R.id.button5);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = myDB.viewEmployees();
                StringBuffer buffer = new StringBuffer();
                while(cur.moveToNext()){
                    buffer.append("id: "+cur.getString(0)+ "\n");
                    buffer.append("name: "+cur.getString(1)+ "\n");
                    buffer.append("surname: "+cur.getString(2)+ "\n");
                    buffer.append("national id: "+cur.getString(3)+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setCancelable(true);
                builder.setTitle("All Employees");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        delete = findViewById(R.id.button6);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_val = id.getText().toString();
                myDB.deleteEmployees(id_val);

                Toast.makeText(MainActivity3.this, "Deleted Employee Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        act2 = findViewById(R.id.button7);
        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, MainActivity2.class));
            }
        });

    }
}