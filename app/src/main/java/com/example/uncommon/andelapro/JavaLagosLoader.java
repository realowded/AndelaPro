package com.example.uncommon.andelapro;

import android.content.AsyncTaskLoader;
import android.content.Context;


import java.util.List;

/**
 * Created by uncommon on 9/4/17.
 */

public class JavaLagosLoader  extends AsyncTaskLoader<List<Javalagos>> {
    private String mUrl;

    public JavaLagosLoader(Context context, String url){
        super(context);
        mUrl = url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Javalagos> loadInBackground() {
        if (mUrl == null){
            return  null;
        }

        List<Javalagos> javaLagList = QueryLagos.fetchJavaDevLag(mUrl);
        return javaLagList;
    }
}
