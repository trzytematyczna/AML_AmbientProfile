package pl.edu.agh.eis.database;

import android.provider.BaseColumns;

/**
 * Created by moni on 2014-11-06.
 */
public class TableEntry {

    public static abstract class ProfileEntry implements BaseColumns {
        public static final String TABLE_NAME = "ProfilesTable";
        public static final String COLUMN_ID= "id";
        public static final String COLUMN_NAME= "name";
        public static final String COLUMN_VOLUME_ON= "volume_on";
        public static final String COLUMN_VOLUME_LEVEL= "volume_level";
        public static final String COLUMN_BRIGHTNESS_LEVEL= "brightness_level";
        public static final String COLUMN_VIBRATIONS_ON= "vibrations_on";
        public static final String COLUMN_AIRPLANE_ON= "airplane_on";
        public static final String COLUMN_NOTIFICATION_SOUND= "notification_sound";
        public static final String COLUMN_SCREEN_TIMEOUT_ON= "screen_timeout_on";
    }
}
