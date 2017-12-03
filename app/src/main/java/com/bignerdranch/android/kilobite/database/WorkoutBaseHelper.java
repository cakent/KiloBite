package com.bignerdranch.android.kilobite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.kilobite.database.WorkoutDBSchema.WorkoutTables;

/**
 * Created by kent on 11/12/17.
 */

public class WorkoutBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME ="Workout.db";

    public WorkoutBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+ WorkoutTables.WOD_NAME+"("+" _id integer primary key autoincrement, "+WorkoutTables.Cols.UUID+", "+WorkoutTables.Cols.EXERCISE+", "+WorkoutTables.Cols.REPS+", "+WorkoutTables.Cols.COMPLETED+", "+WorkoutTables.Cols.WORKOUTNUM+")");
        db.execSQL("create table "+ WorkoutTables.USER_STATS+"("+" _id integer primary key autoincrement, "+WorkoutTables.Cols.USERID+", "+WorkoutTables.Cols.CURRENTHEIGHT+", "+WorkoutTables.Cols.CURRENTWEIGHT+", "+WorkoutTables.Cols.GOALWEIGHT+", "+WorkoutTables.Cols.BMI+", "+WorkoutTables.Cols.WORKOUTSCOMPLETED+", "+WorkoutTables.Cols.PREFDAYS+")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
