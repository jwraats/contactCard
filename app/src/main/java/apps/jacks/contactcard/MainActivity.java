package apps.jacks.contactcard;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements ApiTask.OnPersonAvailable, PersonFragment.OnFragmentInteractionListener, PersonDetailsActivity.OnFragmentInteractionListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add persons to the PersonStorage
        for (int i = 0; i < 10; i++){
            ApiTask apiTask = new ApiTask(this);
            String[] url = new String[] { "https://randomuser.me/api/"};
            apiTask.execute(url);
        }
    }

    @Override
    public void onPersonAvailable(Person person) {
        PersonStorage.addItem(person);

        //notifyDatasetChanged on the default adapter.. geez
        ListView personListView = (ListView) findViewById(R.id.PersonListView);
        ((BaseAdapter)personListView.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPersonSelected(String email) {
        PersonDetailsActivity info = (PersonDetailsActivity)
                getFragmentManager().findFragmentById(R.id.fragment2);

        if(info != null){ //Use the fragment frame.

        }else{ //Use activity
            PersonDetailsActivity pda = new PersonDetailsActivity();
            pda.setPerson(PersonStorage.getPersonByEmail(email));

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, pda);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
