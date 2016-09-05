package crossover.com.medicalschedule;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import crossover.com.medicalschedule.dao.ControllerDAO;
import crossover.com.medicalschedule.menu.BaseMenu;
import crossover.com.medicalschedule.models.Conference;
import crossover.com.medicalschedule.models.User;
import crossover.com.medicalschedule.utils.UserUtils;

public class ViewConferenceActivity extends BaseMenu {


    TextView _title, _date, _hour, _address, _topics, _suggets;
    ControllerDAO _controllerDAO;
    String _idTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_conference);
        _controllerDAO = new ControllerDAO(this);

        Intent intent = getIntent();

        _idTitle = intent.getStringExtra("title");
        setTitle(intent.getStringExtra("title"));
        setDate(intent.getStringExtra("date"));
        setHour(intent.getStringExtra("hour"));
        setAddress(intent.getStringExtra("address"));
        setTopics(intent.getStringExtra("topics"));
        setSuggest(intent.getStringExtra("suggest"));

        enableMenus(UserUtils.getIsAdmin());

    }

    private void enableMenus(String admin) {
        if (admin.equals("0")) {
            doctorPanel();
        } else {
            adminPanel();
        }

    }

    private void adminPanel() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        Menu nav_menu = navigationView.getMenu();
        nav_menu.findItem(R.id.conferenceAdmin).setVisible(true);
    }

    private void doctorPanel() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        Menu nav_menu = navigationView.getMenu();
        nav_menu.findItem(R.id.conferenceDoctor).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.all_conferences:
                startActivity(new Intent(this, ConferenceActivity.class));
                return true;

            case R.id.my_conferences:
                startActivity(new Intent(this, MyInvitesActivity.class));
                return true;

            case R.id.edit_conference:
                Intent intent = new Intent(this, EditConferenceActivity.class);
                intent.putExtra("idTitle", _idTitle);
                startActivity(intent);
                return true;

            case R.id.logout:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return true;

            case R.id.cancel_conference:
                AlertDialog.Builder cancel = new AlertDialog.Builder(this);
                cancel.setCancelable(true);
                cancel.setTitle("Info");
                cancel.setMessage("Do you want delete?");
                cancel.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        _controllerDAO.deleteConference(_idTitle);
                        deletedConfirmMessage("Cancel conference", "Canceled", ViewConferenceActivity.this);
                    }
                });
                cancel.setNegativeButton("Cancel", null);
                cancel.show();
                return true;

            case R.id.invite_conference:
                final EditText conferenceText = new EditText(this);
                AlertDialog.Builder invite = new AlertDialog.Builder(this);
                invite.setCancelable(true);
                invite.setTitle("Info");
                invite.setMessage("Who do you want to invite?");
                invite.setView(conferenceText);
                invite.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String name = conferenceText.getText().toString();
                        inviteUser(name, _idTitle);
                    }
                });
                invite.setNegativeButton("Cancel", null);
                invite.show();
                return true;

            case R.id.suggest_topic:
                final EditText suggestText = new EditText(this);
                AlertDialog.Builder suggest = new AlertDialog.Builder(this);
                suggest.setCancelable(true);
                suggest.setTitle("Info");
                suggest.setMessage("What do you think?");
                suggest.setView(suggestText);
                suggest.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String suggestion = suggestText.getText().toString();
                        suggest(suggestion, _idTitle);
                    }
                });
                suggest.setNegativeButton("Cancel", null);
                suggest.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deletedConfirmMessage(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setMessage(message);
        builder.show();
    }

    private void inviteUser(String name, String conferenceTitle) {
        User user = _controllerDAO.readOneUser(name);
        try {
            if (user.getMySchedules().equalsIgnoreCase("")) {
                user.setMySchedules(conferenceTitle);
                _controllerDAO.updateUser(user);
                showMessage("Info", "New invited!", this);
            } else {
                user.setMySchedules(conferenceTitle + "/" + user.getMySchedules());
                _controllerDAO.updateUser(user);
                showMessage("Info", "Invited!", this);
            }
        } catch (NullPointerException e) {
            showMessage("Info", "User not exist!", this);
        }
    }

    private void suggest(String suggestion, String conferenceTitle) {
        try {
            addNewSuggest(suggestion, conferenceTitle);
        } catch (NullPointerException e) {
            showMessage("Info", "User not exist!", this);
        }
    }

    private void addNewSuggest(String suggestion, String conferenceTitle) {
        Conference conferenceSuggestion;
        for (Conference conference : _controllerDAO.readAllConferences()) {
            if (conference.getTitle().equals(conferenceTitle)) {
                conferenceSuggestion = new Conference(conference.getId(), conference.getTitle(), conference.getTopics(), conference.getDate(), conference.getHour(), conference.getAddress());
                conferenceSuggestion.setSuggests(UserUtils.getUserName() + ": " + suggestion + "\n" + conference.getSuggests());
                _controllerDAO.updateConference(conferenceTitle, conferenceSuggestion);
                inviteSendMenssage("Info", "Added! Refresh for effects!", this);
            }
        }
    }

    private void inviteSendMenssage(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(ViewConferenceActivity.this, ConferenceActivity.class);
                finish();
                startActivity(intent);
            }
        });
        builder.setMessage(message);
        builder.show();
    }

    private void showMessage(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("OK", null);
        builder.show();
    }


    private void setTitle(String set) {
        _title = (TextView) findViewById(R.id.txtTitle);
        _title.setText(set);
    }

    private void setDate(String set) {
        _date = (TextView) findViewById(R.id.txtDate);
        _date.setText(set);
    }

    private void setHour(String set) {
        _hour = (TextView) findViewById(R.id.txtHour);
        _hour.setText(set);
    }

    private void setAddress(String set) {
        _address = (TextView) findViewById(R.id.txtAddress);
        _address.setText(set);
    }

    private void setTopics(String set) {
        _topics = (TextView) findViewById(R.id.txtTopicsDefault);
        _topics.setText(set);
    }

    private void setSuggest(String set) {
        _suggets = (TextView) findViewById(R.id.txtSuggests);
        _suggets.setText(set);
    }


}
