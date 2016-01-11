package com.hg.contactsretrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.hg.contactsretrofit.adapter.ContactsAdapter;
import com.hg.contactsretrofit.api.IContacts;
import com.hg.contactsretrofit.constants.Constants;
import com.hg.contactsretrofit.model.ContactsModel;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        // end point is your URL
        // log level brings logcat output
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL).build();

        // create interface object
        // callback happens on main thread, but callback is asynchronoous
        IContacts iContacts = restAdapter.create(IContacts.class);
        iContacts.getContacts(new Callback<List<ContactsModel>>() {
            @Override
            public void success(List<ContactsModel> contactsModels, Response response) {

                dismissDialog();

               /* for (int i = 0; i < contactsModels.size(); i++) {
                    ContactsModel contactsModel = contactsModels.get(i);
                    Log.i("Info 33333333333333333", contactsModel.getFirstName() + " " + contactsModel.getSurname());
                }*/

                mAdapter = new ContactsAdapter(contactsModels, R.layout.recycler_row, getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void failure(RetrofitError error) {

                dismissDialog();

                if(error.getKind().equals(RetrofitError.Kind.NETWORK)){
                    Toast.makeText(MainActivity.this, "Network error!", Toast.LENGTH_SHORT).show();
                } else if (error.getKind().equals(RetrofitError.Kind.HTTP)){
                    Toast.makeText(MainActivity.this, "HTTP error!", Toast.LENGTH_SHORT).show();
                } else if (error.getKind().equals(RetrofitError.Kind.CONVERSION)){
                    Toast.makeText(MainActivity.this, "Conversion error!", Toast.LENGTH_SHORT).show();
                } else if(error.getKind().equals(RetrofitError.Kind.UNEXPECTED)){
                    Toast.makeText(MainActivity.this, "Unexpected error!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();

        dismissDialog();
    }

    public void dismissDialog(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

}
