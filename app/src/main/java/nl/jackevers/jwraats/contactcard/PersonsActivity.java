package nl.jackevers.jwraats.contactcard;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;

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

    private void fillPersonList() {
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

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            PersonFragment info = (PersonFragment)
                    getFragmentManager().findFragmentById(R.id.person_details_fragment);
            if(null != info) {
                info.updatePerson(PersonStorage.lastPerson);
            }
        }
        else {
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
        // In Landscape, info != null
        if(null != info) {
            info.updatePerson(PersonStorage.lastPerson);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(this, PersonDetailActivity.class);
            startActivity(intent);
        }
    }
}
