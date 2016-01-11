package com.hg.contactsretrofit.api;

import com.hg.contactsretrofit.model.ContactsModel;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Hurman on 17/12/2015.
 */
public interface IContacts {

    @GET("/contacts")
    public void getContacts(Callback<List<ContactsModel>> response);

}
