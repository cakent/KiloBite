package com.bignerdranch.android.kilobite;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bignerdranch.android.kilobite.database.WorkoutBaseHelper;

/**
 * Created by kent on 11/10/17.
 */

public class GoalSettingFragment extends Fragment {
    private EditText mCurrentWeight;
    private EditText mCurrentHeight;

    private Button mWorkoutButton;
    private User mUser;
    private static final String TAG ="GoalSettingFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        View v = inflater.inflate(R.layout.fragment_goals, container,false);
        Log.d(TAG,"The current height is:");


        mCurrentHeight = (EditText) v.findViewById(R.id.CurrentHeight);
        mCurrentHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUser.setCurrentHeight(Integer.parseInt(s.toString()));
                Log.d(TAG,("Current Height Fired"+mUser.getCurrentHeight()));
                updateUser();
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
                mUser.setCurrentWeight(Integer.parseInt(s.toString()));
                Log.d(TAG,"Current Weight Fired");
                updateUser();
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



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int userID = (int) getActivity().getIntent().getSerializableExtra(GoalSettingActivity.EXTRA_USER_ID);
        mUser=UserLab.get(getActivity()).getUser(userID);

    }
    @Override
    public void onPause(){
        super.onPause();
        UserLab.get(getActivity()).updateUser(mUser);
    }

    private void updateUser(){
        UserLab.get(getActivity()).updateUser(mUser);
        Log.d(TAG,"Updating the user");
    }

}
