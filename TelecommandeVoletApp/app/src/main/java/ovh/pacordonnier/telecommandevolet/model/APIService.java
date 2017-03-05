package ovh.pacordonnier.telecommandevolet.model;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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

    @GET("allOn")
    Call<ResultObject> allUp();

    @GET("allOff")
    Call<ResultObject> allDown();


    class Factory {
        public static APIService create(String IP) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            // add your other interceptors …
            // add logging as last interceptor
            httpClient.addInterceptor(logging);  // <-- this is the important line!
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://" + IP)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();""
            return retrofit.create(APIService.class);
        }
    }

}
