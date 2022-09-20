package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddData extends AppCompatActivity {
    Connection connection;
    String ConnectionResult = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        configureBackButton();
    }

    private void configureBackButton() {
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void SetTextFromSql(View v) {
        TextView BaseNameAdd = findViewById(R.id.BaseNameAdd);
        TextView GeografPositionAdd = findViewById(R.id.GeografPositionAdd);
        TextView NumberOfPartsAdd = findViewById(R.id.NumberOfPartsAdd);
        String Base = BaseNameAdd.getText().toString();
        String Position = GeografPositionAdd.getText().toString();
        String Number = NumberOfPartsAdd.getText().toString();
        try {
            ConectionHellper conectionHellper = new ConectionHellper();
            connection = conectionHellper.connectionClass();

            if (connection != null) {

                String query = "INSERT INTO Base (BaseName, GeographicalPosition, numberOfParts) values ('" + Base + "','" + Position + "'," + Number + ")";
                Statement statement = connection.createStatement();
                statement.execute(query);
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}