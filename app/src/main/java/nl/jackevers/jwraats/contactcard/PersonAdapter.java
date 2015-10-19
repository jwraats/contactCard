package nl.jackevers.jwraats.contactcard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflator;
    ArrayList mPersonArrayList;

    public PersonAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Person> personArrayList)
    {
        mContext = context;
        mInflator = layoutInflater;
        mPersonArrayList = personArrayList;
    }

    @Override
    public int getCount() {
        int size = mPersonArrayList.size();
        return size;
    }

    @Override
    public Object getItem(int position) {
        return mPersonArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        // Create new or recycle one
        if(convertView == null) {
            convertView = mInflator.inflate(R.layout.listview_row, null);

            viewHolder = new ViewHolder();
            viewHolder.profileImageRow = (ImageView) convertView.findViewById(R.id.profileImageRow);
            viewHolder.firstNameRow = (TextView) convertView.findViewById(R.id.firstNameRow);
            viewHolder.lastNameRow = (TextView) convertView.findViewById(R.id.lastNameRow);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Person person = (Person) mPersonArrayList.get(position);

        viewHolder.firstNameRow.setText(person.firstName);
        viewHolder.lastNameRow.setText(person.lastName);
        //viewHolder.profileImageRow.setImageBitmap(person.bitmap);

        return convertView;
    }

    private static class ViewHolder {
        public ImageView profileImageRow;
        public TextView firstNameRow;
        public TextView lastNameRow;
    }
}
