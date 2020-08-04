package com.example.zaregocodechallenge.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zaregocodechallenge.R;
import com.example.zaregocodechallenge.databinding.FragmentDetailBinding;
import com.example.zaregocodechallenge.model.User;

public class DetailFragment extends Fragment {

    public static final String USER = "user";
    public static final String USER_ID = "userId";
    private TextView name;
    private TextView lastName;
    private TextView number;
    private Button buttonEdit;
    private DetailFragmentListener listener;
    private FragmentDetailBinding binding;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        name = binding.fragmentDetailTextViewName;
        lastName = binding.fragmentDetailTextViewLastName;
        number = binding.fragmentDetailTextViewNumber;
        buttonEdit = binding.fragmentDetailButtonEdit;

        Bundle userBundle = getArguments();
        final User clickedUser = (User) userBundle.getSerializable(USER);
        final String clickedUserId = userBundle.getString(USER_ID);

        name.setText(clickedUser.getName());
        lastName.setText(clickedUser.getLastName());
        number.setText(clickedUser.getPhoneNumber());

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickEditEntry(clickedUserId, clickedUser);
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (DetailFragmentListener) context;
    }

    public interface DetailFragmentListener{
        void onClickEditEntry(String userId, User user);
    }

}
