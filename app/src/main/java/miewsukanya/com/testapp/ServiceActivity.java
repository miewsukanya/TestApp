package miewsukanya.com.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ServiceActivity extends AppCompatActivity {
    //Explicit
    private ListView listView;
    private  String[] nameStrings,phoneStrings, imageStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        //Bind widget
        listView = (ListView) findViewById(R.id.livFriend);

        //Receive value from Intent รับค่ามาจาก mainactivity
        nameStrings = getIntent().getStringArrayExtra("Name");
        phoneStrings = getIntent().getStringArrayExtra("Phone");
        imageStrings = getIntent().getStringArrayExtra("Image");
        MyAdapter myAdapter = new MyAdapter(ServiceActivity.this, nameStrings, phoneStrings, imageStrings);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                confirmCall(nameStrings[position], phoneStrings[position]);

            }//onItemClick
        });

    }//Main Method

    private void confirmCall(String nameString, String phoneString) {
        
    }

}//Main Class
