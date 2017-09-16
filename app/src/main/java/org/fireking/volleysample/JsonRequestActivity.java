package org.fireking.volleysample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import java.net.HttpURLConnection;

public class JsonRequestActivity extends AppCompatActivity {

    private static final String TAG = JsonRequestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_request);

        findViewById(R.id.getDataForJson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolleyManager.getInstance(JsonRequestActivity.this)
                        .addToRequestQueue(new JsonRequest<GankEntity>(Request.Method.GET, "http://gank.io/api/data/Android/10/1", null, new Response.Listener<GankEntity>() {
                            @Override
                            public void onResponse(GankEntity response) {
                                Log.e(TAG, "response->" + response.isError() + ", size:" + response.getResults().size());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "error->" + error.getMessage());
                            }
                        }) {
                            @Override
                            protected Response<GankEntity> parseNetworkResponse(NetworkResponse response) {
                                if (response.statusCode == HttpURLConnection.HTTP_OK) {
                                    return Response.success(JSON.parseObject(new String(response.data), GankEntity.class), HttpHeaderParser.parseCacheHeaders(response));
                                } else {
                                    return Response.error(new VolleyError("请求服务器出错了"));
                                }
                            }
                        });
            }
        });
    }

    public static void router(Context context) {
        context.startActivity(new Intent(context, JsonRequestActivity.class));
    }
}
