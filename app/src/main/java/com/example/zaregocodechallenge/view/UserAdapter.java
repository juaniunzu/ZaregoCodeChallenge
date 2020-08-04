package com.example.zaregocodechallenge.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zaregocodechallenge.R;
import com.example.zaregocodechallenge.model.User;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class UserAdapter extends FirestoreRecyclerAdapter<User, UserAdapter.UserViewHolder> {

    private UserAdapterListener listener;

    public UserAdapter(@NonNull FirestoreRecyclerOptions<User> options, UserAdapterListener listener) {
        super(options);
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User model) {
        holder.onBind(model);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_item, parent, false);

        return new UserViewHolder(view);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewLastName;
        private TextView textViewPhoneNumber;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.entryItemTextViewName);
            textViewLastName = itemView.findViewById(R.id.entryItemTextViewLastName);
            textViewPhoneNumber = itemView.findViewById(R.id.entryItemTextViewNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onClickUserAdapter(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

        }

        public void onBind(User user) {
            textViewName.setText(user.getName());
            textViewLastName.setText(user.getLastName());
            textViewPhoneNumber.setText(user.getPhoneNumber());
        }
    }

    public interface UserAdapterListener{
        void onClickUserAdapter(DocumentSnapshot documentSnapshot, int position);
    }
}
