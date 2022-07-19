package cl.ucn.disc.dsm.pictwin.frontend.frontend;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cl.ucn.disc.dsm.pictwin.frontend.model.Pic;
import cl.ucn.disc.dsm.pictwin.frontend.model.Twin;
import cl.ucn.disc.dsm.pictwin.frontend.model.User;
import cl.ucn.disc.dsm.pictwin.frontend.service.UserRepository;

/**
 * The UserViewModel class.
 * @author Benjamin Millas
 */
public final class UserViewModel extends AndroidViewModel {
    /**
     * Executor.
     */
    private static final Executor EXECUTOR = Executors.newFixedThreadPool(2);
    /**
     * The UserRepository.
     */
    private final UserRepository userRepository = new UserRepository();
    /**
     *  UserliveData is a MutableLiveData that contains the user.
     */
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();

    /**
     * The UserViewModel constructor.
     * @param application
     */
    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * The getUser method.
     * @return the user
     */
    public LiveData<User> getUserLiveData() {
        return this.userLiveData;
    }

    /**
     * update method to update the user.
     */
    public void update(){
        if (this.userLiveData.getValue() == null){
            this.retrieveUserFromNetworkInBackground();
        }
    }

    /**
     * The retrieveUserFromNetworkInBackground method.
     */
    private void retrieveUserFromNetworkInBackground() {
        EXECUTOR.execute(() -> {
            Optional<User> oUser = this.userRepository.retrieveUser("admin@ucn.cl", "admin123");
            oUser.ifPresent(userLiveData::postValue);
        });
    }

    /**
     * The createTwin method.
     * @param pic   the pic
     * @param idUser the idUser
     */
    public void createTwinInBackground(Pic pic, Long idUser) {
        EXECUTOR.execute(() -> {
            Optional<Twin> oTwin = this.userRepository.createdTwin(pic, idUser);
        });
    }
}

