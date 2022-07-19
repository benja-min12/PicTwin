package cl.ucn.disc.dsm.pictwin.frontend.frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cl.ucn.disc.dsm.pictwin.frontend.R;
import cl.ucn.disc.dsm.pictwin.frontend.model.Pic;

/**
 * MainActivity.
 * @author Benjamin Millas
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The ViewModel.
     */
    private UserViewModel userViewModel;
    /**
     *  btn_camera.
     */
    FloatingActionButton btnAddTwin;

    /**
     * OnCreate method.
     * @param savedInstanceState the saved instance state
     */
    @SuppressLint("notifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.am_rv_twins);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        UserAdapter adapter = new UserAdapter();

        recyclerView.setAdapter(adapter);

        this.userViewModel= ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getApplication())
                .create(UserViewModel.class);

        userViewModel.getUserLiveData().observe(this, user -> {
            adapter.setUser(user);

            adapter.notifyDataSetChanged();
        });

        btnAddTwin = findViewById(R.id.CreateTwin);

        btnAddTwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenCamera();
            }

        });
        // Menu in MaterialToolbar
        MaterialToolbar toolbar = findViewById(R.id.am_mt_toolbar);
        toolbar.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.menu_theme) {
                // Check the current theme
                if (AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                return true;
            }
            return false;
        });

    }

    /**
     * OpenCamera.
     */
    private void OpenCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    /**
     * OnActivityResult method.
     * @param requestCode the request code
     * @param resultCode the result code
     * @param data the data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            userViewModel.createTwinInBackground(Pic.builder()
                    .name("pic 5")
                    .latitude(-33.4)
                    .longitude(-70.6503)
                    .error(5.7)
                    .owner(userViewModel.getUserLiveData().getValue())
                    .picture(imageBitmap.getNinePatchChunk())
                    .build(), userViewModel.getUserLiveData().getValue().getId());
        }

    }

    /**
     * OnStart method.
     */
    @Override
    protected void onStart() {
        super.onStart();
        userViewModel.update();
    }


}