package cl.ucn.disc.dsm.pictwin.frontend.frontend;

import androidx.appcompat.app.AppCompatActivity;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cl.ucn.disc.dsm.pictwin.frontend.R;
import cl.ucn.disc.dsm.pictwin.frontend.model.Pic;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;

    FloatingActionButton btnAddTwin;
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

    }
    private void OpenCamera() {
        // Create an Intent with action as ACTION_IMAGE_CAPTURE
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Start activity for result if image capture is possible
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            userViewModel.createTwinInBackground(Pic.builder()
                    .name("pic 5")
                    .latitude(-33.4378)
                    .longitude(-70.6503)
                    .error(5.7)
                    .owner(userViewModel.getUserLiveData().getValue())
                    .picture(imageBitmap.getNinePatchChunk())
                    .build(), userViewModel.getUserLiveData().getValue().getId());
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        userViewModel.update();
    }


}