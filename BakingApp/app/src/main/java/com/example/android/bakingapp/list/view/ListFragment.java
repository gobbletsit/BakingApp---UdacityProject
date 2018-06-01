package com.example.android.bakingapp.list.view;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.bakingapp.BakingApplication;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Recipe;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.list.presenter.ListPresenter;
import com.example.android.bakingapp.list_detail.view.ListDetailActivity;
import com.example.android.bakingapp.widget.ShowWidgetRecipesService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements ListView {

    public static final String TAG = ListFragment.class.getSimpleName();

    // koja fora bokte, nemoras ga pravit jer si ga u daggeru napravio, package private mora bit
    @Inject
    ListPresenter listPresenter;

    private LayoutInflater layoutInflater;
    private ListAdapter listAdapter;
    private RecyclerView recyclerView;

    private ArrayList<Recipe> mRecipeList = new ArrayList<>(30);
    private ArrayList<Ingredient> ingredientList = new ArrayList<>(30);
    private ArrayList<Step> stepsList = new ArrayList<>(30);


    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(){return new ListFragment();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BakingApplication) getActivity().getApplication()).createListComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate((R.layout.fragment_list), container, false);

        recyclerView = view.findViewById(R.id.recycler_list_activity);

        layoutInflater = getActivity().getLayoutInflater();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listAdapter = new ListAdapter();
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isNetworkAvailable()){
            listPresenter.setListView(this);
            recyclerView.setAdapter(listAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listPresenter.destroy();
        mRecipeList.clear();
    }

    @Override
    public void setUpView(List<Recipe> recipeList) {
        this.mRecipeList.clear();
        this.mRecipeList.addAll(recipeList);
        listAdapter.notifyDataSetChanged();

    }

    @Override
    public void onRecipeClicked(ArrayList<Ingredient> ingredientList, ArrayList<Step> stepsList) {
        Intent startListDetailIntent = new Intent(getActivity(), ListDetailActivity.class);
        startListDetailIntent.putExtra("ingredient_list", ingredientList);
        startListDetailIntent.putExtra("steps_list", stepsList);
        startActivity(startListDetailIntent);
    }

    private class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(layoutInflater.inflate(R.layout.recipe_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.recipe = mRecipeList.get(position);
            holder.recipeName.setText(holder.recipe.getName());

            ingredientList = new ArrayList<>(Arrays.asList(holder.recipe.getIngredients()));
            stepsList = new ArrayList<>(Arrays.asList(holder.recipe.getSteps()));
        }

        @Override
        public int getItemCount() {
            return mRecipeList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public Recipe recipe;

            private ViewGroup container;
            private TextView recipeName;

            public ViewHolder(View itemView) {
                super(itemView);
                this.container = itemView.findViewById(R.id.recipe_list_item_container);
                this.recipeName = itemView.findViewById(R.id.recipe_name_text_view);
                this.container.setOnClickListener(this);
                itemView.setOnClickListener(this);
                recipeName.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                // Recipe name to display on widget
                String recipeName = recipe.getName();
                listPresenter.onRecipeClick(getActivity(),ingredientList, stepsList, recipeName);
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
