package com.bignerdranch.android.kilobite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by kent on 11/10/17.
 */

public class GoalSettingFragment extends Fragment {
    private EditText mCurrentWeight;
    private EditText mCurrentHeight;
    private EditText mGoalWeight;
    private Button mWorkoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_goals, container,false);

        mCurrentHeight = (EditText) v.findViewById(R.id.CurrentHeight);
        mCurrentHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mCurrentWeight = (EditText) v.findViewById(R.id.CurrentWeight);
        mCurrentWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mGoalWeight = (EditText) v.findViewById(R.id.GoalWeight);
        mGoalWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mWorkoutButton = (Button) v.findViewById(R.id.getWODSButton);
        mWorkoutButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(),WorkoutListActivity.class);
                startActivity(intent);
            }
        });





        return v;
    }



}
