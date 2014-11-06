package pl.edu.agh.eis.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by moni on 2014-11-06.
 */
public class AmbientDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AmbientProfile.sqlite";


    private static final String INT_TYPE = " TEXT";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ITEM_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TableEntry.ProfileEntry.TABLE_NAME + " (" +
                    TableEntry.ProfileEntry.COLUMN_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    TableEntry.ProfileEntry.COLUMN_NAME+ TEXT_TYPE + COMMA_SEP +
                    TableEntry.ProfileEntry.COLUMN_VOLUME_ON+ INT_TYPE + COMMA_SEP +
                    TableEntry.ProfileEntry.COLUMN_VOLUME_LEVEL+ INT_TYPE + COMMA_SEP +
                    TableEntry.ProfileEntry.COLUMN_BRIGHTNESS_LEVEL+ INT_TYPE + COMMA_SEP +
                    TableEntry.ProfileEntry.COLUMN_VIBRATIONS_ON+ INT_TYPE + COMMA_SEP +
                    TableEntry.ProfileEntry.COLUMN_AIRPLANE_ON+ INT_TYPE + COMMA_SEP +
                    TableEntry.ProfileEntry.COLUMN_NOTIFICATION_SOUND+ INT_TYPE + COMMA_SEP +
                    TableEntry.ProfileEntry.COLUMN_SCREEN_TIMEOUT_ON+ INT_TYPE +//+ COMMA_SEP +
                    " );";

    private static final String SQL_DELETE_ITEM_TABLE =
            "DROP TABLE IF EXISTS " + TableEntry.ProfileEntry.TABLE_NAME +";";

    public AmbientDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        sqLiteDatabase.execSQL(SQL_DELETE_ITEM_TABLE);
        onCreate(sqLiteDatabase);
    }


}
