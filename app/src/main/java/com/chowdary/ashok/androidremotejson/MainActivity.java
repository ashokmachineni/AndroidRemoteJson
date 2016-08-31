package com.chowdary.ashok.androidremotejson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chowdary.ashok.androidremotejson.network.GsonRequest;
import com.chowdary.ashok.androidremotejson.network.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String PATH_TO_JSON_FILE = "http://thecodeeasy.com/test/videos.json";

    private static final int MY_SOCKET_TIMEOUT_MS = 5000;

    private RecyclerView jsonRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonRecyclerView = (RecyclerView) findViewById(R.id.main_content);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        jsonRecyclerView.setLayoutManager(linearLayoutManager);
        jsonRecyclerView.setHasFixedSize(true);

        getAndParseJsonFromServer();
    }

    private void getAndParseJsonFromServer(){
        GsonRequest<JsonObjectWrapper[]> serverRequest = new GsonRequest<JsonObjectWrapper[]>(
                Request.Method.GET,
                PATH_TO_JSON_FILE,
                JsonObjectWrapper[].class,
                createRequestSuccessListener(),
                createRequestErrorListener());

        serverRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(MainActivity.this).addToRequestQueue(serverRequest);
    }

    private Response.Listener<JsonObjectWrapper[]> createRequestSuccessListener() {
        return new Response.Listener<JsonObjectWrapper[]>() {
            @Override
            public void onResponse(JsonObjectWrapper[] response) {
                try {
                    Log.d(TAG, "Json Response " + response.length);
                    if(response != null && response.length > 0){
                        List<JsonObjectWrapper> allData = new ArrayList<JsonObjectWrapper>();
                        for(int i = 0; i < response.length; i++){
                            JsonObjectWrapper newsObject = response[i];
                            Log.d(TAG, " News Category " + newsObject.getTitle());
                            allData.add(newsObject);
                        }
                        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(MainActivity.this, allData);
                        jsonRecyclerView.setAdapter(mAdapter);
                    }else{
                        Toast.makeText(MainActivity.this, getString(R.string.no_content), Toast.LENGTH_LONG).show();
                        return;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        };
    }

    private Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };
    }

}
