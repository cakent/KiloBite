package com.bignerdranch.android.kilobite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.UUID;

/**
 * Created by kent on 11/5/17. This is the fragment that will contain the actual workout details. As well as the finish button and the start tracking button etc.
 */

public class WorkoutFragment extends Fragment {

    private Workout mWorkout;
    private TextView mExerciseName;
    private TextView mNumOfReps;
    private static final String ARG_WORKOUT_ID="workout_id";
    private Button mCompleteButton;
    private User mUser;
    private static final String TAG ="WorkoutFragment";

    public static WorkoutFragment newInstance(UUID workoutID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_WORKOUT_ID,workoutID);
        WorkoutFragment fragment = new WorkoutFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID workoutId = (UUID) getArguments().getSerializable(ARG_WORKOUT_ID);
        mWorkout= WorkoutLab.get(getActivity()).getWorkout(workoutId);

        mUser=UserLab.get(getActivity()).getUser(1);
        Log.d(TAG,"workouts completed:"+mUser.getWorkoutsCompleted());
    }
    @Override
    public void onPause(){
        super.onPause();
        WorkoutLab.get(getActivity()).updateWorkout(mWorkout);
        Log.d(TAG,"workouts completed:"+mUser.getWorkoutsCompleted());
        UserLab.get(getActivity()).updateUser(mUser);
        Log.d(TAG,"workouts completed:"+mUser.getWorkoutsCompleted());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v =inflater.inflate(R.layout.fragment_workout, container, false);

        mCompleteButton = (Button) v.findViewById(R.id.completeButton);
        mCompleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                double newGoalWeight = mUser.getGoalWeight();
               mUser.addWorkoutCompleted();
                mUser.setGoalWeight((newGoalWeight-(newGoalWeight/18)));
                mWorkout.setCompleted(true);
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                startActivity(intent);

            }
        });
        String[] workouts=mWorkout.getExercise();
        int[] reps = mWorkout.getReps();

         String workoutString ="";
        String repString="";

        workoutString=workouts[0];
        for(int i =1;i<workouts.length;i++){
            workoutString=workoutString+"/"+workouts[i];
        }
        repString=String.valueOf(reps[0]);
        for(int i =1;i<reps.length;i++){
            repString=repString+"/"+String.valueOf(reps[i]);
        }
        mExerciseName = (TextView) v.findViewById(R.id.exercise_name);
        mExerciseName.setText(workoutString);
        mNumOfReps =(TextView) v.findViewById(R.id.exericse_reps);
        mNumOfReps.setText(repString);

        return v;
    }
}
