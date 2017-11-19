package com.bignerdranch.android.kilobite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    }
    @Override
    public void onPause(){
        super.onPause();
        WorkoutLab.get(getActivity()).updateWorkout(mWorkout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v =inflater.inflate(R.layout.fragment_workout, container, false);


        mExerciseName = (TextView) v.findViewById(R.id.exercise_name);
        mExerciseName.setText(mWorkout.getExercise());
        mNumOfReps =(TextView) v.findViewById(R.id.exericse_reps);
        mNumOfReps.setText(Integer.toString(mWorkout.getReps()));
        return v;
    }
}
