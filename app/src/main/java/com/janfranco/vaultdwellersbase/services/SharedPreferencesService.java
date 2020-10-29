package com.janfranco.vaultdwellersbase.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class SharedPreferencesService {

    private final SharedPreferences mSharedPreferences;

    public SharedPreferencesService(Activity activity) {
        mSharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public SharedPreferencesService(Context context, int fileKey) {
        String fileKeyString = Resources.getSystem().getString(fileKey);
        mSharedPreferences = context.getSharedPreferences(fileKeyString, Context.MODE_PRIVATE);
    }

    @SuppressWarnings("all")
    public <T> T readData(int key, T defaultValue) throws Exception {
        String keyString = Resources.getSystem().getString(key);

        if (defaultValue instanceof Boolean) {
            Boolean data = mSharedPreferences.getBoolean(keyString, (Boolean) defaultValue);
            return (T) data;
        } else if (defaultValue instanceof Integer) {
            Integer data = mSharedPreferences.getInt(keyString, (Integer) defaultValue);
            return (T) data;
        } else if (defaultValue instanceof String) {
            String data = mSharedPreferences.getString(keyString, (String) defaultValue);
            return (T) data;
        } else {
            throw new Exception("Unknown type");
        }
    }

    @SuppressWarnings("all")
    public <T> void writeData(int key, T data) throws Exception {
        String keyString = Resources.getSystem().getString(key);
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        if (data instanceof Boolean) {
            editor.putBoolean(keyString, (Boolean) data);
            editor.apply();
        } else if (data instanceof Integer) {
            editor.putBoolean(keyString, (Boolean) data);
            editor.apply();
        } else if (data instanceof String) {
            editor.putBoolean(keyString, (Boolean) data);
            editor.apply();
        } else {
            throw new Exception("Unknown type");
        }
    }

}
