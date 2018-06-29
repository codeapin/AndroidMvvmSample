package id.codeapin.samplejetpack.data.pref;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

import id.codeapin.samplejetpack.injection.annotation.ApplicationContext;

public class PrefHelper {

    private static final String PREF_FILE_NAME = "rke_mobile_pref_file";

    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    @Inject
    public PrefHelper(@ApplicationContext Context context, Gson gson) {
        sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        this.gson = gson;
    }
}
