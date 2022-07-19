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

/**
 * Adapter for the recycler view.
 * @author Benjamin Millas
 */
public final class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    /**
     * User
     */
    private User user;

    /**
     * Constructor.
     */
    public UserAdapter() {
    }

    /**
     * OnCreateViewHolder method.
     * @param parent the parent
     * @param viewType the view type
     * @return the view holder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View twinView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.row_twin, parent, false);
        return new ViewHolder(twinView);
    }

    /**
     * OnBindViewHolder method.
     * @param holder  the holder
     * @param position the position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Twin twin = this.user.getTwins().get(position);

        holder.mine.setText(String.format("%s", twin.getMy().getId()));
        holder.yours.setText(String.format("%s", twin.getYours().getId()));

    }

    /**
     * GetItemCount method.
     * @return the item count
     */
    @Override
    public int getItemCount() {
        if(this.user == null) {
            return 0;
        }
        return this.user.getTwins().size();
    }

    /**
     * SetUser method.
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * ViewHolder class.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * Mine text view.
         */
        protected TextView mine;
        /**
         * Yours text view.
         */
        protected TextView yours;
        /**
         * name text view.
         */
        protected TextView NameMY;
        /**
         * name text view.
         */
        protected TextView NameYour;

        /**
         * Constructor.
         * @param view
         */
        public ViewHolder(@NonNull View view) {
            super(view);
            this.mine = view.findViewById(R.id.rt_tv_mine);
            this.yours = view.findViewById(R.id.rt_tv_yours);

        }
    }
}

