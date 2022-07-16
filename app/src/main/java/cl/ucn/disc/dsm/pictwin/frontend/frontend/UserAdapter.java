package cl.ucn.disc.dsm.pictwin.frontend.frontend;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import cl.ucn.disc.dsm.pictwin.frontend.R;
import cl.ucn.disc.dsm.pictwin.frontend.model.Twin;
import cl.ucn.disc.dsm.pictwin.frontend.model.User;

public final class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private User user;

    public UserAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View twinView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.row_twin, parent, false);
        return new ViewHolder(twinView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Twin twin = this.user.getTwins().get(position);

        holder.mine.setText(String.format("%s", twin.getMy().getPicture()));
        holder.yours.setText(String.format("%s", twin.getYours().getPicture()));
    }

    @Override
    public int getItemCount() {
        if(this.user == null) {
            return 0;
        }
        return this.user.getTwins().size();
    }
    public void setUser(User user) {
        this.user = user;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView mine;
        protected TextView yours;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.mine = view.findViewById(R.id.rt_tv_mine);
            this.yours = view.findViewById(R.id.rt_tv_yours);
        }
    }
}

