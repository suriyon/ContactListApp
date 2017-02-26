package cs.naosuke.contactlistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NAOSUKE on 2/26/2017.
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<Contact> contacts;
    private LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<Contact> contacts){
        this.contacts = contacts;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder{
        TextView tvName;
        TextView tvPhone;
    }
    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contacts.get(position).getId();
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            view = inflater.inflate(R.layout.contact_layout, parent, false);
            holder = new ViewHolder();

            holder.tvName = (TextView) view.findViewById(R.id.edt_cus_name);
            holder.tvPhone = (TextView) view.findViewById(R.id.edt_cus_phone);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(contacts.get(position).getName());
        holder.tvPhone.setText(contacts.get(position).getPhone());

        return view;
    }
}
