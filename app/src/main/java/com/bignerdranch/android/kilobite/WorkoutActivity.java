package com.bignerdranch.android.kilobite;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class WorkoutActivity extends SingleFragmentActivity {

    public static final String EXTRA_WORKOUT_ID ="com.bignerdranch.android.kilobite.workout_id";

    public static Intent newIntent(Context packageContext, UUID workoutId){
        Intent intent=new Intent(packageContext, WorkoutActivity.class);
        intent.putExtra(EXTRA_WORKOUT_ID,workoutId);
        return intent;

    }

    @Override
    protected Fragment createFragment(){
        UUID workoutId = (UUID) getIntent().getSerializableExtra(EXTRA_WORKOUT_ID);
        return WorkoutFragment.newInstance(workoutId);
    }


}
