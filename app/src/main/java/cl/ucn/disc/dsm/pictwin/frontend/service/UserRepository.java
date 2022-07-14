package cl.ucn.disc.dsm.pictwin.frontend.service;
import android.util.Log;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


import cl.ucn.disc.dsm.pictwin.frontend.model.Pic;
import cl.ucn.disc.dsm.pictwin.frontend.model.Twin;
import cl.ucn.disc.dsm.pictwin.frontend.model.User;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;

@Slf4j
public final class UserRepository {
    private static final String BASE_URL = "http://192.168.1.121:8080";

    private final PicTwinAPIRest apiRest;

    public UserRepository() {

        Log.d("buiding reposistory", "building repository with url: " + BASE_URL);

        HttpLoggingInterceptor theLogging = new HttpLoggingInterceptor();
        theLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient theClient = new OkHttpClient.Builder()
                .addInterceptor(theLogging)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .callTimeout(50, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(theClient)
                .build();

        this.apiRest = retrofit.create(PicTwinAPIRest.class);
    }
    @Headers("Content-Type: application/json")
    public Optional<User> retrieveUser(final String email, final String password) {
        Call<User> cUser = this.apiRest.retrieveUser(email, password);
        try {
            Response<User> rUser =cUser.execute();
            if (rUser.isSuccessful()) {
                if(rUser.body() == null) {
                    return Optional.empty();
                }
                return Optional.of(rUser.body());
            }
        throw new RuntimeException("Can't Retrieve ",new HttpException(rUser));
        } catch (Exception e) {
            throw new RuntimeException("Can't Retrieve User",e);
        }

    }
    public Optional<Twin> createdTwin(final Pic pic, final Long idUser) {
        Call<Twin> cTwin = this.apiRest.createTwin(pic, idUser);
        try {
            Response<Twin> rTwin =cTwin.execute();
            if (rTwin.isSuccessful()) {
                if(rTwin.body() == null) {
                    return Optional.empty();
                }
                return Optional.of(rTwin.body());
            }
        throw new RuntimeException("Can't Retrieve ",new HttpException(rTwin));
        } catch (Exception e) {
            throw new RuntimeException("Can't Retrieve pic",e);
        }

    }
}
