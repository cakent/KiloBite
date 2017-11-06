package com.bignerdranch.android.kilobite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by kent on 11/5/17. This is the fragment that will contain the actual workout details. As well as the finish button and the start tracking button etc.
 */

public class WorkoutFragment extends Fragment {

    private Workout mWorkout;
    private TextView mExerciseName;
    private TextView mNumOfReps;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mWorkout=new Workout();
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
