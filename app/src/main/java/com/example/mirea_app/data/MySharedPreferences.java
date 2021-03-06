package com.example.mirea_app.data;


import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceDataStore;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Set;

public class MySharedPreferences extends PreferenceDataStore {

    private static final String CONFIG_FILE_NAME = "SecureSharedPreferences";
    private static final String CONFIG_MASTER_KEY_ALIAS = "KeyAlias";
    public static final String GAME_KEY = "GameKey";
    public static final String EMAIL_KEY = "EmailKey";
    private static MySharedPreferences mInstance;
    private SharedPreferences mSharedPreferences;

    private MySharedPreferences(Context context) throws GeneralSecurityException, IOException {
        MasterKey masterKey = new MasterKey.Builder(context, CONFIG_MASTER_KEY_ALIAS)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build();

        try {
            mSharedPreferences = EncryptedSharedPreferences.create(
                    context,
                    CONFIG_FILE_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);

        } catch (Exception e) {
            // Fallback
            mSharedPreferences = context.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        }
    }

    public static MySharedPreferences getInstance(Context ctx) {
        try {
            if (mInstance == null) {
                mInstance = new MySharedPreferences(ctx);
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mInstance;
    }

    @Override
    public void putString(String key, @Nullable String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    @Override
    public void putStringSet(String key, @Nullable Set<String> values) {
        mSharedPreferences.edit().putStringSet(key, values).apply();
    }

    @Override
    public void putInt(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    @Override
    public void putLong(String key, long value) {
        mSharedPreferences.edit().putLong(key, value).apply();
    }

    @Override
    public void putFloat(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    @Override
    public void putBoolean(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    @Nullable
    @Override
    public String getString(String key, @Nullable String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
        return mSharedPreferences.getStringSet(key, defValues);
    }

    @Override
    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return mSharedPreferences.getFloat(key, defValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }
}
