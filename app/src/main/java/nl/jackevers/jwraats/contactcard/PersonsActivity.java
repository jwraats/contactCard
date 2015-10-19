package nl.jackevers.jwraats.contactcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PersonsActivity extends AppCompatActivity implements PersonListFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
    }

    @Override
    public void onFragmentInteraction(String msg) {
        // Doorsturen naar andere Fragments ...
//        PersonListFragment info = (PersonListFragment)
//                getFragmentManager().findFragmentById(R.id.person_list_fragment);
//
//        // In Landscape, info != null
//        if (info != null ) {
//
//        }else{
//
//        }
    }
}
