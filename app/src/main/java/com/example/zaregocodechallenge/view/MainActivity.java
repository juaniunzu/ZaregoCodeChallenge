package com.example.zaregocodechallenge.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.zaregocodechallenge.R;
import com.example.zaregocodechallenge.model.User;
import com.google.firebase.firestore.DocumentSnapshot;

public class MainActivity extends AppCompatActivity implements HomeFragment.HomeFragmentListener, DetailFragment.DetailFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(new HomeFragment());
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activityMainFragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClickFab() {
        setFragment(new AddUserFragment());
    }

    @Override
    public void onClickUserHomeFragment(DocumentSnapshot documentSnapshot, int position) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle detail = new Bundle();
        User user = documentSnapshot.toObject(User.class);

        detail.putSerializable(DetailFragment.USER, user);
        detail.putString(DetailFragment.USER_ID, documentSnapshot.getId());
        detailFragment.setArguments(detail);
        setFragment(detailFragment);
    }

    @Override
    public void onClickEditEntry(String userId, User user) {
        AddUserFragment addUserFragment = new AddUserFragment();
        Bundle modifyUser = new Bundle();
        modifyUser.putString(AddUserFragment.USER_ID, userId);
        modifyUser.putSerializable(AddUserFragment.USER, user);
        addUserFragment.setArguments(modifyUser);
        setFragment(addUserFragment);
    }
}
