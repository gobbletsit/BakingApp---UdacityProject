package com.example.android.bakingapp.list_detail.view;


import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.bakingapp.BakingApplication;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Ingredient;
import com.example.android.bakingapp.data.Step;
import com.example.android.bakingapp.detail.view.StepDetailActivity;
import com.example.android.bakingapp.ingredients_list_detail.view.IngredientsListDetailActivity;
import com.example.android.bakingapp.list_detail.presenter.ListDetailPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListDetailFragment extends Fragment implements ListDetailView {

    public static final String TAG = ListDetailFragment.class.getSimpleName();

    private Callback callback;

    @Inject
    ListDetailPresenter listDetailPresenter;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private ListDetailAdapter listDetailAdapter;

    private Button ingredientsButton;

    ArrayList<Step> mStepsList = new ArrayList<>(30);


    public ListDetailFragment() {
        // Required empty public constructor
    }

    public static ListDetailFragment newInstance(ArrayList<Ingredient> ingredientList, ArrayList<Step> stepsList){
        ListDetailFragment fragment = new ListDetailFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("ingredient_list", ingredientList);
        args.putParcelableArrayList("steps_list", stepsList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        ((BakingApplication)getActivity().getApplication()).createListDetailComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_detail, container, false);

        recyclerView = view.findViewById(R.id.recycler_list_detail_activity);
        ingredientsButton = view.findViewById(R.id.fragment_list_detail_ingredients_button);

        layoutInflater = getActivity().getLayoutInflater();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listDetailAdapter = new ListDetailAdapter();
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null && getArguments().containsKey("ingredient_list")
        && getArguments().containsKey("steps_list")){
            ArrayList<Ingredient> ingredient_list = getArguments().getParcelableArrayList("ingredient_list");
            ArrayList<Step> steps_list = getArguments().getParcelableArrayList("steps_list");
            listDetailPresenter.setView(this);
            listDetailPresenter.showListDetails(steps_list);
            recyclerView.setAdapter(listDetailAdapter);
            ingredientsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // invokes a callback in a view method, not sure if it's the right way to do it
                    listDetailPresenter.onIngredientsButtonClick(ingredient_list);
                }
            });
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
    public void showListDetails(ArrayList<Step> stepsList) {
        mStepsList.clear();
        mStepsList.addAll(stepsList);
        listDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStepClick(Step step, int position, ArrayList<Step> stepArrayList) {
        callback.onStepClick(step, position, stepArrayList);
    }

    @Override
    public void onIngredientsButtonClick(ArrayList<Ingredient> ingredients) {
        callback.onIngredientsClick(ingredients);
    }

    private class ListDetailAdapter extends RecyclerView.Adapter<ListDetailAdapter.ViewHolder>{

        @Override
        public ListDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.list_detail_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ListDetailAdapter.ViewHolder holder, int position) {
            holder.step = mStepsList.get(position);
            holder.stepDescription.setText(holder.step.getShortDescription());
            holder.position = holder.getAdapterPosition();
        }

        @Override
        public int getItemCount() {
            return mStepsList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public Step step;
            int position;
            private ViewGroup container;
            private Button stepDescription;

            public ViewHolder(View itemView) {
                super(itemView);
                this.container = itemView.findViewById(R.id.list_detail_item_container);
                this.stepDescription = itemView.findViewById(R.id.list_detail_step_button);
                this.container.setOnClickListener(this);
                this.stepDescription.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

                // invokes a callback from a view method, not it sure if it's the right way to do it
                listDetailPresenter.onStepDescriptionClick(step, position, mStepsList);
            }
        }
    }

    public interface Callback {

        void onStepClick(Step step, int position, ArrayList<Step> stepArrayList);

        void onIngredientsClick(ArrayList<Ingredient> ingredientsList);
    }
}
