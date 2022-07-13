package cl.ucn.disc.dsm.pictwin.frontend.frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import cl.ucn.disc.dsm.pictwin.frontend.R;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        userViewModel.update();
    }


}