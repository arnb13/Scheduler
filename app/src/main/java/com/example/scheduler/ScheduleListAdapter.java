package com.example.scheduler;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ScheduleListAdapter extends ArrayAdapter<Scheduler> {
    private Context mContext;
    int mResource;
    List<Client> clients;


    public ScheduleListAdapter(Context context, int resource, @NonNull List <Client> objects1,
                               @NonNull List<Scheduler> objects) {
        super(context, resource, objects);

        mContext = context;
        mResource = resource;
        clients = objects1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getClientName(getItem(position).clientId);
        String location = getItem(position).location;
        String time = new SimpleDateFormat("yyyy.MM.dd hh:mm a").
                format(getItem(position).time);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvPlace = (TextView) convertView.findViewById(R.id.tvPlace);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);

        tvName.setText(name);
        tvPlace.setText(location);
        tvTime.setText(time);

        return convertView;
    }

    private String getClientName(int id){
        for (int i = 0 ; i < clients.size() ; i++) {
            if (clients.get(i).id == id){
                return clients.get(i).name;
            }
        }
        return "null";
    }
}
