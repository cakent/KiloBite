package com.bignerdranch.android.kilobite;

import android.support.v4.app.Fragment;

/**
 * Created by kent on 11/10/17.
 */

public class GoalSettingActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new GoalSettingFragment();
    }

}
