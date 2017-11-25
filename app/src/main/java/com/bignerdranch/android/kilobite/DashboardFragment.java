package com.bignerdranch.android.kilobite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kent on 11/13/17.
 */

public class DashboardFragment extends Fragment {



    private TextView mBMITextView;
    private User mUser;
    private TextView mWodsCompleted;
    private TextView mWeightToLoseTextView;
    private TextView mExerciseLabel;
    private TextView mRepLabel;
    private Button mWorkoutButton;
    private List<Workout> workouts;
    private static final String TAG ="DashboardFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_user_dashboard, container, false);






        mBMITextView = (TextView) v.findViewById(R.id.ActualBMI);
        mBMITextView.setText(Double.toString(mUser.getBMI()));

        mWodsCompleted=(TextView) v.findViewById(R.id.WODsCompleted);
        mWodsCompleted.setText(mUser.getWorkoutsCompleted()+"/"+"18");

        mWeightToLoseTextView =(TextView) v.findViewById(R.id.CaloritesToBurn);
        mWeightToLoseTextView.setText(Double.toString(mUser.getGoalWeight()));

        mExerciseLabel =(TextView) v.findViewById(R.id.ExerciseLbl);
        mRepLabel = (TextView) v.findViewById(R.id.RepLbl);

        if(mUser.getWorkoutsCompleted()<workouts.size()){

            Workout mWorkout = workouts.get(mUser.getWorkoutsCompleted());
            String[] exercises=mWorkout.getExercise();
            int[] reps = mWorkout.getReps();

            String workoutString ="";
            String repString="";

            workoutString=exercises[0];
            for(int i =1;i<exercises.length;i++){
                workoutString=workoutString+"/"+exercises[i];
            }
            repString=String.valueOf(reps[0]);
            for(int i =1;i<reps.length;i++){
                repString=repString+"/"+String.valueOf(reps[i]);
            }


            mExerciseLabel.setText(workoutString);
            mRepLabel.setText(repString);
        }

        mWorkoutButton = (Button) v.findViewById(R.id.workoutButton);
        mWorkoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG,"Got to the click");


                Intent intent = new Intent(getActivity(),WorkoutListActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.change_goals:
                Intent intent = GoalSettingActivity.newIntent(getActivity(),1);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mUser=UserLab.get(getActivity()).getUser(1);
        WorkoutLab workoutLab = WorkoutLab.get(getActivity());
        workouts = workoutLab.getWorkouts();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_user_dashboard,menu);
    }







}
