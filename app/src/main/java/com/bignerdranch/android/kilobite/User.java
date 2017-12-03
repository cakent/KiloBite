package com.bignerdranch.android.kilobite;

import android.database.Cursor;

import com.bignerdranch.android.kilobite.database.UserCursorWrapper;
import com.bignerdranch.android.kilobite.database.WorkoutDBSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kent on 11/12/17.
 */

public class User {
    private int mCurrentWeight;
    private int mCurrentHeight;
    private double mBMI;
    private int mWorkoutsCompleted;
    private Double mGoalWeight;

    public List<String> getPrefDays() {
        return mPrefDays;
    }

    public void setPrefDays(List<String> prefDays) {
        mPrefDays = prefDays;
    }

    private List<String> mPrefDays= new ArrayList<>();





    public int getUserID() {
        return mUserID;
    }

    private int mUserID;
    public User(){
        this(1);
    }

    public int getWorkoutsCompleted() {
        return mWorkoutsCompleted;
    }

    public void addWorkoutCompleted(){
        mWorkoutsCompleted++;
    }

    public void setWorkoutsCompleted(int workoutsCompleted) {
        mWorkoutsCompleted = workoutsCompleted;
    }

    public User(int id){
        mUserID = id;
        setCurrentWeight(0);
        setCurrentHeight(0);
        setBMI(0);
        setGoalWeight(0.0);
        setWorkoutsCompleted(0);

    }





    public double getBMI() {
        return mBMI;
    }

    public void setBMI(double BMI) {
        mBMI = BMI;
    }


    public Double getGoalWeight() {
        return mGoalWeight;
    }

    public void setGoalWeight(Double goalWeight) {
        mGoalWeight = goalWeight;
    }



    public int getCurrentHeight() {
        return mCurrentHeight;
    }

    public void setCurrentHeight(int currentHeight) {
        mCurrentHeight = currentHeight;
    }


    public int getCurrentWeight() {
        return mCurrentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        mCurrentWeight = currentWeight;
    }







}
