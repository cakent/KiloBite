package com.bignerdranch.android.kilobite;

import android.support.v4.app.Fragment;

/**
 * Created by kent on 11/13/17.
 */

public class DashboardActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new DashboardFragment();
    }
}
