package ovh.pacordonnier.telecommandevolet.model;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by pcordonnier on 13/11/16.
 */

public interface APIService {
    @GET("on")
    Call<ResultObject> on();

    @GET("off")
    Call<ResultObject> off();

    class Factory {
        public static APIService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.155/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(APIService.class);
        }
    }

}
