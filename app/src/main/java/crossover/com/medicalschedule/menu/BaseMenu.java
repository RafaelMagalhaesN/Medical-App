package crossover.com.medicalschedule.menu;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import crossover.com.medicalschedule.ConferenceActivity;
import crossover.com.medicalschedule.CreateNewConferenceActivity;
import crossover.com.medicalschedule.R;

/**
 * Created by rafae on 31/08/2016.
 */
public class BaseMenu extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private NavigationView _navigationView;
    private DrawerLayout _fullLayout;
    private Toolbar _toolbar;
    private ActionBarDrawerToggle _drawerToggle;


    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        _fullLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);

        FrameLayout activityContainer = (FrameLayout) _fullLayout.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        super.setContentView(_fullLayout);

        _toolbar = (Toolbar) findViewById(R.id.toolbar);
        _navigationView = (NavigationView) findViewById(R.id.navigationView);

        if (useToolbar()) {
            setSupportActionBar(_toolbar);
        } else {
            _toolbar.setVisibility(View.GONE);
        }

        setUpNavView();
    }

    protected boolean useToolbar() {
        return true;
    }

    protected void setUpNavView() {
        _navigationView.setNavigationItemSelectedListener(this);

        if (useDrawerToggle()) {
            _drawerToggle = new ActionBarDrawerToggle(this, _fullLayout, _toolbar,
                    R.string.nav_drawer_opened,
                    R.string.nav_drawer_closed);

            _fullLayout.setDrawerListener(_drawerToggle);
            _drawerToggle.syncState();
        } else if (useToolbar() && getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(getResources()
                    .getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        }
    }

    /**
     * Helper method to allow child classes to opt-out of having the
     * hamburger menu.
     *
     * @return
     */
    protected boolean useDrawerToggle() {
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        _fullLayout.closeDrawer(GravityCompat.START);
        return onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.all_conferences:
                startActivity(new Intent(this, ConferenceActivity.class));
                return true;

            case R.id.create_conference:
                startActivity(new Intent(this, CreateNewConferenceActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
