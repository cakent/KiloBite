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
        String Rep = getString(getColumnIndex(WorkoutTables.Cols.REPS));
        int workoutNum = getInt(getColumnIndex(WorkoutTables.Cols.WORKOUTNUM));


        Rep = Rep.replaceAll("\\[", "").replaceAll("\\]","");
        exercise = exercise.replaceAll("\\[", "").replaceAll("\\]","");
        String[] exercises = exercise.split(",");
        String[] repString = Rep.split(",");

        for(int i =0;i<repString.length;i++){
            repString[i] = repString[i].replaceAll(" ","");;
        }

        for(int i =0;i<exercises.length;i++){
            exercises[i] = exercises[i].replaceAll(" ","");;
        }

        int[] repCount =new int[repString.length];

        for( int i=0;i<repString.length;i++){
            repCount[i]= Integer.parseInt(repString[i]);
        }

        Workout workout = new Workout(UUID.fromString(uuidString));
        workout.setReps(repCount);
        workout.setExercise(exercises);
        workout.setCompleted(isCompleted!=0);
        workout.setWorkoutNum(workoutNum);
        return workout;
    }
}
