package com.example.scheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ClientListAdapter extends ArrayAdapter <Client> {
    private Context mContext;
    int mResource;


    public ClientListAdapter(Context context, int resource, @NonNull ArrayList <Client> objects) {
        super(context, resource, objects);

        mContext = context;
        mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName().toString();
        String company = getItem(position).getCompany().toString();

        Client c = new Client(500, name, "abc", company, "ddd");

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView Tname = (TextView) convertView.findViewById(R.id.text_name);
        TextView Tcompany = (TextView) convertView.findViewById(R.id.text_company);

        Tname.setText(name);
        Tcompany.setText(company);

        return convertView;


    }
}
