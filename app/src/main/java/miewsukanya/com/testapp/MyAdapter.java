package miewsukanya.com.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Sukanya Boonpun on 13/11/2559.
 */

public class MyAdapter extends BaseAdapter{
    //Explicit
    private Context context;
    private  String[] nameStrings,phoneStrings, imageStrings;
    private TextView nameTextView, phoneTextView;
    private ImageView imageView;

    public MyAdapter(Context context,
                     String[] nameStrings,
                     String[] phoneStrings,
                     String[] imageStrings) {
        this.context = context;
        this.nameStrings = nameStrings;
        this.phoneStrings = phoneStrings;
        this.imageStrings = imageStrings;
    }

    @Override
    public int getCount() {
        return nameStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.my_layout, parent, false);

        //BindGet
        nameTextView = (TextView) view.findViewById(R.id.textView2);
        phoneTextView = (TextView) view.findViewById(R.id.textView3);
        imageView = (ImageView) view.findViewById(R.id.imageView2);

        //show view
        nameTextView.setText(nameStrings[position]);
        phoneTextView.setText(phoneStrings[position]);

        Picasso.with(context).load(imageStrings[position]).into(imageView);

        return view;
    }
}//main class
