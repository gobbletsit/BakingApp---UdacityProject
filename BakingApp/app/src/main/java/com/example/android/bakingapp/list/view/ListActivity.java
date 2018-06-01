package com.example.android.bakingapp.list.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.bakingapp.R;

public class ListActivity extends AppCompatActivity {

    public static final String LIST_FRAG = "LIST_FRAG";

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ListFragment listFragment = (ListFragment) fragmentManager.findFragmentByTag(LIST_FRAG);

        if (listFragment == null){
            listFragment = ListFragment.newInstance();
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.root_activity_list, listFragment, LIST_FRAG);
        fragmentTransaction.commit();

    }
}
