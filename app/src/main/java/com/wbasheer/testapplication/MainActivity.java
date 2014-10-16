package com.wbasheer.testapplication;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wbasheer.testapplication.adapter.CustomListAdapter;
import com.wbasheer.testapplication.model.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog progressDialog;
    private AbsListView listView;
    private CustomListAdapter adapter;

    List<Image> images = new ArrayList<Image>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        listView = (AbsListView) findViewById(R.id.listView);
        adapter = new CustomListAdapter(this, images);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listItemClickListener);

        progressDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        // Creating volley request obj
        String url = AppController.dataUrl;
        JsonObjectRequest imagesReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONArray jsonArray = response.getJSONArray("album");
                                jsonArray.getJSONObject(i);

                                for(int x = 0; x < jsonArray.length(); x++) {
                                    JSONObject obj = jsonArray.getJSONObject(x);
                                    Image image = new Image();
                                    image.setName(obj.getString("img_filename"));
                                    image.setTitle(obj.getString("img_name"));
                                    image.setDescription(obj.getString("img_description"));
                                    images.add(image);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(imagesReq);
    }

    private void hidePDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }


    AdapterView.OnItemClickListener listItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            Image image = (Image)adapterView.getItemAtPosition(position);
            final Intent intent = new Intent(MainActivity.this, ImageInfoActivity.class);
            intent.putExtra("IMAGE", image);
            startActivity(intent);

        }
    };

}
