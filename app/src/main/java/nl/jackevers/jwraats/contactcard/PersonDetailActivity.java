package nl.jackevers.jwraats.contactcard;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PersonDetailActivity extends AppCompatActivity implements PersonFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(String email) {
        //Doorsturen naar andere Fragments ...
        PersonFragment info = (PersonFragment)
                getFragmentManager().findFragmentById(R.id.personDetails_fragment);
        //
        PersonStorage.lastPerson = PersonStorage.getPersonByEmail(email);
        if(null != info) {
            info.updatePerson(PersonStorage.lastPerson);
        }
    }
}
