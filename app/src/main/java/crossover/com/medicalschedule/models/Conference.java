package crossover.com.medicalschedule.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafae on 29/08/2016.
 */
public class Conference {

    private int id;
    private String _title;
    private String _topics;
    private String _date;
    private String _hour;
    private String _address;
    private String _doctors;
    private String _suggests ="";
    private static List<Conference> _conferenceArray = new ArrayList<Conference>();


    public Conference() { }

    public Conference(String _title, String _topics, String _date, String _hour, String _address) {
        this._title = _title;
        this._topics = _topics;
        this._date = _date;
        this._hour = _hour;
        this._address = _address;

    }

    public Conference(int id, String _title, String _topics, String _date, String _hour, String _address) {
        this.id = id;
        this._title = _title;
        this._topics = _topics;
        this._date = _date;
        this._hour = _hour;
        this._address = _address;

    }

    public void addConference(List<Conference> conferenceArray){
        _conferenceArray.addAll(conferenceArray);
    }

    public void clearConference(){
        _conferenceArray.clear();
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public void setTopics(String _topics) {
        this._topics = _topics;
    }

    public void setDate(String _date) {
        this._date = _date;
    }

    public void setHour(String _hour) {
        this._hour = _hour;
    }

    public void setAddress(String _address) {
        this._address = _address;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return _title;
    }

    public String getTopics()
    {
        String topics[] = _topics.split(", ");
        StringBuilder topicsFormat = new StringBuilder();
        for(String topic : topics){
            topicsFormat.append(topic.trim()+"\n");
        }
        return topicsFormat.toString();
    }

    public String getDate() {
        return _date;
    }

    public String getHour() {
        return _hour;
    }

    public String getAddress() {
        return _address;
    }

    public String getDoctors() {
        return _doctors;
    }

    public String getSuggests() {
     try {
         return _suggests;
     }catch (NullPointerException error){
         return "";
     }
    }
    public void setDoctors(String _doctors) {
        this._doctors = _doctors;
    }

    public void setSuggests(String _suggests) {
        this._suggests = _suggests;
    }


}
