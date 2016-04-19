package com.luxary_team.beatbox;

import android.app.Fragment;

public class BeatBoxActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return  BeatBoxFragment.newInstance();
    }
}
