package com.bignerdranch.android.kilobite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.kilobite.database.UserCursorWrapper;
import com.bignerdranch.android.kilobite.database.WorkoutBaseHelper;
import com.bignerdranch.android.kilobite.database.WorkoutCursorWrapper;
import com.bignerdranch.android.kilobite.database.WorkoutDBSchema;
import com.bignerdranch.android.kilobite.database.WorkoutDBSchema.WorkoutTables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kent on 11/15/17.
 */

public class UserLab {
    private static UserLab sUserLab;
    private List<User> mUsers;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static UserLab get(Context context){
        if(sUserLab==null){
            sUserLab=new UserLab(context);
        }

        return sUserLab;
    }

    private UserLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new WorkoutBaseHelper(mContext).getWritableDatabase();
        User mUser=new User();
        addUser(mUser);



    }
    public User getUser(int id){

        UserCursorWrapper cursor = queryCursor(WorkoutTables.Cols.USERID+" = ?",new String[]{Integer.toString(id)});

        try{
            if(cursor.getCount()==0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getUser();
        }finally{
            cursor.close();
        }
    }

    public void addUser(User u){
        ContentValues values = getUserContentValues(u);

        mDatabase.insert(WorkoutTables.USER_STATS,null,values);
    }

    public List<User> getUsers(){
        return new ArrayList<>();
    }

    public void updateUser(User u){
        String id = Integer.toString(u.getUserID());
        ContentValues values = getUserContentValues(u);

        mDatabase.update(WorkoutTables.USER_STATS,values,WorkoutTables.Cols.USERID+" = ?",new String[]{id});
    }

    private static ContentValues getUserContentValues(User user){


        StringBuilder daysList = new StringBuilder();
        for(String s : user.getPrefDays()){
            daysList.append(s);
            daysList.append(",");
        }

        ContentValues values = new ContentValues();
        values.put(WorkoutTables.Cols.BMI,Double.toString(user.getBMI()));
        values.put(WorkoutTables.Cols.GOALWEIGHT,Double.toString(user.getGoalWeight()));
        values.put(WorkoutTables.Cols.CURRENTWEIGHT,Integer.toString(user.getCurrentWeight()));
        values.put(WorkoutTables.Cols.CURRENTHEIGHT,Integer.toString(user.getCurrentHeight()));
        values.put(WorkoutTables.Cols.WORKOUTSCOMPLETED,Integer.toString(user.getWorkoutsCompleted()));
        values.put(WorkoutTables.Cols.USERID,Integer.toString(user.getUserID()));
        values.put(WorkoutTables.Cols.PREFDAYS,daysList.toString());

        return values;
    }

    private UserCursorWrapper queryCursor(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                WorkoutTables.USER_STATS,null,whereClause,whereArgs,null,null,null
        );
        return new UserCursorWrapper(cursor);
    }
}
