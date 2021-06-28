package com.example.solution2;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class IntEx implements Interceptor {
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Log.d("response", request.toString());
        return chain.proceed(request);
    }
}
