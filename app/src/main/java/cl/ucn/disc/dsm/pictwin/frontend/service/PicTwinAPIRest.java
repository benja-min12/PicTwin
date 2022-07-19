package cl.ucn.disc.dsm.pictwin.frontend.service;

import cl.ucn.disc.dsm.pictwin.frontend.model.Pic;
import cl.ucn.disc.dsm.pictwin.frontend.model.Twin;
import cl.ucn.disc.dsm.pictwin.frontend.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * The PicTwinAPIRest interface defines the API of the PicTwinAPIRest service.
 * @author Benjamin Millas
 */
public interface PicTwinAPIRest {
    /**
     *  registerUser method GET the user from the API.
     */
    @GET("/users")
    Call<User> retrieveUser(@Query("email") String email, @Query(value = "password", encoded = true) String password);
    /**
     *  CreateTwin method POST the Twin to the API.
     */
    @POST("/twins")
    Call<Twin> createTwin(@Body Pic pic,@Query("idUser") Long idUser);
}
