package crossover.com.medicalschedule.models;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafae on 29/08/2016.
 */
public class User {

    private String _username;
    private String _password;
    private String _conferenceAdmin;
    private int id;
    private String _mySchedules;

    public User(int id, String _username, String _password, String _conferenceAdmin, String _mySchedules) {
        this.id = id;
        this._username = _username;
        this._password = _password;
        this._conferenceAdmin = _conferenceAdmin;
        this._mySchedules = _mySchedules;
    }

    public User(String _username, String _password, String _conferenceAdmin, String _mySchedules) {
        this._username = _username;
        this._password = _password;
        this._conferenceAdmin = _conferenceAdmin;
        this._mySchedules = _mySchedules;
    }

    public User() {
    }

    public String[] getMySchedulesArray() {
        String[] titles = _mySchedules.split("/");
        return titles;
    }

    public String getMySchedules() {
        return _mySchedules;
    }

    public void setMySchedules(String _mySchedules) {
        this._mySchedules = _mySchedules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setUsername(String _username) {
        this._username = _username;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public void setConferenceAdmin(String _conferenceAdmin) {
        this._conferenceAdmin = _conferenceAdmin;
    }

    public String getUsername() {
        return _username;
    }

    public String getPassword() {
        return _password;
    }

    public String getConferenceAdmin(){
        return _conferenceAdmin;
    }


}
