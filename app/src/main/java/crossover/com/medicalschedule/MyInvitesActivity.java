package crossover.com.medicalschedule;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import crossover.com.medicalschedule.adapters.ConferenceRecyclerAdapter;
import crossover.com.medicalschedule.adapters.MyInvitesRecyclerAdapter;
import crossover.com.medicalschedule.dao.ControllerDAO;
import crossover.com.medicalschedule.menu.BaseMenu;
import crossover.com.medicalschedule.models.Conference;
import crossover.com.medicalschedule.models.User;
import crossover.com.medicalschedule.utils.UserUtils;

public class MyInvitesActivity extends BaseMenu {

    private ControllerDAO _controllerDAO;
    private RecyclerView recycler;
    private MyInvitesRecyclerAdapter _myInvitesRecyclerAdapter;
    private User _user;
    List<Conference> conferences = new ArrayList<Conference>();
    List<String> _titleArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_invites);
        _user = new User();
        _controllerDAO = new ControllerDAO(this);


        recycler = (RecyclerView) findViewById(R.id.recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setHasFixedSize(true);

        getInvitations();
        setAdapter();

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        try {
            Log.v("On restart: ","On restart");
            _myInvitesRecyclerAdapter = new MyInvitesRecyclerAdapter(_controllerDAO.readAllConferenceTitle(_titleArray));
            _myInvitesRecyclerAdapter.notifyDataSetChanged();
            recycler.setAdapter(_myInvitesRecyclerAdapter);
        }catch (NullPointerException e){
            showMessage("Info","No invites avaliable!",this);
        }
    }

    private void getInvitations() {
        _user = _controllerDAO.readOneUser(UserUtils.getUserName());
        for (String title : _user.getMySchedulesArray()) {
            _titleArray.add(title);
            Log.i("Added ", title);
        }
    }

    private void setAdapter() {
        try {
            _myInvitesRecyclerAdapter = new MyInvitesRecyclerAdapter(_controllerDAO.readAllConferenceTitle(_titleArray));
            _myInvitesRecyclerAdapter.notifyDataSetChanged();
            recycler.setAdapter(_myInvitesRecyclerAdapter);
        } catch (NullPointerException e) {
            showMessage("Info", "No invites avaliable!", this);
        }
    }

    private void showMessage(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("OK", null);
        builder.show();
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
