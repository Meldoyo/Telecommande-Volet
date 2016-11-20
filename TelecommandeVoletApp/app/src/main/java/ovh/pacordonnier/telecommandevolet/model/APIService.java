package ovh.pacordonnier.telecommandevolet.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pcordonnier up 13/11/16.
 */

public interface APIService {
    @GET("on")
    Call<ResultObject> on(@Query("salle") String salle);

    @GET("off")
    Call<ResultObject> off(@Query("salle") String salle);

    @GET("get")
    Call<List<Room>> get();

    class Factory {
        public static APIService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.39/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(APIService.class);
        }
    }

}
