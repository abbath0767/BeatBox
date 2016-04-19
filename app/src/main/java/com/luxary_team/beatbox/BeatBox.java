package com.luxary_team.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeatBox {
    public static final String TAG = "BeatBox";
    public static final String SOUND_FOLDER = "sample_sounds";

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;

        try {
            soundNames = mAssets.list(SOUND_FOLDER);
            Log.i(TAG, "Found sounds: " + Arrays.toString(soundNames));
            Log.i(TAG, "Sound count = : " + soundNames.length);
        } catch (Exception e) {
            Log.e(TAG, "Could not list assets, " + e);
            return;
        }

        for (String fileName: soundNames) {
            mSounds.add(new Sound(SOUND_FOLDER + "/" + fileName));
        }
    }

    public List<Sound> getSounds() {
        return  mSounds;
    }
}
