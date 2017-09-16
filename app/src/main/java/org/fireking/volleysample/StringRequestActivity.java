package org.fireking.volleysample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class StringRequestActivity extends AppCompatActivity {

    private static final String TAG = StringRequestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_request);

        findViewById(R.id.getDataForString).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolleyManager.getInstance(StringRequestActivity.this)
                        .addToRequestQueue(new StringRequest("http://gank.io/api/data/Android/10/1", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, "response->" + response);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "error->" + error.getMessage());
                            }
                        }));
            }
        });
    }

    public static void router(Context context) {
        context.startActivity(new Intent(context, StringRequestActivity.class));
    }
}
