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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
            if(getWorkouts().size()<1) {
                Workout WOD = new Workout();
                WOD.setCompleted(false);
                WOD.setExercise(new String[]{"rounds","pushups","squats","burpees","lunges","situps"});
                WOD.setReps(new int[]{1,5,10,5,20,15});
                WOD.setWorkoutNum(30);
                addWorkout(WOD);
                Workout WOD2 = new Workout();
                WOD2.setCompleted(false);
                WOD2.setExercise(new String[]{"rounds","squat","Pullups","Dips","Broad Jumps","Knee Raises"});
                WOD2.setReps(new int[]{1,5,5,5,5,5});
                WOD2.setWorkoutNum(25);
                addWorkout(WOD2);
                Workout WOD3 = new Workout();
                WOD3.setCompleted(false);
                WOD3.setExercise(new String[]{"rounds","Burpees","Squats","Situps","Pullups","Min. of Rest"});
                WOD3.setReps(new int[]{1,10,15,10,5,2});
                WOD3.setWorkoutNum(40);
                addWorkout(WOD3);
                Workout WOD4 = new Workout();
                WOD4.setCompleted(false);
                WOD4.setExercise(new String[]{ "rounds","Broad Jumps","V-ups","Glute Bridges","Diamond Pushups","Min. of Rest" });
                WOD4.setReps(new int[]{1,6,6,6,6,1});
                WOD4.setWorkoutNum(24);
                addWorkout(WOD4);
                Workout WOD5 = new Workout();
                WOD5.setCompleted(false);
                WOD5.setExercise(new String[]{ "rounds","Squats","Dips","Pullups","Lunges","Flutter Kicks"});
                WOD5.setReps(new int[]{1,6,8,4,16,6});
                WOD5.setWorkoutNum(32);
                addWorkout(WOD5);
                Workout WOD6 = new Workout();
                WOD6.setCompleted(false);
                WOD6.setExercise(new String[]{ "Rounds","Pullups","Burpees","Sit ups","Squats","Min. of Rest"});
                WOD6.setReps(new int[]{1,5,10,15,20,3});
                WOD6.setWorkoutNum(50);
                addWorkout(WOD6);
                Workout WOD7 = new Workout();
                WOD7.setCompleted(false);
                WOD7.setExercise(new String[]{"Rounds","Mountain Climbers","Pushups","Pullups","Bear Crawl(M)","Situps"});
                WOD7.setReps(new int[]{1,20,10,5,10,20});
                WOD7.setWorkoutNum(65);
                addWorkout(WOD7);
                Workout WOD8 = new Workout();
                WOD8.setCompleted(false);
                WOD8.setExercise(new String[]{"Min AMRAP","Pushups","Squats","Burpees","Lunges","Situps"});
                WOD8.setReps(new int[]{3,5,10,5,20,15});
                WOD8.setWorkoutNum(45);
                addWorkout(WOD8);
                Workout WOD9 = new Workout();
                WOD9.setCompleted(false);
                WOD9.setExercise(new String[]{ "Min AMRAP","Burpees","Squats","Situps","Pullups","Lunges"});
                WOD9.setReps(new int[]{3,10,15,10,5,16});
                WOD9.setWorkoutNum(56);
                addWorkout(WOD9);
                Workout WOD10 = new Workout();
                WOD10.setCompleted(false);
                WOD10.setExercise(new String[]{"Min AMRAP","Squats","Dips","Pullups","Lunges","Flutter Kicks"});
                WOD10.setReps(new int[]{3,6,8,4,16,6});
                WOD10.setWorkoutNum(34);
                addWorkout(WOD10);
                Workout WOD11 = new Workout();
                WOD11.setCompleted(false);
                WOD11.setExercise(new String[]{"Min AMRAP","Pullups","Glute Bridges","Sit ups","Squats","Burpees"});
                WOD11.setReps(new int[]{3,5,10,15,20,10});
                WOD11.setWorkoutNum(65);
                addWorkout(WOD11);
                Workout WOD12 = new Workout();
                WOD12.setCompleted(false);
                WOD12.setExercise(new String[]{"Min AMRAP","Squats","Situps","Pushups","Lunges","Pullups"});
                WOD12.setReps(new int[]{3,25,20,15,20,5});
                WOD12.setWorkoutNum(85);
                addWorkout(WOD12);
                Workout WOD13 = new Workout();
                WOD13.setCompleted(false);
                WOD13.setExercise(new String[]{ "Min AMRAP","Pushups","Squats","Situps","Lunges","Burpees"});
                WOD13.setReps(new int[]{3,5,10,15,10,5});
                WOD13.setWorkoutNum(45);
                addWorkout(WOD13);
                Workout WOD14 = new Workout();
                WOD14.setCompleted(false);
                WOD14.setExercise(new String[]{"Rounds","Lunges","Pushups","V ups","Pullups","Burpees"});
                WOD14.setReps(new int[]{1,20,8,6,4,2});
                WOD14.setWorkoutNum(40);
                addWorkout(WOD14);
                Workout WOD15 = new Workout();
                WOD15.setCompleted(false);
                WOD15.setExercise(new String[]{"Rounds","Pushups","Dips","Pullups","Burpees","Min. of Rest"});
                WOD15.setReps(new int[]{1,5,5,5,5,2});
                WOD15.setWorkoutNum(22);
                addWorkout(WOD15);
                Workout WOD16 = new Workout();
                WOD16.setCompleted(false);
                WOD16.setExercise(new String[]{ "Rounds","Squats","Situps","Pushups","Lunges","Pullups"});
                WOD16.setReps(new int[]{1,25,20,15,20,5});
                WOD16.setWorkoutNum(85);
                addWorkout(WOD16);
                Workout WOD17 = new Workout();
                WOD17.setCompleted(false);
                WOD17.setExercise(new String[]{ "Rounds","Pushups","Dips","Pullups","Burpees","Min. of Rest"});
                WOD17.setReps(new int[]{1,5,5,5,5,2});
                WOD17.setWorkoutNum(22);
                addWorkout(WOD17);
                Workout WOD18 = new Workout();
                WOD18.setCompleted(false);
                WOD18.setExercise(new String[]{"Rounds","Squats","Lunges","Glute Bridges","Broad Jumps","Min. of Rest"});
                WOD18.setReps(new int[]{1,5,10,5,5,2});
                WOD18.setWorkoutNum(25);
                addWorkout(WOD18);




            }



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
        values.put(WorkoutTables.Cols.EXERCISE,Arrays.toString(workout.getExercise()));
        values.put(WorkoutTables.Cols.REPS, Arrays.toString(workout.getReps()));
        values.put(WorkoutTables.Cols.WORKOUTNUM,Integer.toString(workout.getWorkoutNum()));

        return values;
    }


}
