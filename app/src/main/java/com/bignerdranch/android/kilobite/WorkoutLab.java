package com.bignerdranch.android.kilobite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bignerdranch.android.kilobite.database.UserCursorWrapper;
import com.bignerdranch.android.kilobite.database.WorkoutBaseHelper;
import com.bignerdranch.android.kilobite.database.WorkoutCursorWrapper;
import com.bignerdranch.android.kilobite.database.WorkoutDBSchema;
import com.bignerdranch.android.kilobite.database.WorkoutDBSchema.WorkoutTables;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kent on 11/5/17.
 */

public class WorkoutLab {
    private static WorkoutLab sWorkoutLab;
    private static final String TAG ="WorkoutLab";

    private static Context mContext;
    private SQLiteDatabase mDatabase;

    public static WorkoutLab get(Context context){
        if(sWorkoutLab == null) sWorkoutLab = new WorkoutLab(context);
        return  sWorkoutLab;
    }

    public List<Workout> getWorkouts(){

        List<Workout> workouts = new ArrayList<>();

        WorkoutCursorWrapper cursor =queryCursor(null,null);
        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                workouts.add(cursor.getWorkout());
                cursor.moveToNext();
            }
        }finally{
            cursor.close();
        }
        return workouts;
    }
//This is where we'll build the routines. Right now it just plugs in burpees and divides 100 by i
    private WorkoutLab(Context context){

        mContext= context.getApplicationContext();
        mDatabase= new WorkoutBaseHelper(mContext).getWritableDatabase();


    }

    public void addWorkout(Workout w){
        ContentValues values = getWorkoutContentValues(w);

        mDatabase.insert(WorkoutTables.WOD_NAME,null,values);

    }


    public void updateWorkout(Workout workout){
        String uuidString = workout.getWorkoutID().toString();
        ContentValues values = getWorkoutContentValues(workout);

        mDatabase.update(WorkoutTables.WOD_NAME, values,WorkoutTables.Cols.UUID+" = ?",new String[]{uuidString});


    }



    private WorkoutCursorWrapper queryCursor(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                WorkoutTables.WOD_NAME,null,whereClause,whereArgs,null,null,null
        );
        return new WorkoutCursorWrapper(cursor);
    }




    public Workout getWorkout(UUID id){

        WorkoutCursorWrapper cursor = queryCursor(WorkoutTables.Cols.UUID+" = ?",new String[]{id.toString()});

        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getWorkout();
        }finally {
            cursor.close();
        }
    }



    private static ContentValues getWorkoutContentValues(Workout workout){
        ContentValues values = new ContentValues();
        values.put(WorkoutTables.Cols.UUID, workout.getWorkoutID().toString());
        values.put(WorkoutTables.Cols.COMPLETED,workout.getCompleted().toString());
        values.put(WorkoutTables.Cols.EXERCISE,workout.getExercise().toString());
        values.put(WorkoutTables.Cols.REPS,Integer.toString(workout.getReps()));
        values.put(WorkoutTables.Cols.WORKOUTNUM,Integer.toString(workout.getWorkoutNum()));

        return values;
    }


}
