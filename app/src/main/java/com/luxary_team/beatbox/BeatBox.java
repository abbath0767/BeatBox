package com.luxary_team.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeatBox {
    public static final String TAG = "BeatBox";
    public static final String SOUND_FOLDER = "sample_sounds";
    public static final int MAX_SOUNDS_COUNT = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS_COUNT, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    private void load(Sound sound) throws IOException {
        AssetFileDescriptor discriptor = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(discriptor, 1);
        sound.setSoundId(soundId);
    }

    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null)
            return;
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
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
            try {
                Sound sound = new Sound(SOUND_FOLDER + "/" + fileName);
                load(sound);
                mSounds.add(sound);
            } catch (Exception e) {
                Log.d(TAG, "can't load sound " + fileName, e);
            }
        }
    }

    public List<Sound> getSounds() {
        return  mSounds;
    }

    public void release() {
        mSoundPool.release();
    }
}
