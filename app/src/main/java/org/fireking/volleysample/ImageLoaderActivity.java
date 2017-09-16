package org.fireking.volleysample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonRequest;

import java.net.HttpURLConnection;

public class ImageLoaderActivity extends AppCompatActivity {

    private static final String TAG = ImageLoaderActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);

        final ImageView ivImageLoadSample = findViewById(R.id.ivImageLoadSample);

        findViewById(R.id.getDataForJson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolleyManager.getInstance(ImageLoaderActivity.this)
                        .addToRequestQueue(new JsonRequest<GankFuliEntity>(Request.Method.GET, "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1", null, new Response.Listener<GankFuliEntity>() {
                            @Override
                            public void onResponse(GankFuliEntity response) {
                                Log.e(TAG, "response->" + response.isError() + ", size:" + response.getResults().size());
                                VolleyManager.getInstance(ImageLoaderActivity.this).getImageLoader().get(response.getResults().get(0).getUrl(), new ImageLoader.ImageListener() {
                                    @Override
                                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                                        ivImageLoadSample.setImageBitmap(response.getBitmap());
                                    }

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.e(TAG, error.getMessage());
                                    }
                                });
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "error->" + error.getMessage());
                            }
                        }) {
                            @Override
                            protected Response<GankFuliEntity> parseNetworkResponse(NetworkResponse response) {
                                if (response.statusCode == HttpURLConnection.HTTP_OK) {
                                    return Response.success(JSON.parseObject(new String(response.data), GankFuliEntity.class), HttpHeaderParser.parseCacheHeaders(response));
                                } else {
                                    return Response.error(new VolleyError("请求服务器出错了"));
                                }
                            }
                        });
            }
        });
    }

    public static void router(Context context) {
        context.startActivity(new Intent(context, ImageLoaderActivity.class));
    }
}
