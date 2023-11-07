package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Bai2Activity extends AppCompatActivity {

    private EditText edWidth, edHeight;
    private Button btnSend, btnNext;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        edWidth = findViewById(R.id.edWidth);
        edHeight = findViewById(R.id.edHeight);
        btnSend = findViewById(R.id.btnSend);
        tvResult = findViewById(R.id.tvResult);
        btnNext = findViewById(R.id.btnNext);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String width = edWidth.getText().toString();
                String height = edHeight.getText().toString();
                new SendRequestTask().execute(width, height);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bai2Activity.this, Bai3Activity.class);
                startActivity(intent);
            }
        });
    }
    private class SendRequestTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String width = strings[0];
            String height = strings[1];

            try {
                String url = "http://192.187.61.100:80/kimhoangson_ph21573/rectangle_POST.php";

                URL obj = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                OutputStream os = connection.getOutputStream();
                Map<String, String> params = new HashMap<>();
                params.put("width", width);
                params.put("height", height);
                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, String> param : params.entrySet()) {
                    if (postData.length() != 0) postData.append('&');
                    postData.append(param.getKey()).append('=').append(param.getValue());
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");
                os.write(postDataBytes);
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            } catch (Exception e) {
                Log.e("lỗi", "onPostExecute: "+ e.getMessage());
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            tvResult.setText(result);
        }
    }
}