package uz.fozilbekimomov.geographyquiz;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;

public class App extends Application {
    private static MediaPlayer musicPlayer;
    private static Context appContext;

    public static void startMusic() {

        if (musicPlayer == null) {
            musicPlayer = MediaPlayer.create(appContext, R.raw.nasheed);
            musicPlayer.setLooping(true);
        }
        if (musicPlayer != null) {
            musicPlayer.start();
        }
    }


}
