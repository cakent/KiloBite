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
    private String[] mExercise ={"a","b","c","d","e","f"};
    private Boolean mCompleted;
    private int mWorkoutNum;
    private int[] mReps = new int[6];


    public int getWorkoutNum() {
        return mWorkoutNum;
    }

    public void setWorkoutNum(int workoutNum) {
        mWorkoutNum = workoutNum;
    }



    public Boolean getCompleted() {
        return mCompleted;
    }

    public void setCompleted(Boolean completed) {
        mCompleted = completed;
    }



    public String[] getExercise() {
        return mExercise;
    }

    public void setExercise(String[] exercise) {
        for(int i=0;i<mExercise.length;i++){
            mExercise[i]=exercise[i];
        }

    }

    public int[] getReps() {
        return mReps;
    }

    public void setReps(int[] reps) {
        for(int i=0;i<mReps.length;i++){
            mReps[i]=reps[i];
        }


    }



    public Workout(){
        this(UUID.randomUUID());
    }

    public Workout(UUID id){
        mworkoutID = id;
    }
}
