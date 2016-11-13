package ovh.pacordonnier.telecommandevolet;

import android.app.Application;
import android.content.Context;

import ovh.pacordonnier.telecommandevolet.model.APIService;

/**
 * Created by pcordonnier on 13/11/16.
 */

public class TelecommandeApplication extends Application {
    private APIService apiService;



    public static TelecommandeApplication get(Context context) {
        return (TelecommandeApplication) context.getApplicationContext();
    }

    public APIService getApiService() {
        if (apiService == null) {
            apiService = APIService.Factory.create();
        }
        return apiService;
    }
}
