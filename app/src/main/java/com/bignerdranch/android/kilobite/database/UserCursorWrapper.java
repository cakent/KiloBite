package com.bignerdranch.android.kilobite.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.kilobite.User;
import com.bignerdranch.android.kilobite.Workout;
import com.bignerdranch.android.kilobite.database.WorkoutDBSchema.WorkoutTables;

import java.util.UUID;

/**
 * Created by kent on 11/12/17.
 */

public class UserCursorWrapper extends CursorWrapper {

    public UserCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public User getUser(){
        int id = getInt(getColumnIndex(WorkoutTables.Cols.USERID));
        Double GoalWeight = getDouble(getColumnIndex(WorkoutTables.Cols.GOALWEIGHT));
        int CurrentWeight = getInt(getColumnIndex(WorkoutTables.Cols.CURRENTWEIGHT));
        int CurrentHeight = getInt(getColumnIndex(WorkoutTables.Cols.CURRENTWEIGHT));
        int workoutsCompleted = getInt(getColumnIndex(WorkoutTables.Cols.WORKOUTSCOMPLETED));
        double BMI = getDouble(getColumnIndex(WorkoutTables.Cols.BMI));

        User user = new User(id);
        user.setCurrentHeight(CurrentHeight);
        user.setCurrentWeight(CurrentWeight);
        user.setBMI(BMI);
        user.setGoalWeight(GoalWeight);
        user.setWorkoutsCompleted(workoutsCompleted);

        return user;
    }
}
