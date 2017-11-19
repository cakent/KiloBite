package com.bignerdranch.android.kilobite.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.kilobite.Workout;
import com.bignerdranch.android.kilobite.database.WorkoutDBSchema.WorkoutTables;

import java.util.UUID;

/**
 * Created by kent on 11/12/17.
 */

public class WorkoutCursorWrapper extends CursorWrapper {

    public WorkoutCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Workout getWorkout(){
        String uuidString = getString(getColumnIndex(WorkoutTables.Cols.UUID));
        int isCompleted = getInt(getColumnIndex(WorkoutTables.Cols.COMPLETED));
        String exercise = getString(getColumnIndex(WorkoutTables.Cols.EXERCISE));
        int Reps = getInt(getColumnIndex(WorkoutTables.Cols.REPS));
        int workoutNum = getInt(getColumnIndex(WorkoutTables.Cols.WORKOUTNUM));

        Workout workout = new Workout(UUID.fromString(uuidString));
        workout.setReps(Reps);
        workout.setExercise(exercise);
        workout.setCompleted(isCompleted!=0);
        workout.setWorkoutNum(workoutNum);
        return workout;
    }
}
