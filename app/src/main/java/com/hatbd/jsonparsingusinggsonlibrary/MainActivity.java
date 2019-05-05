package com.hatbd.jsonparsingusinggsonlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private  static  final  String URL = "https://api.github.com/users";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("key",response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
             User[] users = gson.fromJson(response, User[].class);

             recyclerView.setAdapter(new MyAdapter(getApplicationContext(),users));



            }
        },

          new Response.ErrorListener() {
           @Override
            public void onErrorResponse(VolleyError error) {
               String message = error.getMessage().toString();

               Toast.makeText(MainActivity.this, "Error! "+message, Toast.LENGTH_SHORT).show();
           }
          });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
