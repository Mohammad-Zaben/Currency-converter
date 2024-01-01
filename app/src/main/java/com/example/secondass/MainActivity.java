package com.example.secondass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private EditText txt1, txt2;
    RequestQueue q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner1 = findViewById(R.id.spinner_firstConversion);
        spinner2 = findViewById(R.id.spinner_secondConversion);

        txt1 = findViewById(R.id.et_firstConversion);
        txt2 = findViewById(R.id.et_secondConversion);

        q = Volley.newRequestQueue(this);

        String first = extractCurrencyCode(spinner1.getSelectedItem().toString());
        String second = extractCurrencyCode(spinner2.getSelectedItem().toString());
        String url = "https://v6.exchangerate-api.com/v6/b8a353b0c5efb1e9812c881f/latest/" + first;


    }

    public void bt_OnClick(View view) {

        String first = extractCurrencyCode(spinner1.getSelectedItem().toString());
        String second = extractCurrencyCode(spinner2.getSelectedItem().toString());
        if(!txt1.getText().toString().equals("")) {
            Toast.makeText(this, "Currencies are now being Exchange", Toast.LENGTH_LONG).show();
            String url = "https://v6.exchangerate-api.com/v6/b8a353b0c5efb1e9812c881f/latest/" + first;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                // Check if the API request was successful
                                if (response.getString("result").equals("success")) {
                                    // Get the conversion rates
                                    JSONObject conversionRates = response.getJSONObject("conversion_rates");

                                    // Get the conversion rate from USD to EUR
                                    double Rate = conversionRates.getDouble(second);
                                    double result = Integer.parseInt(txt1.getText().toString());
                                    txt2.setText(Rate * result + "");

                                    // Now you can use usdToEurRate as the conversion rate from USD to EUR

                                } else {
                                    Log.e("API Error", "API request failed: " + response.getString("error"));
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Volley Error", "Error making API request: " + error.toString());
                        }
                    });

            q.add(jsonObjectRequest);
        }else{
            Toast.makeText(this, "Enter the value you want to convert", Toast.LENGTH_LONG).show();
        }

    }

    private static String extractCurrencyCode(String input) {
        // Assuming the currency code is always three characters and at the beginning of the string
        int endIndex = Math.min(3, input.length());
        return input.substring(0, endIndex);
    }
}