package com.bignerdranch.android.kilobite;

import android.app.PendingIntent;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bignerdranch.android.kilobite.database.WorkoutBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kent on 11/10/17.
 */

public class GoalSettingFragment extends Fragment {
    private EditText mCurrentWeight;
    private EditText mCurrentHeight;

    private Button mWorkoutButton;
    private User mUser;
    private List<Workout> mWorkouts;
    private CheckBox sunCheckBox;
    private CheckBox monCheckBox;
    private CheckBox tuesCheckBox;
    private CheckBox wedCheckBox;
    private CheckBox thursCheckBox;
    private CheckBox friCheckBox;
    private CheckBox satCheckBox;


    private List<String> mPrefDays;

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
        sunCheckBox = (CheckBox) v.findViewById(R.id.sun);
        monCheckBox = (CheckBox) v.findViewById(R.id.mon);
        tuesCheckBox = (CheckBox) v.findViewById(R.id.tues);
        wedCheckBox = (CheckBox) v.findViewById(R.id.wed);
        thursCheckBox = (CheckBox) v.findViewById(R.id.thurs);
        friCheckBox = (CheckBox) v.findViewById(R.id.frid);
        satCheckBox = (CheckBox) v.findViewById(R.id.sat);


        mWorkoutButton = (Button) v.findViewById(R.id.getWODSButton);
        mWorkoutButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){


                List<String> userPrefDays = new ArrayList<>() ;



                if(sunCheckBox.isChecked())
                userPrefDays.add("Sunday");
                else
                    userPrefDays.remove("Sunday");
                if(monCheckBox.isChecked())
                    userPrefDays.add("Monday");
                else
                    userPrefDays.remove("Monday");
                if(tuesCheckBox.isChecked())
                    userPrefDays.add("Tuesday");
                else
                    userPrefDays.remove("Tuesday");
                if(wedCheckBox.isChecked())
                    userPrefDays.add("Wednesday");
                else
                    userPrefDays.remove("Wednesday");
                if(thursCheckBox.isChecked())
                    userPrefDays.add("Thursday");
                else
                    userPrefDays.remove("Thursday");
                if(friCheckBox.isChecked())
                    userPrefDays.add("Friday");
                else
                    userPrefDays.remove("Friday");
                if(satCheckBox.isChecked())
                    userPrefDays.add("Saturday");
                else
                    userPrefDays.remove("Saturday");

                mUser.setPrefDays(userPrefDays);

                if(mUser.getCurrentHeight()!=0&&mUser.getCurrentWeight()!=0) {
                   int userWeight =mUser.getCurrentWeight();
                    int userHeight =mUser.getCurrentHeight();

                    double BMI = CalculateBMI(userWeight,userHeight);
                    mUser.setBMI(BMI);
                    double goalWeight = toGetInRange(userWeight,userHeight,BMI);
                    mUser.setGoalWeight(goalWeight);
                    for(int i =0;i<mWorkouts.size();i++){
                    updateWorkoutReps(goalWeight,i);}
                    Intent intent = new Intent(getActivity(), WorkoutListActivity.class);
                    startActivity(intent);
                }


            }
        });





        return v;
    }



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int userID = (int) getActivity().getIntent().getSerializableExtra(GoalSettingActivity.EXTRA_USER_ID);
        mWorkouts=WorkoutLab.get(getActivity()).getWorkouts();
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

    private void updateWorkout(Workout w){
        WorkoutLab.get(getActivity()).updateWorkout(w);
        Log.d(TAG,"Updating the workout");
    }

    private void updateWorkoutReps(double goalCalBurned, int index){
        Workout baseworkout = mWorkouts.get(index);
        int baseNum = baseworkout.getWorkoutNum();
        int ratio = (int) ((goalCalBurned*.01)/18)/baseNum;
        int[] newReps = baseworkout.getReps();

            newReps[0] = newReps[0]*ratio;

        baseworkout.setReps(newReps);
        updateWorkout(baseworkout);
    }

    public double CalculateBMI(int userWeight, int userHeight){

        double height=userHeight;
        double weight=userWeight;
        double BMI;
        BMI =(weight)/(height*height)*703;

        return BMI;
    }

    public double toGetInRange(int UserWeight, int userHeight, double userBMI){
        double difference = userBMI-24.9;

        double BMItoPounds = (userHeight*0.0254)*(userHeight*0.0254)/0.453592;
        double toGetInRange = difference*BMItoPounds;
        return toGetInRange*3500;
    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?

    }



}
