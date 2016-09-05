package crossover.com.medicalschedule.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import crossover.com.medicalschedule.ConferenceActivity;
import crossover.com.medicalschedule.MyInvitesActivity;
import crossover.com.medicalschedule.R;
import crossover.com.medicalschedule.ViewConferenceActivity;
import crossover.com.medicalschedule.dao.ControllerDAO;
import crossover.com.medicalschedule.models.Conference;
import crossover.com.medicalschedule.models.User;
import crossover.com.medicalschedule.utils.UserUtils;

/**
 * Created by rafae on 31/08/2016.
 */
public class MyInvitesRecyclerAdapter extends RecyclerView.Adapter<MyInvitesRecyclerAdapter.MyInvitesViewHolder> {

public static class MyInvitesViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView date,hour, address, title, topics;

    MyInvitesViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        date = (TextView)itemView.findViewById(R.id.txtDate);
        hour = (TextView)itemView.findViewById(R.id.txtHour);
        address = (TextView)itemView.findViewById(R.id.txtAddress);
        title = (TextView)itemView.findViewById(R.id.txtTitle);
        topics = (TextView)itemView.findViewById(R.id.txtTopicsDefault);
    }
}

    List<Conference> conference;
    public MyInvitesRecyclerAdapter(List<Conference> conference) {
        this.conference = conference;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MyInvitesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_invite, viewGroup, false);
        MyInvitesViewHolder myInvitesViewHolder = new MyInvitesViewHolder(v);
        return myInvitesViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyInvitesViewHolder myInvitesViewHolder, final int position) {
        myInvitesViewHolder.date.setText(conference.get(position).getDate());
        myInvitesViewHolder.hour.setText(conference.get(position).getHour());
        myInvitesViewHolder.address.setText(conference.get(position).getAddress());
        myInvitesViewHolder.title.setText(conference.get(position).getTitle());
        myInvitesViewHolder.topics.setText(conference.get(position).getTopics());
        myInvitesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder newInvite = new AlertDialog.Builder(myInvitesViewHolder.context);
                newInvite.setCancelable(true);
                newInvite.setTitle("Info");
                newInvite.setMessage("Accept conference and add in your calendar?");
                newInvite.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Calendar cal = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US);
                        String timeAndDate = conference.get(position).getDate()+" "+conference.get(position).getHour();

                        try {
                            cal.setTime(simpleDateFormat.parse(timeAndDate));
                            Intent intent = new Intent (Intent.ACTION_EDIT);
                            intent.setType("vnd.android.cursor.item/event");
                            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,cal.getTimeInMillis());
                            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,cal.getTimeInMillis());
                            intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                            intent.putExtra(CalendarContract.Events.TITLE,conference.get(position).getTitle());
                            intent.putExtra(CalendarContract.Events.DESCRIPTION, conference.get(position).getTopics());
                            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, conference.get(position).getAddress());
                            myInvitesViewHolder.context.startActivity(intent);
                        } catch (ParseException e) {
                            Toast.makeText( myInvitesViewHolder.context, "Error!"+e, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                newInvite.setNegativeButton("Skip",  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ControllerDAO controllerDAO = new ControllerDAO(myInvitesViewHolder.context);
                        User user = controllerDAO.readOneUser(UserUtils.getUserName());
                        StringBuilder newSchedules = new StringBuilder();
                        for (String title : user.getMySchedulesArray()) {
                            if(!conference.get(position).getTitle().equalsIgnoreCase(title)){
                                newSchedules.append(title+"/");
                            }
                        }
                        user.setMySchedules(newSchedules.toString());
                        controllerDAO.updateUser(user);
                        Intent intent = new Intent(myInvitesViewHolder.context, ConferenceActivity.class);
                        myInvitesViewHolder.context.startActivity(intent);
                    }
                });
                newInvite.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return conference.size();
    }
}
