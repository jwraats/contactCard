package nl.jackevers.jwraats.contactcard;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwraats on 19/10/15.
 */
public class Person {

    public Boolean isMale;
    public String email, firstName, lastName, imageURL;
    public Bitmap thumbnailImg;

    private PersonAdapter pa;

    public Person(String email, Boolean isMale, String firstName, String lastName, String imageURL) {
        this.email = email;
        this.isMale = isMale;
        this.firstName = makeStringCapitalized(firstName);
        this.lastName = makeStringCapitalized(lastName);
        this.imageURL = imageURL;
    }

    public Bitmap getThumbnailImage() {
        return this.thumbnailImg;
    }

    private String makeStringCapitalized(String input) {
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }

    public PersonAdapter getAdapter() {
        return pa;
    }

    public void setAdapter(PersonAdapter pa) {
        this.pa = pa;
    }

    public void loadThumbnailImage(PersonAdapter pa) {
        this.pa = pa;
        if (this.imageURL != null && !this.imageURL.equals("")) {
            new ImageLoadTask().execute(this.imageURL);
        }
    }

    //Lazy LOADING
    private class ImageLoadTask extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
        }

        // PARAM[0] IS IMG URL
        protected Bitmap doInBackground(String... param) {
            try {
                Bitmap b = PersonStorage.getBitmapFromURL(param[0]);
                return b;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onProgressUpdate(String... progress) {
            // NO OP
        }

        protected void onPostExecute(Bitmap ret) {
            if (ret != null) {
                thumbnailImg = ret;
                if (pa != null) {
                    // WHEN IMAGE IS LOADED NOTIFY THE ADAPTER
                    pa.notifyDataSetChanged();
                }
            }
        }
    }

}
