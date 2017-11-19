package com.bignerdranch.android.kilobite.database;

import com.bignerdranch.android.kilobite.SingleFragmentActivity;

/**
 * Created by kent on 11/12/17.
 */

public class WorkoutDBSchema {

    public static final class WorkoutTables {
        public static final String WOD_NAME = "Workouts";
        public static final String USER_STATS = "User";


        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String EXERCISE = "exercise";
            public static final String REPS = "reps";
            public static final String COMPLETED = "Completed";
            public static final String CURRENTWEIGHT = "Current_Weight";
            public static final String CURRENTHEIGHT = "Current_Height";
            public static final String GOALWEIGHT = "Goal_Weight";
            public static final String BMI = "BMI";
            public static final String WORKOUTSCOMPLETED = "Workouts_completed";
            public static final String WORKOUTNUM = "Workout_num";
            public static final String USERID ="User_ID";
        }
    }
}