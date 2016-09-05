package crossover.com.medicalschedule.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import crossover.com.medicalschedule.models.User;

/**
 * Created by rafae on 29/08/2016.
 */
public class DbHandler extends SQLiteOpenHelper{

    protected static final String DATABASE_NAME = "medicalSchedule";
    protected static final int DATABASE_VERSION = 1;

    protected static final String TABLE_USERS = "users";
    protected static final String TABLE_CONFERENCE = "conference";

    protected static final String KEY_ID = "id";

    // User Table Columns names with id
    protected static final String KEY_NAME = "name";
    protected static final String KEY_PASSWORD = "password";
    protected static final String KEY_ADMIN = "admin";
    protected static final String KEY_SCHEDULES = "schedules";

    // Conference Table Columns names with id
    protected static final String KEY_TITLE = "title";
    protected static final String KEY_TOPICS = "topics";
    protected static final String KEY_DATE = "date";
    protected static final String KEY_HOUR = "hour";
    protected static final String KEY_ADDRESS = "address";
    protected static final String KEY_DOCTORS = "doctors";
    protected static final String KEY_SUGGESTS = "suggests";



    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USERS_TABLE =
                "CREATE TABLE "+TABLE_USERS+"("
                +KEY_ID+" INTEGER PRIMARY KEY,"
                +KEY_NAME+" TEXT,"
                +KEY_PASSWORD+" TEXT,"
                +KEY_ADMIN+" TEXT,"
                +KEY_SCHEDULES+" TEXT)";

        String CREATE_CONFERENCE_TABLE =
                "CREATE TABLE "+TABLE_CONFERENCE+"("
                        +KEY_ID+" INTEGER PRIMARY KEY,"
                        +KEY_TITLE+" TEXT,"
                        +KEY_DATE+" TEXT,"
                        +KEY_HOUR+" TEXT,"
                        +KEY_ADDRESS+" TEXT,"
                        +KEY_TOPICS+" TEXT,"
                        +KEY_DOCTORS+" TEXT,"
                        +KEY_SUGGESTS+" TEXT)";

        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);
        sqLiteDatabase.execSQL(CREATE_CONFERENCE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_CONFERENCE);
        onCreate(sqLiteDatabase);
    }

}
