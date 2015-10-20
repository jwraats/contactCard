package nl.jackevers.jwraats.contactcard;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class PersonsActivity extends AppCompatActivity implements ApiTask.OnPersonAvailable, PersonListFragment.OnFragmentInteractionListener, PersonFragment.OnFragmentInteractionListener {

    @Override
    public void onPersonAvailable(Person person) {
        PersonStorage.addItem(person);
        ListView personListView = (ListView) findViewById(R.id.personList);
        if(personListView != null){
            PersonAdapter pa = (PersonAdapter)personListView.getAdapter();
            if(pa != null){
                person.loadThumbnailImage(pa);
                pa.notifyDataSetChanged();
            }
        }
    }

    private void fillPersonList()
    {
        //ApiTask
        //add persons to the PersonStorage
        for (int i = 0; i < 10; i++){
            ApiTask apiTask = new ApiTask(this);
            String[] url = new String[] { "https://randomuser.me/api/" };
            apiTask.execute(url);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);

        if(PersonStorage.ITEMS.isEmpty())
        {
            fillPersonList();
        }

        //Strange bug with Fragments
        // http://stackoverflow.com/questions/5293850/fragment-duplication-on-fragment-transaction
        // http://stackoverflow.com/questions/13446935/fragmenttransaction-replace-not-working
        PersonListFragment plf = new PersonListFragment();
        //
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.placeHolder, plf);
        // Commit
        transaction.commit();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            PersonFragment info = (PersonFragment)
                    getFragmentManager().findFragmentById(R.id.person_details_fragment);
            if(null != info) {
                info.updatePerson(PersonStorage.lastPerson);
            }
        }
        else
        {
            //when the screen is being created in portrait mode and there is more than zero entries in the backStack. go back.
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
            }
        }

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
        PersonStorage.lastPerson = PersonStorage.getPersonByEmail(email);

        if(null != info) {
            info.setmListener(this);
            info.updatePerson(PersonStorage.lastPerson);
            if (getResources().getConfiguration().orientation == 2) {
                info.updatePerson(PersonStorage.lastPerson);
            }
        }
        // In Landscape, info != null
        else{
            PersonFragment pf = new PersonFragment();
            pf.setmListener(this);
            pf.updatePerson(PersonStorage.lastPerson);
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

    @Override
    public void onFragmentCreate() {
        PersonFragment info = (PersonFragment)
                getFragmentManager().findFragmentById(R.id.person_details_fragment);
        if(null != info)
        {
            info.updatePerson(PersonStorage.lastPerson);
        }
    }
}
