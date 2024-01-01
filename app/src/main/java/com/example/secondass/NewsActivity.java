package com.example.secondass;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayList<NewsObj> NewsList = new ArrayList<>();
    private RequestQueue q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        listview = findViewById(R.id.list);
        q = Volley.newRequestQueue(this);

        Get_News();
    }

    public void Get_News() {
        Toast.makeText(this, "Just a moment ....", Toast.LENGTH_LONG).show();
        String url = "https://newsdata.io/api/1/news?apikey=pub_3548222cb6c38fa04bdf5081735a3aa378b88&q=pegasus&language=en";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray resultsArray = response.getJSONArray("results");

                            for (int i = 0; i < Math.min(10, resultsArray.length()); i++) {
                                JSONObject jsonObject = resultsArray.getJSONObject(i);
                                String title = jsonObject.getString("title");
                                String description = jsonObject.optString("description", "");

                                NewsObj newsObj = new NewsObj(title, description);
                                NewsList.add(newsObj);

                            }
                            TaskAdapter adapter = new TaskAdapter(NewsActivity.this, NewsList);
                            listview.setAdapter(adapter);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
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
    }
}