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

    private TextView mExercise1Label;
    private TextView mExercise2Label;
    private TextView mExercise3Label;
    private TextView mExercise4Label;
    private TextView mExercise5Label;
    private TextView mRep1Label;
    private TextView mRep2Label;
    private TextView mRep3Label;
    private TextView mRep4Label;
    private TextView mRep5Label;
    private TextView mStructureLabel;
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
        String[] exercises=mWorkout.getExercise();
        int[] reps = mWorkout.getReps();

        mStructureLabel =(TextView) v.findViewById(R.id.StructureLbl);

        mExercise1Label =(TextView) v.findViewById(R.id.Exercise1Lbl);
        mRep1Label = (TextView) v.findViewById(R.id.reps1Lbl);
        mExercise2Label =(TextView) v.findViewById(R.id.Exercise2Lbl);
        mRep2Label = (TextView) v.findViewById(R.id.reps2Lbl);
        mExercise3Label =(TextView) v.findViewById(R.id.Exercise3lbl);
        mRep3Label = (TextView) v.findViewById(R.id.reps3lbl);
        mExercise4Label =(TextView) v.findViewById(R.id.Exercise4Lbl);
        mRep4Label = (TextView) v.findViewById(R.id.reps4lbl);
        mExercise5Label =(TextView) v.findViewById(R.id.Exercise5Lbl);
        mRep5Label = (TextView) v.findViewById(R.id.reps5lbl);

        mStructureLabel.setText(""+Integer.toString(reps[0])+" "+exercises[0]);
        mExercise1Label.setText(exercises[1]);
        mRep1Label.setText(Integer.toString(reps[1]));
        mExercise2Label.setText(exercises[2]);
        mRep2Label.setText(Integer.toString(reps[2]));
        mExercise3Label.setText(exercises[3]);
        mRep3Label.setText(Integer.toString(reps[3]));
        mExercise4Label.setText(exercises[4]);
        mRep4Label.setText(Integer.toString(reps[4]));
        mExercise5Label.setText(exercises[5]);
        mRep5Label.setText(Integer.toString(reps[5]));

        return v;
    }
}
