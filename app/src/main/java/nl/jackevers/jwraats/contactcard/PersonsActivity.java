package nl.jackevers.jwraats.contactcard;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

public class PersonsActivity extends AppCompatActivity implements PersonListFragment.OnFragmentInteractionListener, PersonFragment.OnFragmentInteractionListener {
    private Person lastPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);


        //Strange bug with Fragments
        // http://stackoverflow.com/questions/5293850/fragment-duplication-on-fragment-transaction
        // http://stackoverflow.com/questions/13446935/fragmenttransaction-replace-not-working
        PersonListFragment plf = new PersonListFragment();
        //
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.placeHolder, plf);
        // Commit
        transaction.commit();

    }

    // http://stackoverflow.com/questions/26047988/pressing-back-does-not-return-to-previous-fragment
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(String email) {
        //Doorsturen naar andere Fragments ...
        PersonFragment info = (PersonFragment)
                getFragmentManager().findFragmentById(R.id.person_details_fragment);
        lastPerson = PersonStorage.getPersonByEmail(email);
        // In Landscape, info != null
        if (info != null ) {
            //
            info.updatePerson(lastPerson);
        }else{
            PersonFragment pf = new PersonFragment();
            pf.setPerson(lastPerson);
            //
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.placeHolder, pf);
            transaction.addToBackStack(pf.getTag());

            // Commit
            transaction.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //Just because Person Details frame wants it :/
    }
}
