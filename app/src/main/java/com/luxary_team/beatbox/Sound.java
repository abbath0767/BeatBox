package com.luxary_team.beatbox;

public class Sound {
    private String mAssetPath;
    private String mName;

    public Sound(String assetPath) {
        //todo test and refactor
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".wav", "");
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
