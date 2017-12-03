package com.bignerdranch.android.kilobite;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by kent on 11/10/17.
 */

public class GoalSettingActivity extends SingleFragmentActivity {
    public static final String EXTRA_USER_ID="com.bignerdranch.android.kilobite.user_id";


    public static Intent newIntent(Context packageContext, int id){
        Intent intent = new Intent(packageContext,GoalSettingActivity.class);
        intent.putExtra(EXTRA_USER_ID,id);

        return intent;

    }



    @Override
    protected Fragment createFragment(){
        return new GoalSettingFragment();
    }

}
