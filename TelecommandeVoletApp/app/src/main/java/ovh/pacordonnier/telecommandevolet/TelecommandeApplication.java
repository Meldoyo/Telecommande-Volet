package ovh.pacordonnier.telecommandevolet;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import ovh.pacordonnier.telecommandevolet.model.APIService;

/**
 * Created by pcordonnier up 13/11/16.
 */

public class TelecommandeApplication extends Application {
    private APIService apiService;



    public static TelecommandeApplication get(Context context) {
        return (TelecommandeApplication) context.getApplicationContext();
    }

    public APIService getApiService() {
        if (apiService == null) {
            SharedPreferences sharedPreferences = getSharedPreferences("default", MODE_PRIVATE);
            apiService = APIService.Factory.create(sharedPreferences.getString("IP", null));
        }
        return apiService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = getSharedPreferences("default", MODE_PRIVATE);
        if (sharedPreferences.getString("IP", null) == null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("IP", "192.168.0.39");
            edit.apply();
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals("IP")) {
                    apiService = null;
                }
            }
        });
    }
}
