package com.bignerdranch.android.kilobite;

import android.support.v4.app.Fragment;

/**
 * Created by kent on 12/6/17.
 */

public class GlossaryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new GlossaryFragment();
    }

}
