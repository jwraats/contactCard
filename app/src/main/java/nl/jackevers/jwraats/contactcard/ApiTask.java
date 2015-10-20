package nl.jackevers.jwraats.contactcard;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jack on 18-10-2015.
 */
public class ApiTask extends AsyncTask<String, Void, String> {

    public interface OnPersonAvailable {
        void onPersonAvailable(Person person);
    }

    private static final String TAG = "ApiTask";

    private OnPersonAvailable listener = null;

    public ApiTask(OnPersonAvailable listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {

        String urlString = params[0]; //"https://randomuser.me/api";
        String response = "";

        InputStream inputStream = null;
        int responseCode = -1;


        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();

            if (!(urlConnection instanceof HttpURLConnection)) {
                // Url
                return null;
            }

            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            //setAllowUserINteraction is unused according to the documentation
            //httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true); // nice to remember.
            httpConnection.setRequestMethod("GET");
            httpConnection.connect(); // do the actual Get

            responseCode = httpConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { //check if the get went OK?
                inputStream = httpConnection.getInputStream(); //getting the inputstream
                response = getStringFromInputStream(inputStream); // input stream to response
                //Log.i(TAG, response);
            }
        } catch (MalformedURLException e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        }

        return response;
    }

    protected void onPostExecute(String response) {

        //Log.i(TAG, response);

        // parse JSON and inform caller
        JSONObject jsonObject;

        try {
            // Top level json object
            jsonObject = new JSONObject(response);

            // Get all users and start looping
            JSONArray users = jsonObject.getJSONArray("results");
            for(int idx = 0; idx < users.length(); idx++) {
                // array level objects and get user
                JSONObject array = users.getJSONObject(idx);
                JSONObject user = array.getJSONObject("user");

                // get gender
                String gender = user.getString("gender");

                // Get title, first and last name
                JSONObject name = user.getJSONObject("name");

                // Get image url
                JSONObject picture = user.getJSONObject("picture");

                // Create new Person object
                Person p = new Person(
                        user.getString("email"),
                        (gender.equals("male")),
                        name.getString("first"),
                        name.getString("last"),
                        picture.getString("large"),
                        picture.getString("thumbnail")

                );

                // call back with new person data
                listener.onPersonAvailable(p);

            }
        } catch( JSONException ex) {
            Log.e(TAG, ex.getLocalizedMessage());
        }
    }

    // Diederich's code
    // convert InputStream to String
    //
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

}