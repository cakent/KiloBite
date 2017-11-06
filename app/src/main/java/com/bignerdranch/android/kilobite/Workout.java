package com.bignerdranch.android.kilobite;

import java.util.UUID;

/**
 * Created by kent on 11/5/17.
 */

public class Workout {
//basic workout class
    public UUID getWorkoutID() {
        return mworkoutID;
    }

    private UUID mworkoutID;
    private String mExercise;

    public String getExercise() {
        return mExercise;
    }

    public void setExercise(String exercise) {
        mExercise = exercise;
    }

    public int getReps() {
        return mReps;
    }

    public void setReps(int reps) {
        mReps = reps;
    }

    private int mReps;

    public Workout(){
        mReps=100;
        mExercise="Burpees";
        mworkoutID = UUID.randomUUID();
    }
}
