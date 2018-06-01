package com.example.android.bakingapp.ingredients_list_detail.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.bakingapp.BakingApplication;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.ingredients_list_detail.presenter.IngredientsListDetailPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsListDetailFragment extends Fragment implements IngredientListDetailsView {

    public static final String TAG = IngredientsListDetailFragment.class.getSimpleName();

    @Inject
    IngredientsListDetailPresenter ingredientsListDetailPresenter;

    private RecyclerView recyclerView;
    private LayoutInflater layoutInflater;
    private IngredientsAdapter mIngredientsAdapter;

    private ArrayList<Ingredient> mIngredientsList = new ArrayList<>(30);

    public IngredientsListDetailFragment() {
        // Required empty public constructor
    }

    public static IngredientsListDetailFragment newInstance(ArrayList<Ingredient> ingredientsList){
        IngredientsListDetailFragment ingredientsListDetailFragment = new IngredientsListDetailFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("ingredient_list", ingredientsList);
        ingredientsListDetailFragment.setArguments(args);
        return ingredientsListDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BakingApplication)getActivity().getApplication()).createIngredientsListComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredients_list_detail, container, false);

        recyclerView = view.findViewById(R.id.recycler_ingredient_list_detail_activity);

        layoutInflater = getActivity().getLayoutInflater();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        mIngredientsAdapter = new IngredientsAdapter();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null && getArguments().containsKey("ingredient_list")){

            ArrayList<Ingredient> ingredientsList = getArguments().getParcelableArrayList("ingredient_list");
            Log.e(TAG, "Ingredients list size = " + ingredientsList.size());
            if (ingredientsList.size() != 0){
                ingredientsListDetailPresenter.setView(this);
                ingredientsListDetailPresenter.showDetails(ingredientsList);
                recyclerView.setAdapter(mIngredientsAdapter);
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showDetails(ArrayList<Ingredient> ingredientList) {
        mIngredientsList.clear();
        mIngredientsList.addAll(ingredientList);
        mIngredientsAdapter.notifyDataSetChanged();

    }

    private class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.ingredient_list_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Ingredient ingredient = mIngredientsList.get(position);
            holder.quantityAndMeasure.setText(String.valueOf(ingredient.getQuantity()) + " " + ingredient.getMeasure());
            holder.name.setText(ingredient.getName());
        }

        @Override
        public int getItemCount() {
            Log.e(TAG, "GET ITEM COUNT = " + mIngredientsList.size());
            return mIngredientsList.size();

        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView quantityAndMeasure;
            private TextView name;

            public ViewHolder(View itemView) {
                super(itemView);
                quantityAndMeasure = itemView.findViewById(R.id.ingredient_quantity_and_measure_text_view);
                name = itemView.findViewById(R.id.ingredient_name_text_view);
            }
        }
    }
}
