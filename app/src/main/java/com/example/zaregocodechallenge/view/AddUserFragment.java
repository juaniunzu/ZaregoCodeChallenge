package com.example.zaregocodechallenge.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.zaregocodechallenge.R;
import com.example.zaregocodechallenge.controller.UserController;
import com.example.zaregocodechallenge.databinding.FragmentAddUserBinding;
import com.example.zaregocodechallenge.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class AddUserFragment extends Fragment {

    public static final String USER_ID = "userId";
    public static final String USER = "user";
    private TextInputEditText editTextName;
    private TextInputEditText editTextLastName;
    private TextInputEditText editTextNumber;
    private UserController userController;
    private Boolean updateUser = false;
    private Bundle modifyEntry;
    private FragmentAddUserBinding binding;

    public AddUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAddUserBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        setHasOptionsMenu(true);
        userController = new UserController();

        editTextName = binding.fragmentAddUserTextInputEditTextName;
        editTextLastName = binding.fragmentAddUserTextInputEditTextLastName;
        editTextNumber = binding.fragmentAddUserTextInputEditTextNumber;

        modifyEntry = getArguments();
        if(modifyEntry == null){
            Toast.makeText(getContext(), "Create new entry", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Modify entry", Toast.LENGTH_SHORT).show();
            User user = (User) modifyEntry.getSerializable(USER);
            editTextName.setText(user.getName());
            editTextLastName.setText(user.getLastName());
            editTextNumber.setText(user.getPhoneNumber());
            updateUser = true;

        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.new_entry_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_entry:
                if (checkFields()) {
                    if(updateUser){
                        updateUser(modifyEntry.getString(USER_ID));
                        getFragmentManager().popBackStack();
                        getFragmentManager().popBackStack();
                        hideKeyboardFrom(getContext(), binding.getRoot());
                    } else {
                        saveUser();
                        getFragmentManager().popBackStack();
                        hideKeyboardFrom(getContext(), binding.getRoot());
                    }
                } else {
                    Toast.makeText(getContext(), "Please complete all fields", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Boolean checkFields() {
        String name = editTextName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String number = editTextNumber.getText().toString();
        return !(name.trim().isEmpty() || lastName.trim().isEmpty() || number.trim().isEmpty());
    }

    private void updateUser(String userId){
        String name = editTextName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String number = editTextNumber.getText().toString();

        userController.modifyUser(getContext(), userId, new User(name, lastName, number));
    }

    private void saveUser() {
        String name = editTextName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String number = editTextNumber.getText().toString();

        userController.addUser(getContext(), new User(name, lastName, number));
    }

    private void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
