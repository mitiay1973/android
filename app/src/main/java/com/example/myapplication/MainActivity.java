package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.TypeConverter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    Connection connection;
    String ConnectionResult="";
    public final int[] i = {1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void GetTextFromSql(View v)
    {
        TextView BaseId = findViewById(R.id.BaseId);
        TextView BaseName = findViewById(R.id.BaseName);
        TextView GeographyPosition = findViewById(R.id.GeografPosition);
        TextView NumberOfParse = findViewById(R.id.NumberOfParts);

        try
        {
            ConectionHellper conectionHellper = new ConectionHellper();
            connection=conectionHellper.connectionClass();

            if(connection!=null)
            {
                Button NextList = findViewById((R.id.NextList));
                Button BackList = findViewById((R.id.BackList));
                String query0 = "select count(Base_Id) from Base ";
                Statement statement0 = connection.createStatement();
                ResultSet resultSet0 = statement0.executeQuery(query0);
                BackList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(i[0]!=1)
                        {
                            i[0] = i[0] - 1;
                        }
                    }
                });
                NextList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            if(i[0]!= 7)
                            {
                                i[0] = i[0] + 1;
                            }
                    }
                });
                String query = "Select * From Base where Base_Id="+i[0]+"";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next())
                {
                    BaseId.setText(resultSet.getString(1));
                    BaseName.setText(resultSet.getString(2));
                    GeographyPosition.setText(resultSet.getString(3));
                    NumberOfParse.setText(resultSet.getString(4));
                }
            }
            else
            {
                ConnectionResult = "Check Connection";
            }
        }
        catch (Exception ex)
        {

        }
    }
}