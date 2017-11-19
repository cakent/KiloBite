package com.bignerdranch.android.kilobite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kent on 11/13/17.
 */

public class DashboardFragment extends Fragment {


    private Button mGoalButton;
    private TextView mBMITextView;
    private User mUser;
    private List<Workout> workouts;
    private TextView mWeightToLoseTextView;
    private Button mWorkoutButton;
    private static Random rn = new Random();
    private static final String TAG ="DashboardFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_user_dashboard, container, false);


        mUser.setBMI(CalculateBMI(mUser.getCurrentWeight(),mUser.getCurrentHeight()));
        mUser.setGoalWeight(toGetInRange(mUser.getCurrentWeight(),mUser.getCurrentHeight(),mUser.getBMI()));
        mGoalButton = (Button) v.findViewById(R.id.SetGoals);
        mGoalButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = GoalSettingActivity.newIntent(getActivity(),1);
                startActivity(intent);
            }
        });

        mBMITextView = (TextView) v.findViewById(R.id.ActualBMI);
        mBMITextView.setText(Double.toString(mUser.getBMI()));

        mWeightToLoseTextView =(TextView) v.findViewById(R.id.CaloritesToBurn);
        mWeightToLoseTextView.setText(Double.toString(mUser.getGoalWeight()));

        mWorkoutButton = (Button) v.findViewById(R.id.WorkoutButton);
        mWorkoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG,"Got to the click");
                List<Workout> mWorkouts = createRoutine(mUser.getGoalWeight());
                for(int i =0;i<mWorkouts.size();i++){
                    Log.d(TAG,"got a routine back with workout"+mWorkouts.get(i).toString());
                    WorkoutLab.get(getActivity()).addWorkout(mWorkouts.get(i));
                    Log.d(TAG,"got a routine back with workout"+workouts);
                }

                Intent intent = new Intent(getActivity(),WorkoutListActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }






    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mUser=UserLab.get(getActivity()).getUser(1);
        WorkoutLab workoutLab = WorkoutLab.get(getActivity());
        workouts = workoutLab.getWorkouts();

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

    public static ArrayList<Workout> createRoutine(double calories){
// Creates Routine using workouts from wBank to meet user calorie burn
        //?Present long list of workouts, have user pick 5 workouts they like and any off days that are necessary, fill a daily or weekly routine to burn calories?
        //?Take input of hours per day dedicated to workout and days per week to take breaks, use that in scheduling ?
        calories  = Math.abs(calories); //Converted to only burn calories for the Demo
        List<Workout> bank = new ArrayList<Workout>();
        ArrayList<Workout> routine = new ArrayList<Workout>();
        bank = fill_wBank(bank);
        Log.d(TAG,"The current height is:"+bank.size());
        while(calories > 0) {//goes one workout over
            Workout w = bank.get(rn.nextInt(bank.size()));
            //Test for fit - if accepted delete from temp bank
            if(w.getWorkoutNum() >= calories) {
                routine.add(w); //Maybe find the minimum workout in later Sprint?
               // bank.remove(w);
                calories = 0;
            }
            else {//Eventually check for over saturated routines? - Right now just fill randomly from the wBank
                routine.add(w);
               // bank.remove(w); //only saturation fail safe
                calories = calories - w.getWorkoutNum();
            }}


        return routine; //returns completed routine with collection of individual workout activities from wBank that in total will burn the amount of calories entered(includes 1 extra WO if calBurned not divisible

    }

    static List<Workout> fill_wBank(List<Workout> wBank) { //Creates workout objects to put into wBank - calBurned_PerSession currently minimum calories burned per exercise. [Varies By body type]
        //Running - Distance Based i , "Running", 5, i*12 , i*94
        for (int i = 1; i < 60; i++) {
            Workout temp = new Workout();
            temp.setExercise("Running @ 75%");
            temp.setReps(i);
            temp.setWorkoutNum(i * 94);
            temp.setCompleted(false);   //one run (1 mi) at 5mph for 12min burns 94 cal
            wBank.add(temp);
        }
        //Walking - Distance Basedi , "Walking", 3, i*20 , i*65
        for (int i = 1; i < 40; i++) {
            Workout temp = new Workout(); //walking 1 mi at 3mph for 20min burns 65 cal
            temp.setExercise("Jogging @ 50%");
            temp.setReps(i);
            temp.setWorkoutNum(i * 65);
            wBank.add(temp);
            temp.setCompleted(false);
        }
        for (int i = 1; i < 30; i++) {
            Workout temp = new Workout(); //walking 1 mi at 2mph for 30min burns 74 cali , "Walking", 2, i*30 , i*74
            temp.setExercise("Walking at 30%");
            temp.setReps(i);
            temp.setWorkoutNum(i * 74);
            temp.setCompleted(false);
            wBank.add(temp);
        }
        return wBank;
    }

}
