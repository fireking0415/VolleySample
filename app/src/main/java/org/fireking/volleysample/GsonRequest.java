package org.fireking.volleysample;

import android.net.http.HttpResponseCache;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.net.HttpURLConnection;

/**
 * qiaoke app
 * Created by fireking on 2017/9/16.
 */

public class GsonRequest<T> extends Request<T> {

    private Gson gson = new Gson();
    private Class<T> mClazz;
    private Response.Listener<T> mListener;

    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mClazz = clazz;
        this.mListener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        if(response.statusCode == HttpURLConnection.HTTP_OK){
            return Response.success(gson.fromJson(new String(response.data), mClazz), HttpHeaderParser.parseCacheHeaders(response));
        }else{
            return Response.error(new VolleyError("服务器请求出错了"));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
