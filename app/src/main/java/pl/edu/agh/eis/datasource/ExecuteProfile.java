package pl.edu.agh.eis.datasource;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.os.Bundle;

import java.util.ArrayList;

import pl.edu.agh.eis.amlprofile.R;
import pl.edu.agh.eis.database.AmbientDbHelper;
import pl.edu.agh.eis.database.TableEntry;

/**
 * Created by moni on 2014-11-08.
 */
public class ExecuteProfile extends ContextWrapper{
    AmbientDbHelper mDBHelper;

    public ExecuteProfile(Context base) {
        super(base);
    }

    public void go(int id){
        Bundle bundle = getInfo(id);
        if(bundle.getInt("Volume_on")==1){
            AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//            audio.setStreamMute(AudioManager.STREAM_VOICE_CALL, true);//(AudioManager.STREAM_RING, true);
            audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }
        else{
            AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//            audio.setStreamMute(AudioManager.STREAM_VOICE_CALL, false);//(AudioManager.STREAM_RING, true);
            audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }
        if(bundle.getInt("Brightness")==1){
            android.provider.Settings.System.putInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            android.provider.Settings.System.putInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, 1);

        }
        else{
            android.provider.Settings.System.putInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            android.provider.Settings.System.putInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, 110);

        }
    }
    private Bundle getInfo(int id) {
        String selectQuery = "SELECT  * FROM " +  TableEntry.ProfileEntry.TABLE_NAME + " WHERE id=" +id +" LIMIT 1;";
        mDBHelper = new AmbientDbHelper(this);
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        Bundle info = new Bundle();
        try {
            if (cursor.moveToFirst()) {
                do {
                    info.putInt("Volume_on", cursor.getInt(2));
                    info.putInt("Brightness",cursor.getInt(4));
                } while (cursor.moveToNext());
            }
        }
        finally{
            cursor.close();
            db.close();
            mDBHelper.close();
        }
        return info;
    }
}
