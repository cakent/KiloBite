package com.bignerdranch.android.kilobite;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by kent on 11/13/17.
 */

public class DashboardFragment extends Fragment {



    private TextView mBMITextView;
    private User mUser;
    private TextView mWodsCompleted;
    private TextView mWeightToLoseTextView;
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
    private Button mWorkoutButton;
    private List<Workout> workouts;
    private static final String TAG ="DashboardFragment";
    private AlarmManager alarmMgr;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_user_dashboard, container, false);




        startAlarm(getActivity());

        mBMITextView = (TextView) v.findViewById(R.id.ActualBMI);
        mBMITextView.setText(Double.toString(mUser.getBMI()));

        mWodsCompleted=(TextView) v.findViewById(R.id.WODsCompleted);
        mWodsCompleted.setText(mUser.getWorkoutsCompleted()+"/"+"18");

        mWeightToLoseTextView =(TextView) v.findViewById(R.id.CaloritesToBurn);
        mWeightToLoseTextView.setText(Double.toString(mUser.getGoalWeight()));

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

        if(mUser.getWorkoutsCompleted()<workouts.size()){

            Workout mWorkout = workouts.get(mUser.getWorkoutsCompleted());
            String[] exercises=mWorkout.getExercise();
            int[] reps = mWorkout.getReps();



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

    @Override
    public void onResume(){
        super.onResume();
        mUser=UserLab.get(getActivity()).getUser(1);
    }

    public void startAlarm(Context context){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 7);

        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotifcationSender.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        alarmMgr.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }





}
