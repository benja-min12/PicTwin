package cl.ucn.disc.dsm.pictwin.frontend.service;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


import cl.ucn.disc.dsm.pictwin.frontend.model.User;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Slf4j
public final class UserRepository {
    private static final String BASE_URL = "http://192.168.1.121:8080";

    private final PicTwinAPIRest apiRest;

    public UserRepository() {

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
}
