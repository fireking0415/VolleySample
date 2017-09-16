package org.fireking.volleysample;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

public class CustomRequestActivity extends AppCompatActivity {

    private static final String TAG =CustomRequestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_request);

        findViewById(R.id.getDataForGson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolleyManager.getInstance(CustomRequestActivity.this)
                        .addToRequestQueue(new GsonRequest<GankFuliEntity>(Request.Method.GET,
                                "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1",
                                GankFuliEntity.class, new Response.Listener<GankFuliEntity>() {
                            @Override
                            public void onResponse(GankFuliEntity response) {
                                Log.e(TAG, "response->" +response.isError()+", size->" + response.getResults().size());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "error->" +error.getMessage());
                            }
                        }));
            }
        });

        findViewById(R.id.getDataForFastJson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolleyManager.getInstance(CustomRequestActivity.this)
                        .addToRequestQueue(new FastJsonRequest<GankFuliEntity>(Request.Method.GET,
                                "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1",
                                GankFuliEntity.class, new Response.Listener<GankFuliEntity>() {
                            @Override
                            public void onResponse(GankFuliEntity response) {
                                Log.e(TAG, "response->" +response.isError()+", size->" + response.getResults().size());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "error->" +error.getMessage());
                            }
                        }));
            }
        });
    }

    public static void router(Context context) {
        context.startActivity(new Intent(context, CustomRequestActivity.class));
    }
}
