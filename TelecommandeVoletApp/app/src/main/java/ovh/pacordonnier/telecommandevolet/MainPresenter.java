package ovh.pacordonnier.telecommandevolet;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import ovh.pacordonnier.telecommandevolet.model.APIService;
import ovh.pacordonnier.telecommandevolet.model.ResultObject;
import ovh.pacordonnier.telecommandevolet.model.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pcordonnier up 13/11/16.
 */

public class MainPresenter implements Presenter<MainView> {
    MainView mainView;
    private String IP;

    @Override
    public void attachView(MainView view) {
        mainView = view;
    }

    @Override
    public void detachView() {
        mainView = null;
    }

    public void get() {
        if (mainView != null) {
            TelecommandeApplication telecommandeApplication = TelecommandeApplication.get(mainView.getContext());
            APIService apiService = telecommandeApplication.getApiService();
            apiService.get().enqueue(new Callback<List<Room>>() {
                @Override
                public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                    mainView.resultGet(response.body());
                }

                @Override
                public void onFailure(Call<List<Room>> call, Throwable t) {
                    mainView.resultNotOk();
                }
            });
        }
    }

    public void up(Room room) {
        if (mainView != null) {
            TelecommandeApplication telecommandeApplication = TelecommandeApplication.get(mainView.getContext());
            APIService apiService = telecommandeApplication.getApiService();
            apiService.on(room.getName()).enqueue(new Callback<ResultObject>() {
                @Override
                public void onResponse(Call<ResultObject> call, Response<ResultObject> response) {
                    mainView.resultOK();
                }

                @Override
                public void onFailure(Call<ResultObject> call, Throwable t) {
                    mainView.resultNotOk();
                }
            });
        }
    }

    public void down(Room room) {
        if (mainView != null) {
            TelecommandeApplication telecommandeApplication = TelecommandeApplication.get(mainView.getContext());
            APIService apiService = telecommandeApplication.getApiService();
            apiService.off(room.getName()).enqueue(new Callback<ResultObject>() {
                @Override
                public void onResponse(Call<ResultObject> call, Response<ResultObject> response) {
                    mainView.resultOK();
                }

                @Override
                public void onFailure(Call<ResultObject> call, Throwable t) {
                    mainView.resultNotOk();
                }
            });
        }
    }

    public void allDown() {
        if (mainView != null) {
            TelecommandeApplication telecommandeApplication = TelecommandeApplication.get(mainView.getContext());
            APIService apiService = telecommandeApplication.getApiService();
            apiService.allDown().enqueue(new Callback<ResultObject>() {
                @Override
                public void onResponse(Call<ResultObject> call, Response<ResultObject> response) {
                    mainView.resultOK();
                }

                @Override
                public void onFailure(Call<ResultObject> call, Throwable t) {
                    mainView.resultNotOk();
                }
            });
        }
    }

    public void allUp() {
        if (mainView != null) {
            TelecommandeApplication telecommandeApplication = TelecommandeApplication.get(mainView.getContext());
            APIService apiService = telecommandeApplication.getApiService();
            apiService.allUp().enqueue(new Callback<ResultObject>() {
                @Override
                public void onResponse(Call<ResultObject> call, Response<ResultObject> response) {
                    mainView.resultOK();
                }

                @Override
                public void onFailure(Call<ResultObject> call, Throwable t) {
                    mainView.resultNotOk();
                }
            });
        }
    }

    public String getIP() {
        String byDefault = "default";
        SharedPreferences sharedPreferences =
            mainView.getContext().getSharedPreferences(byDefault, Context.MODE_PRIVATE);
        return sharedPreferences.getString("IP", null);
    }

    public void setIP(String IP) {
        String byDefault = "default";
        SharedPreferences sharedPreferences =
                mainView.getContext().getSharedPreferences(byDefault, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("IP", IP);
        editor.commit();
    }
}
