package crossover.com.medicalschedule;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import crossover.com.medicalschedule.adapters.ConferenceRecyclerAdapter;
import crossover.com.medicalschedule.dao.ControllerDAO;
import crossover.com.medicalschedule.menu.BaseMenu;
import crossover.com.medicalschedule.models.Conference;
import crossover.com.medicalschedule.models.User;
import crossover.com.medicalschedule.utils.UserUtils;


public class ConferenceActivity extends BaseMenu {



    ControllerDAO _controllerDAO;
    private RecyclerView recycler;
    private ConferenceRecyclerAdapter _conferenceRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);
        _controllerDAO = new ControllerDAO(this);

        recycler =(RecyclerView)findViewById(R.id.recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setHasFixedSize(true);

        readNewConferences();
        enableMenus(UserUtils.getIsAdmin());


    }

    private void enableMenus(String admin){
        if(admin.equals("0")){
            doctorPanel();
        }else{
            adminPanel();
        }

    }

    private void adminPanel() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        Menu nav_menu = navigationView.getMenu();
        nav_menu.findItem(R.id.conferenceAdminPanel).setVisible(true);
    }

    private void doctorPanel() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        Menu nav_menu = navigationView.getMenu();
        nav_menu.findItem(R.id.my_conferences).setVisible(true);
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

            case R.id.create_conference:
                startActivity(new Intent(this, CreateNewConferenceActivity.class));
                return true;

            case R.id.logout:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        try {
            Log.v("On restart: ","On restart");
            _conferenceRecyclerAdapter = new ConferenceRecyclerAdapter(_controllerDAO.readAllConferences());
            _conferenceRecyclerAdapter.notifyDataSetChanged();
            recycler.setAdapter(_conferenceRecyclerAdapter);
        }catch (NullPointerException e){
                showMessage("Info","No conferences avaliable!",this);
            }
    }

    private void showMessage(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("OK",null);
        builder.show();
    }

    private void readNewConferences(){
        try {
            _conferenceRecyclerAdapter = new ConferenceRecyclerAdapter(_controllerDAO.readAllConferences());
            recycler.setAdapter(_conferenceRecyclerAdapter);
        } catch (NullPointerException e){
            showMessage("Info","No conferences avaliable!",this);
        }
    }




}
