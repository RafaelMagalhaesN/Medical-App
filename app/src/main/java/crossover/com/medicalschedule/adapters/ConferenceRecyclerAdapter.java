package crossover.com.medicalschedule.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import crossover.com.medicalschedule.R;
import crossover.com.medicalschedule.ViewConferenceActivity;
import crossover.com.medicalschedule.models.Conference;

/**
 * Created by rafae on 30/08/2016.
 */
public class ConferenceRecyclerAdapter extends RecyclerView.Adapter<ConferenceRecyclerAdapter.ConferenceViewHolder> {

    public static class ConferenceViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private TextView date,hour, address, title, topics;


        ConferenceViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            date = (TextView)itemView.findViewById(R.id.txtDate);
            hour = (TextView)itemView.findViewById(R.id.txtHour);
            address = (TextView)itemView.findViewById(R.id.txtAddress);
            title = (TextView)itemView.findViewById(R.id.txtTitle);
        }
    }

    List<Conference> conference;
    public ConferenceRecyclerAdapter(List<Conference> conference) {
        this.conference = conference;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ConferenceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_conference, viewGroup, false);
        ConferenceViewHolder conferenceViewHolder = new ConferenceViewHolder(v);
        return conferenceViewHolder;
    }

    @Override
    public void onBindViewHolder(final ConferenceViewHolder conferenceViewHolder, final int position) {
        conferenceViewHolder.date.setText(conference.get(position).getDate());
        conferenceViewHolder.hour.setText(conference.get(position).getHour());
        conferenceViewHolder.address.setText(conference.get(position).getAddress());
        conferenceViewHolder.title.setText(conference.get(position).getTitle());
        conferenceViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(conferenceViewHolder.context, ViewConferenceActivity.class);
                intent.putExtra("id",conference.get(position).getId());
                intent.putExtra("title",conference.get(position).getTitle());
                intent.putExtra("date",conference.get(position).getDate());
                intent.putExtra("hour", conference.get(position).getHour());
                intent.putExtra("address",conference.get(position).getAddress());
                intent.putExtra("topics", conference.get(position).getTopics());
                intent.putExtra("suggest", conference.get(position).getSuggests());
                conferenceViewHolder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return conference.size();
    }
}
