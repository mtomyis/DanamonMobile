package com.example.danamonmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{
    private Context context;
    private List<AllUser> notesList;

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final AllUser user = notesList.get(position);

        holder.tvUsername.setText(user.getUserName());
        holder.tvId.setText(user.getId());
        holder.tvRole.setText(user.getRole());
        holder.tvEmail.setText(user.getEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, user.getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUsername;
        public TextView tvId;
        public TextView tvRole;
        public TextView tvEmail;

        public MyViewHolder(View view) {
            super(view);
            tvId = view.findViewById(R.id.id_id_a);
            tvEmail = view.findViewById(R.id.id_email_a);
            tvUsername = view.findViewById(R.id.id_user_a);
            tvRole= view.findViewById(R.id.id_role_a);
        }
    }
    public UserAdapter(Context context, List<AllUser> notesList) {
        this.context = context;
        this.notesList = notesList;
    }
}
