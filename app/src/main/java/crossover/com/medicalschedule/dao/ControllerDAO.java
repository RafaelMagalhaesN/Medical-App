package crossover.com.medicalschedule.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import crossover.com.medicalschedule.models.Conference;
import crossover.com.medicalschedule.models.User;

/**
 * Created by rafae on 29/08/2016.
 * C R U D Methods
 */

public class ControllerDAO {

    private DbHandler dbHandler;
    private SQLiteDatabase sqLiteDatabase;

    public ControllerDAO(Context context) {
        dbHandler = new DbHandler(context);
    }
    public void open() throws SQLException{sqLiteDatabase = dbHandler.getWritableDatabase(); }
    public void close() throws SQLException{sqLiteDatabase.close(); }


    /**
     * USERS CREATE
     * @param user
     */

    public boolean createNewUser(User user){
        open();
        long result;
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHandler.KEY_NAME, user.getUsername());
        contentValues.put(DbHandler.KEY_PASSWORD, user.getPassword());
        contentValues.put(DbHandler.KEY_ADMIN, user.getConferenceAdmin());
        contentValues.put(DbHandler.KEY_SCHEDULES, user.getMySchedules());
        result = sqLiteDatabase.insert(dbHandler.TABLE_USERS,null,contentValues);
        close();
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }


    /**
     * USERS READ ONE
     * @param username
     */
    public User readOneUser(String username){
        open();
        User user = null;
        Cursor cursor = sqLiteDatabase.query(dbHandler.TABLE_USERS, new String[] {
                DbHandler.KEY_ID, DbHandler.KEY_NAME, DbHandler.KEY_PASSWORD,
                DbHandler.KEY_ADMIN, DbHandler.KEY_SCHEDULES},
                DbHandler.KEY_NAME+"=?",
                new String[] {username}, null,null,null);

        if(cursor != null){
            try{cursor.moveToFirst();
            user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));}
            catch (CursorIndexOutOfBoundsException error){
                return null;
            }
        }
        close();
        return user;
    }

    /**
     * USERS READ ALL
     * CRUD READ ONLY
     */

    public List<User> readAllUsers(){
        open();
        User user = new User();
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM "+DbHandler.TABLE_USERS;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setConferenceAdmin(cursor.getString(3));
                user.setMySchedules(cursor.getString(4));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return userList;
    }

    /**
     * USERS UPDATE
     * @param user
     */
    public void updateUser(User user){
        ContentValues contentValues;
        open();
        contentValues = new ContentValues();
        contentValues.put(DbHandler.KEY_NAME, user.getUsername());
        contentValues.put(DbHandler.KEY_PASSWORD, user.getPassword());
        contentValues.put(DbHandler.KEY_ADMIN, user.getConferenceAdmin());
        contentValues.put(DbHandler.KEY_SCHEDULES, user.getMySchedules());
         sqLiteDatabase.update(DbHandler.TABLE_USERS,contentValues,DbHandler.KEY_NAME+"= ?",
                new String[]{user.getUsername()});
    }


    /**
     * CONFERENCE OPERATIONS
     * Create new Conference
     * @param conference
     */
    public boolean createNewConference(Conference conference){
        open();
        long result;
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHandler.KEY_TITLE, conference.getTitle());
        contentValues.put(DbHandler.KEY_DATE, conference.getDate());
        contentValues.put(DbHandler.KEY_HOUR, conference.getHour());
        contentValues.put(DbHandler.KEY_ADDRESS, conference.getAddress());
        contentValues.put(DbHandler.KEY_TOPICS, conference.getTopics());
        contentValues.put(DbHandler.KEY_DOCTORS, conference.getDoctors());
        contentValues.put(DbHandler.KEY_SUGGESTS, conference.getSuggests());
        result = sqLiteDatabase.insert(dbHandler.TABLE_CONFERENCE,null,contentValues);
        close();
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    /**
     * CONFERENCE OPERATIONS
     * read All Conferences
     *
     */
    public List<Conference> readAllConferences(){
        Conference conference;
        List<Conference> conferences = new ArrayList<Conference>();
        String[] fields =  {DbHandler.KEY_ID, DbHandler.KEY_TITLE, DbHandler.KEY_DATE, DbHandler.KEY_HOUR, DbHandler.KEY_ADDRESS,DbHandler.KEY_TOPICS, DbHandler.KEY_DOCTORS, DbHandler.KEY_SUGGESTS};
        open();
        Cursor cursor = sqLiteDatabase.query(DbHandler.TABLE_CONFERENCE, fields, null, null, null, null, null, null);

        if(cursor!=null){
            if(cursor.moveToFirst()){
                do {
                    conference = new Conference();
                    conference.setId(Integer.parseInt(cursor.getString(0)));
                    conference.setTitle(cursor.getString(1));
                    conference.setDate(cursor.getString(2));
                    conference.setHour(cursor.getString(3));
                    conference.setAddress(cursor.getString(4));
                    conference.setTopics(cursor.getString(5));
                    conference.setDoctors(cursor.getString(6));
                    conference.setSuggests(cursor.getString(7));
                    conferences.add(conference);
                } while (cursor.moveToNext());
            }
        }
        close();
        cursor.close();
        return conferences;
    }

    /**
     * CONFERENCE OPERATIONS
     * Update
     *
     */
    public void updateConference(String title, Conference conference){
        ContentValues contentValues;

        open();
        contentValues = new ContentValues();
        contentValues.put(DbHandler.KEY_TITLE, conference.getTitle());
        contentValues.put(DbHandler.KEY_DATE, conference.getDate());
        contentValues.put(DbHandler.KEY_HOUR, conference.getHour());
        contentValues.put(DbHandler.KEY_ADDRESS, conference.getAddress());
        contentValues.put(DbHandler.KEY_TOPICS, conference.getTopics());
        contentValues.put(DbHandler.KEY_DOCTORS, conference.getDoctors());
        contentValues.put(DbHandler.KEY_SUGGESTS, conference.getSuggests());
        sqLiteDatabase.update(dbHandler.TABLE_CONFERENCE,contentValues,dbHandler.KEY_TITLE+"=?",new String[]{title});
        close();
    }

    /**
     * CONFERENCE OPERATIONS
     * Delete
     *
     */
    public void deleteConference(String title){
        open();
        sqLiteDatabase.delete(dbHandler.TABLE_CONFERENCE, dbHandler.KEY_TITLE+"=?",new String[]{title});
        close();
    }


    /**
     * USERS READ ONE
     * @param titles
     */

    public List<Conference> readAllConferenceTitle(List<String> titles){
        Conference conference;
        List<Conference> conferences = new ArrayList<Conference>();
        String[] fields =  {DbHandler.KEY_ID, DbHandler.KEY_TITLE, DbHandler.KEY_DATE, DbHandler.KEY_HOUR, DbHandler.KEY_ADDRESS,DbHandler.KEY_TOPICS, DbHandler.KEY_DOCTORS, DbHandler.KEY_SUGGESTS};
        open();
        for(String title: titles) {
            Cursor cursor = sqLiteDatabase.query(DbHandler.TABLE_CONFERENCE, fields, DbHandler.KEY_TITLE + "=?", new String[]{title}, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        conference = new Conference();
                        conference.setId(Integer.parseInt(cursor.getString(0)));
                        conference.setTitle(cursor.getString(1));
                        conference.setDate(cursor.getString(2));
                        conference.setHour(cursor.getString(3));
                        conference.setAddress(cursor.getString(4));
                        conference.setTopics(cursor.getString(5));
                        conference.setDoctors(cursor.getString(6));
                        conference.setSuggests(cursor.getString(7));
                        conferences.add(conference);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        }
        close();
        return conferences;
    }


}
