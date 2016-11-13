package ovh.pacordonnier.telecommandevolet;

import ovh.pacordonnier.telecommandevolet.model.APIService;
import ovh.pacordonnier.telecommandevolet.model.ResultObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pcordonnier on 13/11/16.
 */

public class MainPresenter implements Presenter<MainView> {
    MainView mainView;

    @Override
    public void attachView(MainView view) {
        mainView = view;
    }

    @Override
    public void detachView() {
        mainView = null;
    }

    public void on() {
        if (mainView != null) {
            TelecommandeApplication telecommandeApplication = TelecommandeApplication.get(mainView.getContext());
            APIService apiService = telecommandeApplication.getApiService();
            apiService.on().enqueue(new Callback<ResultObject   >() {
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

    public void off() {
        if (mainView != null) {
            TelecommandeApplication telecommandeApplication = TelecommandeApplication.get(mainView.getContext());
            APIService apiService = telecommandeApplication.getApiService();
            apiService.off().enqueue(new Callback<ResultObject>() {
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
}
