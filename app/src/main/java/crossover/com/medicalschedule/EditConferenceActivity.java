package crossover.com.medicalschedule;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import crossover.com.medicalschedule.adapters.ConferenceRecyclerAdapter;
import crossover.com.medicalschedule.dao.ControllerDAO;
import crossover.com.medicalschedule.menu.BaseMenu;
import crossover.com.medicalschedule.models.Conference;

public class EditConferenceActivity extends BaseMenu implements View.OnClickListener {

    private ControllerDAO _controllerDAO;

    private EditText _date, _time, _title, _address, _spoke;
    private DatePickerDialog _datePickerDialog;
    private SimpleDateFormat _dateFormatter;
    private TimePickerDialog _timePickerDialog;
    private String _idTitle;
    private Conference _conConference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_conference);
        _controllerDAO = new ControllerDAO(getBaseContext());
        Intent intent = getIntent();
        _idTitle = intent.getStringExtra("idTitle");
        _date = (EditText) findViewById(R.id.editTextDate);
        _time = (EditText) findViewById(R.id.editTextHour);
        _title = (EditText) findViewById(R.id.editTextTitle);
        _address = (EditText) findViewById(R.id.editTextAddress);
        _spoke = (EditText) findViewById(R.id.editTextSpoke);

        setFields();
        setEditTextData();
        setEditTextTime();
    }


    private void setFields() {
        for (Conference conference : _controllerDAO.readAllConferences()) {
            if (conference.getTitle().equals(_idTitle)) {
                _conConference = new Conference(conference.getId(), conference.getTitle(), conference.getTopics(), conference.getDate(), conference.getHour(), conference.getAddress());
            }
        }
        _date.setText(_conConference.getDate());
        _time.setText(_conConference.getHour());
        _title.setText(_conConference.getTitle());
        _address.setText(_conConference.getAddress());
        _spoke.setText(_conConference.getTopics());
    }



    @Override
    public void onClick(View v) {
        if (v == _date) {
            _datePickerDialog.show();
        } else if (v == _time) {
            _timePickerDialog.show();
        }

    }

    private void setEditTextData() {
        _date.setInputType(InputType.TYPE_NULL);
        _dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        _date.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        _datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                _date.setText(_dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    private void setEditTextTime() {
        _time.setInputType(InputType.TYPE_NULL);

        Calendar newCalendar = Calendar.getInstance();
        int hour = newCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = newCalendar.get(Calendar.MINUTE);
        _time.setOnClickListener(this);


        _timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        _time.setText(hourOfDay + ":" + new DecimalFormat("00").format(minute));
                    }
                }, hour, minute, true);

    }


    private void showMessage(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setNegativeButton("OK", null);
        builder.setMessage(message);
        builder.show();
    }

    public String getTitleOf() {
        return _title.getText().toString();
    }

    public String getDateOf() {
        return _date.getText().toString();
    }


    public String getHourOf() {
        return _time.getText().toString();
    }

    public String getAddressOf() {
        return _address.getText().toString();
    }

    public String getSpokeOf() {
        return _spoke.getText().toString();
    }

    public boolean isEditTextEmpty() {
        if (getTitleOf().equals("") || getDateOf().equals("")
                || getHourOf().equals("") || getAddressOf().equals("")
                || getSpokeOf().equals("")) {
            return true;
        }
        return false;
    }

    public void onUpdateConference(View v) {
        if (!isEditTextEmpty()) {
            Conference conference = new Conference(getTitleOf(), getSpokeOf(), getDateOf(), getHourOf(), getAddressOf());
            _controllerDAO.updateConference(_idTitle, conference);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Info");
            builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) { startHome(ConferenceActivity.class);}});
            builder.setMessage("Updated! To see changes go to home");
            builder.show();
        } else {
            showMessage("Info", "Fields empty!", this);
        }
    }

    private void startHome(Class a){
        Intent intent = new Intent(this,a);
        startActivity(intent);
    }

    @Override
    protected boolean useDrawerToggle() {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();

        return super.onOptionsItemSelected(item);
    }


}
