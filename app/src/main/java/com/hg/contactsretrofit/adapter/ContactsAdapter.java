package com.hg.contactsretrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hg.contactsretrofit.R;
import com.hg.contactsretrofit.model.ContactsModel;
import java.util.List;

/**
 * Created by Hurman on 17/12/2015.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{

    private List<ContactsModel> contacts;
    private int rowLayout;
    private Context mContext;

    public ContactsAdapter(List<ContactsModel> contacts, int rowLayout, Context context) {
        this.contacts = contacts;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder viewHolder, int i) {

        //final ContactsModel contactsModel = contacts.get(position);
        try {
            viewHolder.contactName.setText(contacts.get(i).getFirstName() + " " + contacts.get(i).getSurname());
            viewHolder.contactAddress.setText(contacts.get(i).getAddress());
            viewHolder.contactPhoneNumber.setText(contacts.get(i).getPhoneNumber());
            viewHolder.contactEmail.setText(contacts.get(i).getEmail().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*// viewHolder.versionName=viewHolder.countryName.getText().toString();
        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(mContext, "#" + position + " - " + country.name + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "#" + position + " - " +country.name, Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView contactName, contactAddress, contactPhoneNumber, contactEmail;
        public ViewHolder(View itemView) {
            super(itemView);
            contactName = (TextView) itemView.findViewById(R.id.tv_name);
            contactAddress = (TextView)itemView.findViewById(R.id.tv_address);
            contactPhoneNumber = (TextView)itemView.findViewById(R.id.tv_phone_number);
            contactEmail = (TextView)itemView.findViewById(R.id.tv_email);

        }
    }
}
