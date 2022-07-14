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


public final class UserViewModel extends AndroidViewModel {

    private static final Executor EXECUTOR = Executors.newFixedThreadPool(2);

    private final UserRepository userRepository = new UserRepository();

    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();

    public UserViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<User> getUserLiveData() {
        return this.userLiveData;
    }
    public void update(){
        if (this.userLiveData.getValue() == null){
            this.retrieveUserFromNetworkInBackground();
        }
    }

    private void retrieveUserFromNetworkInBackground() {
        EXECUTOR.execute(() -> {
            Optional<User> oUser = this.userRepository.retrieveUser("admin@ucn.cl", "admin123");
            oUser.ifPresent(userLiveData::postValue);
        });
    }

    public void createTwinInBackground(Pic pic, Long idUser) {
        EXECUTOR.execute(() -> {
            Optional<Twin> oTwin = this.userRepository.createdTwin(pic, idUser);
        });
    }
}

